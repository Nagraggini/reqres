package api.getUser;

import api.base.*;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

class getUser extends BaseApiTest {

	/**
	 * Ellenőrzése az első felhasználó id mezőjének a first_name mezőjének.
	 */
	@Test
	void checkOneUserFirstName() {
		given().accept(ContentType.JSON).when().get("/api/users").then()
		.log().ifValidationFails()
		.statusCode(200).log()
				.ifValidationFails().body("$", notNullValue())
				.body("data[0].first_name", containsString("George"));
	}
	
	/**
	 * Ellenőrzése az utolsó felhasználó id mezőjének és az email mezőjének, hogy van-e benne 
	 * @ jel.
	 */
	@Test
	void checkLastUserEmail() {
		given().accept(ContentType.JSON).when().get("/api/users")
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.body("data[5].email",containsString("@"));
	}
}
