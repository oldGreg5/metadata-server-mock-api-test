import extensions.AfterTestExecutionExtension;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AfterTestExecutionExtension.class)
public class BaseTest {

    @AfterEach
    public void afterTestCleanup() {
        RestAssured.reset();
    }
}
