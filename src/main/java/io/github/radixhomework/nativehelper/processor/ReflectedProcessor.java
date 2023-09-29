package io.github.radixhomework.nativehelper.processor;

import com.google.auto.service.AutoService;
import io.github.radixhomework.nativehelper.annotation.Reflected;
import io.github.radixhomework.nativehelper.model.reflected.Field;
import io.github.radixhomework.nativehelper.model.reflected.Metadata;
import io.github.radixhomework.nativehelper.model.reflected.Method;
import io.github.radixhomework.nativehelper.service.ReflectedService;
import io.github.radixhomework.nativehelper.util.ConfigWriter;
import io.github.radixhomework.nativehelper.util.Constants;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("io.github.radixhomework.nativehelper.annotation.Reflected")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class ReflectedProcessor extends AbstractProcessor {

    private static final String UNSUPPORTED_MESSAGE_END = " elements are not supported yet for native image reflection configuration";

    ReflectedService service = new ReflectedService();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            return false;
        }
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing Reflected Annotation");
        List<Metadata> metadataList = new ArrayList<>();
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            Metadata metadata = new Metadata();

            for (Element element : annotatedElements) {
                Reflected instance = element.getAnnotation(Reflected.class);

                // Initialize metadata and configuration from annotation instance
                metadata = service.initMetadata(instance);

                PackageElement packageElement = (PackageElement) element.getEnclosingElement();
                // Package name + Class name
                metadata.setName(packageElement.getQualifiedName().toString() + "." + element.getSimpleName());

                List<Method> methods = new ArrayList<>();
                List<Field> fields = new ArrayList<>();

                for (Element classElement : element.getEnclosedElements()) {
                    switch (classElement.getKind()) {
                        case FIELD -> {
                            if (metadata.isFieldsToInspect()) {
                                VariableElement fieldElement = (VariableElement) classElement;
                                fields.add(service.gatherMetadata(fieldElement));
                            }
                        }
                        case METHOD -> {
                            if (metadata.isMethodsToInspect()) {
                                ExecutableElement methodElement = (ExecutableElement) classElement;
                                if (!metadata.isMethodToIgnore(methodElement.getSimpleName().toString())) {
                                    methods.add(service.gatherMetadata(methodElement));
                                }
                            }
                        }
                        // TODO: add constructors support (in METHOD case ?)
                        case CONSTRUCTOR -> this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
                                "Constructors" + UNSUPPORTED_MESSAGE_END);
                        // TODO: add subclasses support
                        case CLASS -> this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
                                "Subclasses" + UNSUPPORTED_MESSAGE_END);
                        // TODO: add records support
                        case RECORD -> this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
                                "Records" + UNSUPPORTED_MESSAGE_END);
                        default -> {
                            // Silently do nothing for now
                        }
                    }
                }

                metadata.setMethods(methods);
                metadata.setFields(fields);
            }

            metadataList.add(metadata);
        }
        ConfigWriter.writeConfigFile(this.processingEnv, Constants.REFLECT_CONFIG_FILENAME, metadataList);
        return true;
    }

}
