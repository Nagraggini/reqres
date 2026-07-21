package api.getUser;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;

class UserIdNegativeTest extends BaseApiTest{

	/**
	 * Nem létező felhasználó lekérése.
	 *
	 * Ellenőrzi, hogy:
	 * - a válasz státuszkódja 404 vagy 401,
	 * - a válasz törzse üres objektum vagy null.
	 */
	@Test
	void getUserIdUnsuccessfulTest() {
		given().when().get("api/users/99").then().log().ifValidationFails()
		.statusCode(anyOf(is(404), is(401))).body("$", anyOf( anEmptyMap(),nullValue()));
	}
	
	/**
	 * Létező felhasználó azonosítójának ellenőrzése.
	 *
	 * Ellenőrzi, hogy:
	 * - a válasz státuszkódja 200,
	 * - a felhasználó azonosítója nem 3.
	 */
	@Test
	void checkUserIdTest() {
		given().when().get("api/users/2").then().log().ifValidationFails()
		.statusCode(200).body("data.id", not(equalTo(3)));
	}

}
