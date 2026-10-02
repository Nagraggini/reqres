package api.postUser;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import static io.restassured.RestAssured.given;
import model.User;

class UserCreationNegativeTest extends BaseApiTest {

	/**
	 * Felhasználó létrehozása hiányos adatokkal.
	 *
	 * A kérés nem tartalmaz e-mail címet.
	 *
	 * Ellenőrzések:
	 * - A válasz státuszkódja 201.
	 * - Az email mező értéke null.
	 * - A válasz nem tartalmaz address mezőt.
	 *
	 * A létrehozott felhasználó azonosítója kiírásra kerül a konzolra.
	 */
	@Test
	@Disabled // Lejárt kulccsal nem lehet CRUD műveleteket csinálni.
	void userCreationWithMissingEmailTest() {
		User user = new User();
		user.setFirst_name("Jane");
		user.setLast_name("Doe");

		user = given()
				.body(user)
				.when()
				.post("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(201)
				.body("data.email", nullValue())
				.body("data", not(hasKey("address")))
				.extract()
				.jsonPath()
				.getObject("data", User.class);

		System.out.println("New id: " + user.getId());
	}

}