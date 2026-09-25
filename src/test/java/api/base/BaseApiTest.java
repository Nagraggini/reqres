package api.base;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class BaseApiTest {

	// For reduces boilerplate code.
	@BeforeAll
	static void setup() {

		RestAssured.baseURI = "https://reqres.in";

		ConfigLoader config = new ConfigLoader();

		RestAssured.requestSpecification = new RequestSpecBuilder()
				// .addHeader("x-api-key", ConfigLoader.getApiKey())
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.build();

	}

}
