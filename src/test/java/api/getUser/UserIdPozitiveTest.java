package api.getUser;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import static io.restassured.RestAssured.given;
import model.User;

class UserIdPozitiveTest extends BaseApiTest {

	/**
	 * Létező felhasználó adatainak lekérése.
	 *
	 * Ellenőrzi, hogy:
	 * - a válasz státuszkódja 200,
	 * - a válasz nem üres,
	 * - a külső mezők (id, created_at) és a belső data objektum megtalálható.
	 */
	@Test
	void getUsersListFirstUserTest() {
		given()
				.when()
				.get("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("$", notNullValue())
				.body("data[0]", hasKey("id"))
				.body("data[0]", hasKey("data"));
	}

	/**
	 * Felhasználók listájának lekérése.
	 *
	 * Ellenőrzi, hogy:
	 * - a válasz státuszkódja 200,
	 * - a lista nem üres,
	 * - az első rekord belső data objektuma tartalmaz mezőket.
	 */
	@Test
	void getUsersListSecondPageTest() {
		given()
				.when()
				.get("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("$", notNullValue())
				.body("data[0].id", notNullValue())
				.body("data[0].data", notNullValue());
	}

	/**
	 * Létező felhasználó lekérése POJO használatával.
	 *
	 * A válasz data[0].data objektumát alakítja át a User modjellé.
	 */
	@Test
	void getUserByIdWithPOJOTest() {
		// A belső data objektumot deszerializáljuk a User POJO-ba
		User user = given()
				.when()
				.get("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.extract()
				.jsonPath()
				.getObject("data[0].data", User.class);

		assertNotNull(user);
	}
}