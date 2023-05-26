package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class GetSingleUserSpecs {
    public static RequestSpecification getSingleUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .baseUri("https://reqres.in")
            .basePath("/api");

    public static ResponseSpecification successfulGetSingleUserResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification unsuccessfulGetSingleUserResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(404)
            .build();
}
