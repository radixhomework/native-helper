package io.github.radixhomework.nativehelper.service;

import io.github.radixhomework.nativehelper.annotation.Reflected;
import io.github.radixhomework.nativehelper.model.reflected.Field;
import io.github.radixhomework.nativehelper.model.reflected.Metadata;
import io.github.radixhomework.nativehelper.model.reflected.Method;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;

public class ReflectedService {

    public Field gatherMetadata(VariableElement element) {
        Field field = new Field();
        field.setName(element.getSimpleName().toString());
        return field;
    }

    public Method gatherMetadata(ExecutableElement element) {
        Method method = new Method();
        method.setName(element.getSimpleName().toString());
        List<String> parameterTypes = new ArrayList<>();
        for (VariableElement parameter : element.getParameters()) {
            parameterTypes.add(parameter.asType().toString());
        }
        method.setParameterTypes(parameterTypes);
        return method;
    }

    public Metadata initMetadata(Reflected instance) {
        Metadata metadata = new Metadata();
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

        metadata.setIgnoreEquals(instance.ignoreEquals());
        metadata.setIgnoreCanEquals(instance.ignoreCanEqual());
        metadata.setIgnoreHashCode(instance.ignoreHashCode());
        metadata.setIgnoreToString(instance.ignoreToString());

        return metadata;
    }
}
