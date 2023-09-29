package io.github.radixhomework.nativehelper.model.reflected;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.radixhomework.nativehelper.util.MetadataFilter;
import lombok.Data;

import java.util.List;

@Data
@JsonFilter(MetadataFilter.NAME)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class Metadata {
    private Condition condition;
    private String name;
    private List<Method> methods;
    private List<Method> queriedMethods;
    private List<Field> fields;

    private Boolean allDeclaredClasses = false;
    private Boolean allDeclaredMethods = false;
    private Boolean allDeclaredFields = false;
    private Boolean allDeclaredConstructors = false;
    private Boolean allPublicClasses = false;
    private Boolean allPublicMethods = false;
    private Boolean allPublicFields = false;
    private Boolean allPublicConstructors = false;
    private Boolean allRecordComponents = false;
    private Boolean allNestMembers = false;
    private Boolean allSigners = false;
    private Boolean allPermittedSubclasses = false;
    private Boolean queryAllDeclaredMethods = false;
    private Boolean queryAllDeclaredConstructors = false;
    private Boolean queryAllPublicMethods = false;
    private Boolean queryAllPublicConstructors = false;
    private Boolean unsafeAllocated = false;

    @JsonIgnore
    private boolean ignoreEquals = true;
    @JsonIgnore
    private boolean ignoreCanEquals = true;
    @JsonIgnore
    private boolean ignoreHashCode = true;
    @JsonIgnore
    private boolean ignoreToString = true;

    /**
     * Indicates if fields should be inspected one by one.
     *
     * <p>If one of {@code allDeclaredFields} or {@code allPublicFields} boolean are set to true,
     * then no fields inspection is done.</p>
     *
     * @return whether fields should be inspected one by one
     */
    @JsonIgnore
    public boolean isFieldsToInspect() {
        return !(allDeclaredFields || allPublicFields);
    }

    /**
     * Indicates if methods should be inspected one by one.
     *
     * <p>If one of {@code allDeclaredMethods}, {@code allPublicMethods}, {@code queryAllDeclaredMethods} or
     * {@code queryAllPublicMethods} boolean are set to true, then no methods inspection is done.</p>
     *
     * @return whether methods should be inspected one by one
     */
    @JsonIgnore
    public boolean isMethodsToInspect() {
        return !(allDeclaredMethods || allPublicMethods || queryAllDeclaredMethods || queryAllPublicMethods);
    }

    /**
     * Indicates whether the given method should be ignored due to configuration.
     *
     * @param methodName the method name to check
     * @return whether this methode should be ignored
     */
    public boolean isMethodToIgnore(String methodName) {
        return ignoreEquals && "equals".equals(methodName)
                || ignoreCanEquals && "canEqual".equals(methodName)
                || ignoreHashCode && "hashCode".equals(methodName)
                || ignoreToString && "toString".equals(methodName);
    }
}
