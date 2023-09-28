package io.github.radixhomework.nativehelper.processor;

import com.google.auto.service.AutoService;
import io.github.radixhomework.nativehelper.annotation.Reflected;
import io.github.radixhomework.nativehelper.model.reflected.Field;
import io.github.radixhomework.nativehelper.model.reflected.Metadata;
import io.github.radixhomework.nativehelper.model.reflected.Method;
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

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            return false;
        }
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,"Processing Reflected Annotation");
        List<Metadata> metadatas = new ArrayList<>();
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            Metadata metadata = new Metadata();
            for (Element element : annotatedElements) {
                PackageElement packageElement = (PackageElement) element.getEnclosingElement();
                // Package name + Class name
                metadata.setName(packageElement.getQualifiedName().toString() + "." + element.getSimpleName());
                List<Method> methods = new ArrayList<>();
                List<Field> fields = new ArrayList<>();

                for (Element classElement : element.getEnclosedElements()) {
                    switch (classElement.getKind()) {
                        case FIELD -> {
                            VariableElement fieldElement = (VariableElement) classElement;
                            Field field = new Field();
                            field.setName(fieldElement.getSimpleName().toString());
                            fields.add(field);
                        }
                        case METHOD -> {
                            ExecutableElement methodElement = (ExecutableElement) classElement;
                            Method method = new Method();
                            method.setName(classElement.getSimpleName().toString());
                            List<String> parameterTypes = new ArrayList<>();
                            for (VariableElement parameter : methodElement.getParameters()) {
                                parameterTypes.add(parameter.asType().toString());
                            }
                            method.setParameterTypes(parameterTypes);
                            methods.add(method);
                        }
                    }
                }

                metadata.setMethods(methods);
                metadata.setFields(fields);

                Reflected instance = element.getAnnotation(Reflected.class);
                metadata.setAllDeclaredClasses(instance.allDeclaredClasses());
                metadata.setAllDeclaredMethods(instance.allDeclaredMethods());
                metadata.setAllDeclaredFields(instance.allDeclaredFields());
                metadata.setAllDeclaredConstructors(instance.allDeclaredConstructors());
                metadata.setAllPublicClasses(instance.allPublicClasses());
                metadata.setAllPublicMethods(instance.allPublicMethods());
                metadata.setAllPublicFields(instance.allPublicFields());
                metadata.setAllPublicConstructors(instance.allPublicConstructors());
                metadata.setAllRecordComponents(instance.allRecordComponents());
                metadata.setAllNestMembers(instance.allNestMembers());
                metadata.setAllSigners(instance.allSigners());
                metadata.setAllPermittedSubclasses(instance.allPermittedSubclasses());
                metadata.setQueryAllDeclaredMethods(instance.queryAllDeclaredMethods());
                metadata.setQueryAllDeclaredConstructors(instance.queryAllDeclaredConstructors());
                metadata.setQueryAllPublicMethods(instance.queryAllPublicMethods());
                metadata.setQueryAllPublicConstructors(instance.queryAllPublicConstructors());
                metadata.setUnsafeAllocated(instance.unsafeAllocated());
            }
            metadatas.add(metadata);
        }
        ConfigWriter.writeConfigFile(this.processingEnv, Constants.REFLECT_CONFIG_FILENAME, metadatas);
        return true;
    }

    private String getPackage(Element element) {
        if (element.getEnclosingElement() != null){
            return getPackage(element.getEnclosingElement()) + "." + element.getSimpleName();
        } else {
            return element.getSimpleName().toString();
        }
    }
}
