package api.base;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class BaseApiTest {

	// For reduces boilerplate code.
	@BeforeAll
	static void setup() {

		RestAssured.baseURI = "https://reqres.in/api/collections";

		RestAssured.requestSpecification = new RequestSpecBuilder()
				.addHeader("x-api-key", ConfigLoader.getApiKey())
				.addHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
				.addQueryParam("project_id", ConfigLoader.getProjectId())
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.build();

		String apiKey = ConfigLoader.getApiKey();

		System.out.println("API key loaded: " + (apiKey != null && !apiKey.isBlank()));

	}

}
