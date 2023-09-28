package io.github.radixhomework.nativehelper.model.resources;

import lombok.Data;

@Data
public class ResourcesBundle {
    Resources resources = new Resources();

    public void addIncludes(String file) {
        if(resources.getIncludes().stream().noneMatch(pattern -> pattern.getPattern().equals(file))) {
            resources.getIncludes().add(new Pattern(file));
        }
    }

    public void addExcludes(String file) {
        if(resources.getExcludes().stream().noneMatch(pattern -> pattern.getPattern().equals(file))) {
            resources.getExcludes().add(new Pattern(file));
        }
    }
}
