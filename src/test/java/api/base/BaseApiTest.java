package api.base;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BaseApiTest {

	// For reduces boilerplate code.
	@BeforeAll
	static void setup() {

		RestAssured.baseURI = "https://reqres.in";

		ConfigLoader config = new ConfigLoader();
		
		RestAssured.requestSpecification =given().header("x-api-key", config.getApiKey())
		.header("X-Resqres-Env", "prod")
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON);

	}

}
