import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static commons.Endpoints.QUERY;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.contains;

public class QueryTest extends BaseTest {

    @Test
    public void queryWithSubjectsReturns200AndCorrectBody() {
        JSONObject bodyContent = new JSONObject().put("subjects", Arrays.asList("789ef8ae89617f34c07f7f6a12e4d65146f958c0bc15a97b4ff169f16861707079636f696e",
                "789ef8ae89617f34c07f7f6a12e4d65146f958c0bc15a97b4ff169f1"));
        JSONArray bodyValues = bodyContent.getJSONArray("subjects");

        given()
                .when()
                .baseUri(QUERY)
                .contentType(JSON)
                .body(bodyContent.toString())
                .post()
                .then()
                .statusCode(SC_OK)
                .body(contains(bodyValues.get(0), bodyValues.get(1)));
    }

    @Test
    public void queryWithPropertiesReturns200AndCorrectBody() {
        JSONObject bodyContent = new JSONObject().put("properties", Arrays.asList("very", "merry", "christmas"))
                .put("subjects", Arrays.asList("789ef8ae89617f34c07f7f6a12e4d65146f958c0bc15a97b4ff169f16861707079636f696e",
                        "789ef8ae89617f34c07f7f6a12e4d65146f958c0bc15a97b4ff169f1"));

        JSONArray bodyValuesProps = bodyContent.getJSONArray("properties");
        JSONArray bodyValuesSubj = bodyContent.getJSONArray("subjects");

        given()
                .when()
                .baseUri(QUERY)
                .contentType(JSON)
                .body(bodyContent.toString())
                .post()
                .then()
                .statusCode(SC_OK)
                .body(contains(bodyValuesProps.get(0), bodyValuesProps.get(1), bodyValuesProps.get(2),
                        bodyValuesSubj.get(0), bodyValuesSubj.get(1)));
    }

    @Test
    public void queryWithPropertiesAndSubjectsReturns200AndCorrectBody() {
        JSONObject bodyContent = new JSONObject().put("properties", Arrays.asList("very", "merry", "christmas"));
        JSONArray bodyValues = bodyContent.getJSONArray("properties");

        given()
                .when()
                .baseUri(QUERY)
                .contentType(JSON)
                .body(bodyContent.toString())
                .post()
                .then()
                .statusCode(SC_OK)
                .body(contains(bodyValues.get(0), bodyValues.get(1), bodyValues.get(2)));
    }

    @Test
    public void queryWrongMethodReturns405() {
        JSONObject bodyContent = new JSONObject().put("subjects", Arrays.asList("789ef8ae89617f34c07f7f6a12e4d65146f958c0bc15a97b4ff169f16861707079636f696e",
                "789ef8ae89617f34c07f7f6a12e4d65146f958c0bc15a97b4ff169f1"));
        JSONArray bodyValues = bodyContent.getJSONArray("subjects");

        given()
                .when()
                .baseUri(QUERY)
                .contentType(JSON)
                .body(bodyContent.toString())
                .put()
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void queryMalformedBodyReturns400() {
        given()
                .when()
                .baseUri(QUERY)
                .contentType(JSON)
                .body("toTuTataTupie")
                .post()
                .then()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    public void queryMissingBodyReturns400() {
        given()
                .when()
                .baseUri(QUERY)
                .contentType(JSON)
                .post()
                .then()
                .statusCode(SC_BAD_REQUEST);
    }
}
