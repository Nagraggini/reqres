package api.postUser;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import model.User;

class UserCreationNegativeTest extends BaseApiTest{

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
	void userCreationWithMissingEmailTest() {
		User user=new User();
		user.setFirst_name("Jane");
		user.setLast_name("Doe");
		
		user=given().body(user).when().post("api/users").then().log().ifValidationFails()
		.statusCode(201).body("email",nullValue())
		.body("$", not(hasKey("address"))).extract().as(User.class);
		
		System.out.println("New id: "+user.getId());
	}

}
