package api.getUser;

import api.base.*;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

class UserIdPozitiveTest extends BaseApiTest {

	/**
	 * Előfeltétel:
	 * Létező felhasználó (id = 1)
	 *
	 * Lépések:
	 * - Küldj GET kérést a /users/1 végpontra.
	 *
	 * Elvárt eredmény:
	 * - HTTP státuszkód: 200
	 * - A válasz nem null értékű.
	 * - A válasz tartalmazza:
	 *   - id = 1
	 *   - name mezőt
	 *   - email mezőt
	 */
	@Test
	void userIdSuccessEasyWayTest() {
		given().when().get("api/users").then().log().ifValidationFails().
		statusCode(200).body("$", notNullValue()).body("data[0].id", equalTo(1))
		.body("data[0]",hasKey("first_name"))
		.body("data[0]", hasKey("email"));
	}
	
	/**
	 * Előfeltétel:
	 * Létező felhasználók listája.
	 *
	 * Lépések:
	 * Küldj GET kérést a /api/users?page=2 végpontra.
	 *
	 * Elvárt eredmény:
	 * - HTTP 200
	 * - A data lista nem üres
	 * - Az első felhasználó:
	 *      - id = 7
	 *      - email tartalmazza a "@reqres.in" szöveget
	 *      - avatar HTTPS-sel kezdődik
	 *      - first_name nem üres
	 */
	@Test
	void userIdSuccessHarderWayTest() {
		given().when().get("/api/users?page=2").then().log().ifValidationFails().statusCode(200)
		.body("$", notNullValue())
		.body("data[0].id", equalTo(7))
		.body("data[0].email", containsString("@reqres.in"))
		.body("data[0].avatar",startsWith("https"))
		.body("data[0].first_name", notNullValue());
	}

}











