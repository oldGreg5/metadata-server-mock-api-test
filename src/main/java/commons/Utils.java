package commons;

import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaClient;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class Utils {
    public void validateSchema(String jsonForValidation, String schemaFile) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/" + schemaFile)) {
            JSONObject schemaJson = new JSONObject(new JSONTokener(inputStream));
            SchemaLoader schemaLoader = SchemaLoader.builder()
                    .schemaClient(SchemaClient.classPathAwareClient())
                    .schemaJson(schemaJson)
                    .build();
            Schema schema = schemaLoader.load().build();
            schema.validate(new JSONObject(jsonForValidation));
            log.info("Validation success");
        } catch (ValidationException ex) {
            log.warn("Validation failed");
            List<ValidationException> validationFailures = ex.getCausingExceptions();
            if (validationFailures.size() == 0) {
                log.warn(ex.getSchemaLocation() + ex.getMessage());
            } else {
                ex.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(log::warn);
            }
            throw new RuntimeException("Schema validation failure");
        }
    }
}
