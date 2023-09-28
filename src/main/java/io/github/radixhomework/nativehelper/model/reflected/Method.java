package io.github.radixhomework.nativehelper.model.reflected;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class Method {
    private String name;
    private List<String> parameterTypes;
}
