package api.getUser;

import api.base.*;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import model.User;

class UserIdPozitiveTest extends BaseApiTest {

	/**
	 * Létező felhasználó adatainak lekérése.
	 *
	 * Ellenőrzi, hogy:
	 * - a válasz státuszkódja 200,
	 * - a válasz nem üres,
	 * - az első felhasználó azonosítója 1,
	 * - a first_name és email mezők megtalálhatók.
	 */
	@Test
	void getUsersListFirstUserTest() {
		given().when().get("api/users").then().log().ifValidationFails().
		statusCode(200).body("$", notNullValue()).body("data[0].id", equalTo(1))
		.body("data[0]",hasKey("first_name"))
		.body("data[0]", hasKey("email"));
	}
	
	/**
	 * Felhasználók listájának lekérése.
	 *
	 * Ellenőrzi, hogy:
	 * - a válasz státuszkódja 200,
	 * - a lista nem üres,
	 * - az első felhasználó adatai megfelelnek a várt értékeknek,
	 * - az e-mail cím és az avatar formátuma helyes.
	 */
	@Test
	void getUsersListSecondPageTest() {
		given().when().get("api/users?page=2").then().log().ifValidationFails().statusCode(200)
		.body("$", notNullValue())
		.body("data[0].id", equalTo(7))
		.body("data[0].email", containsString("@reqres.in"))
		.body("data[0].avatar",startsWith("https"))
		.body("data[0].first_name", notNullValue());
	}

	/**
	 * Létező felhasználó lekérése POJO használatával.
	 *
	 * A válasz data objektumát User objektummá alakítja,
	 * majd ellenőrzi az azonosítót, az e-mail címet
	 * és a keresztnevet.
	 */
	@Test
	void getUserByIdWithPOJOTest() {
		User user=given().when().get("api/users/2").then()
				.log().ifValidationFails().statusCode(200)
				//.jsonPath().getObject("mezőköz",User.class)
				//Vagy, ha nincs mezőköz akkor elég a .as(Pet.class)
				.extract().jsonPath().getObject("data",User.class);
		
		assertEquals(2,user.getId());
		assertTrue(user.getEmail().contains("@"));
		assertEquals("Janet", user.getFirst_name());
	}
}











