package io.github.radixhomework.nativehelper.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigWriter {

    public static void writeConfigFile(ProcessingEnvironment processingEnv, String fileName, Object content) {
        try {
            Map<String, String> options = processingEnv.getOptions();
            String project = options.get("project");
            if (project != null) {
                project = "/" + project.replace('\\', '/') + "/";
            } else {
                project = "/";
            }
            String relativeName = Constants.NATIVE_HELPER_BASE_PATH + project + fileName;
            FileObject resource = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", relativeName);
            ObjectMapper mapper = new ObjectMapper();
            String text = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(content);
            try (Writer writer = resource.openWriter()) {
                writer.write(text);
                writer.flush();
            }
        } catch (IOException ioe) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Error trying to create resource file: " + ioe.getMessage());
        }
    }
}
