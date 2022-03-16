import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static commons.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertiesTest extends BaseTest {

    @ParameterizedTest(name = "Property name: {0}, added: {1}")
    @CsvSource({PROPS_SUBJECT + ", /thing", PROPS_POLICY + ", /thing",
            PROPS_SUBJECT + ", tank", PROPS_POLICY + ", tank"})
    public void happyWrongPathReturn404(String path, String randomEnd) {
        given()
                .when()
                .baseUri(BASE_HAPPY)
                .basePath(path + randomEnd)
                .get()
                .then()
                .statusCode(SC_NOT_FOUND);
    }

    @ParameterizedTest(name = "Property name: {0}, added: {1}")
    @CsvSource({PROPS_SUBJECT + ", /thing", PROPS_POLICY + ", /thing",
            PROPS_SUBJECT + ", tank", PROPS_POLICY + ", tank"})
    public void amazingWrongPathReturn404(String path, String randomEnd) {
        given()
                .when()
                .baseUri(BASE_AMAZING)
                .basePath(path + randomEnd)
                .get()
                .then()
                .statusCode(SC_NOT_FOUND);
    }

    @ParameterizedTest(name = "Property name: {0}")
    @CsvSource({PROPS_URL, PROPS_NAME, PROPS_TICKER, PROPS_DECIMALS, PROPS_LOGO, PROPS_DESCRIPTION})
    public void happyReturns200WithValidBody(String property) {
        JsonPath response = given()
                .when()
                .baseUri(BASE_HAPPY)
                .basePath(property)
                .get()
                .then()
                .statusCode(SC_OK)
                .extract()
                .jsonPath();

        assertThat(Integer.valueOf(response.getString("sequenceNumber")), is(greaterThanOrEqualTo(0)));
        assertThat(response.getString("value"), containsStringIgnoringCase("happy"));
        assertThat(response.getList("signatures"), hasSize(1));
        assertEquals(response.getString("signatures.signature").length(), 130);
        assertEquals(response.getString("signatures.publicKey").length(), 66);
    }

    @ParameterizedTest(name = "Property name: {0}")
    @CsvSource({PROPS_URL, PROPS_NAME, PROPS_TICKER, PROPS_DECIMALS, PROPS_LOGO, PROPS_DESCRIPTION})
    public void amazingWithExtraParamReturns200WithValidBody(String property) {
        JsonPath response = given()
                .when()
                .baseUri(BASE_AMAZING)
                .basePath(property)
                .queryParam("someParam", "value")
                .get()
                .then()
                .statusCode(SC_OK)
                .extract()
                .jsonPath();

        assertThat(Integer.valueOf(response.getString("sequenceNumber")), is(greaterThanOrEqualTo(0)));
        assertThat(response.getString("value"), containsStringIgnoringCase("amazing"));
        assertThat(response.getList("signatures"), hasSize(1));
        assertEquals(response.getString("signatures.signature").length(), 130);
        assertEquals(response.getString("signatures.publicKey").length(), 66);
    }

    @ParameterizedTest(name = "Property name: {0}")
    @CsvSource({PROPS_SUBJECT, PROPS_POLICY,
            PROPS_SUBJECT, PROPS_POLICY})
    public void happyWrongMethodReturns405(String property) {
        given()
                .when()
                .baseUri(BASE_HAPPY)
                .basePath(property)
                .post()
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }
}
