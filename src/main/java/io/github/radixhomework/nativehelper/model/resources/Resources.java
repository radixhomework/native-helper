package io.github.radixhomework.nativehelper.model.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class Resources {

    public static final String MANIFEST = "META-INF/MANIFEST.MF";

    List<Pattern> includes = new ArrayList<>();
    List<Pattern> excludes = new ArrayList<>();

}
