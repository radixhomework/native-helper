package io.github.radixhomework.nativehelper.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auto.service.AutoService;
import io.github.radixhomework.nativehelper.annotation.Reflected;
import io.github.radixhomework.nativehelper.model.ReflectionField;
import io.github.radixhomework.nativehelper.model.ReflectionMetadata;
import io.github.radixhomework.nativehelper.model.ReflectionMethod;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("io.github.radixhomework.nativetool.annotation.Reflected")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class MetadataProcessor extends AbstractProcessor {

    private static final String OUTPUT_DIR = "target/native-config";
    private static final Path OUTPUT_PATH_DIR = Path.of(OUTPUT_DIR);
    private static final String OUTPUT_FILE = OUTPUT_DIR + "/reflection-config.json";
    private static final Path OUTPUT_PATH_FILE = Path.of(OUTPUT_FILE);

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            // If no annotation provided, return success without doing anything
            // FIXME: find a way to go through this processor only once per build
            return false;
        }
        List<ReflectionMetadata> metadatas = new ArrayList<>();
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            ReflectionMetadata metadata = new ReflectionMetadata();
            for (Element element : annotatedElements) {
                PackageElement packageElement = (PackageElement) element.getEnclosingElement();
                // Package name + Class name
                metadata.setName(packageElement.getQualifiedName().toString() + "." + element.getSimpleName());
                List<ReflectionMethod> methods = new ArrayList<>();
                List<ReflectionField> fields = new ArrayList<>();

                for (Element classElement : element.getEnclosedElements()) {
                    switch (classElement.getKind()) {
                        case FIELD -> {
                            VariableElement fieldElement = (VariableElement) classElement;
                            ReflectionField field = new ReflectionField();
                            field.setName(fieldElement.getSimpleName().toString());
                            fields.add(field);
                        }
                        case METHOD -> {
                            ExecutableElement methodElement = (ExecutableElement) classElement;
                            ReflectionMethod method = new ReflectionMethod();
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
        try {
            if (!Files.exists(OUTPUT_PATH_DIR)) {
                Files.createDirectory(OUTPUT_PATH_DIR);
            }
            if (Files.exists(OUTPUT_PATH_FILE)) {
                Files.delete(OUTPUT_PATH_FILE);
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(OUTPUT_FILE), metadatas);
        } catch (IOException e) {
            System.err.println(e);
            return false;
        }
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
