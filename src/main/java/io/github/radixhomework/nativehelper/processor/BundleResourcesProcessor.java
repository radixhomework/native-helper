package io.github.radixhomework.nativehelper.processor;

import com.google.auto.service.AutoService;
import io.github.radixhomework.nativehelper.annotation.BundleResources;
import io.github.radixhomework.nativehelper.exception.TooManyClassAnnotatedException;
import io.github.radixhomework.nativehelper.model.resources.ResourcesBundle;
import io.github.radixhomework.nativehelper.util.ConfigWriter;
import io.github.radixhomework.nativehelper.util.Constants;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Arrays;
import java.util.Set;

@SupportedAnnotationTypes("io.github.radixhomework.nativehelper.annotation.BundleResources")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class BundleResourcesProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            return false;
        }
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing BundleResources Annotation");
        ResourcesBundle resourcesBundle = new ResourcesBundle();
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            if (annotatedElements.size() > 1) {
                throw new TooManyClassAnnotatedException("Multiple annotation @BundleResources found.");
            }
            for (Element element : annotatedElements) {
                BundleResources instance = element.getAnnotation(BundleResources.class);
                if (instance.includes() != null) {
                    Arrays.asList(instance.includes()).forEach(resourcesBundle::addIncludes);
                }
                if (instance.excludes() != null) {
                    Arrays.asList(instance.excludes()).forEach(resourcesBundle::addExcludes);
                }
            }
        }
        ConfigWriter.writeConfigFile(this.processingEnv, Constants.RESOURCE_CONFIG_FILENAME, resourcesBundle);
        return true;
    }
}
