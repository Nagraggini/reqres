package api.getUser;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import static io.restassured.RestAssured.given;

class UserIdNegativeTest extends BaseApiTest {

	/**
	 * Testing non-existing user query response.
	 */
	@Test
	void getUserIdUnsuccessfulTest() {
		given()
				.when()
				.get("/users/records/non-existent-id")
				.then()
				.log().ifValidationFails()
				.statusCode(404)
				.body("error", equalTo("record_not_found"))
				.body("message", equalTo("Record not found."));
	}
}