package io.github.radixhomework.nativehelper.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class ReflectionMetadata {
    private ReflectionCondition condition;
    private String name;
    private List<ReflectionMethod> methods;
    private List<ReflectionMethod> queriedMethods;
    private List<ReflectionField> fields;
    private boolean allDeclaredClasses = true;
    private boolean allDeclaredMethods = true;
    private boolean allDeclaredFields = true;
    private boolean allDeclaredConstructors = true;
    private boolean allPublicClasses = true;
    private boolean allPublicMethods = true;
    private boolean allPublicFields = true;
    private boolean allPublicConstructors = true;
    private boolean allRecordComponents = true;
    private boolean allNestMembers = true;
    private boolean allSigners = true;
    private boolean allPermittedSubclasses = true;
    private boolean queryAllDeclaredMethods = true;
    private boolean queryAllDeclaredConstructors = true;
    private boolean queryAllPublicMethods = true;
    private boolean queryAllPublicConstructors = true;
    private boolean unsafeAllocated = true;
}
