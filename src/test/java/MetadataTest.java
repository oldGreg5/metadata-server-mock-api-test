import commons.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static commons.Endpoints.BASE_AMAZING;
import static commons.Endpoints.BASE_HAPPY;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_OK;

public class MetadataTest extends BaseTest {

    @Test
    public void metadataForHappyReturns200AndCorrectBody() throws IOException {
        String resp = given()
                .when()
                .baseUri(BASE_HAPPY)
                .get()
                .then()
                .statusCode(SC_OK)
                .extract()
                .asString();

        new Utils().validateSchema(resp, "metadata.json");
    }

    @Test
    public void metadataForAmazingReturns200AndCorrectBody() throws IOException {
        String resp = given()
                .when()
                .baseUri(BASE_AMAZING)
                .get()
                .then()
                .statusCode(SC_OK)
                .extract()
                .asString();

        new Utils().validateSchema(resp, "metadata.json");
    }

    @Test
    public void metadataForAmazingWrongMethodReturns405() {
        given()
                .when()
                .baseUri(BASE_AMAZING)
                .post()
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }
}
