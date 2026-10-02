package api.postUser;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;

class UserCreationPositiveTest extends BaseApiTest {

	/**
	 * Új felhasználó létrehozása POST kéréssel.
	 *
	 * Ellenőrzi, hogy:
	 * - a státuszkód 201,
	 * - a válasz data objektuma tartalmaz id mezőt,
	 * - a first_name és email mezők értéke megegyezik a küldött adatokkal.
	 */
	@Test
	void createUserTest() {
		String requestBody = """
				{
				    "first_name": "Elek",
				    "last_name": "Teszt",
				    "email": "tesztelek@teszt.com"
				}
				""";

		given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(201)
				.body("data.id", notNullValue())
				.body("data.first_name", equalTo("Elek"))
				.body("data.email", equalTo("tesztelek@teszt.com"));
	}

	/**
	 * Új felhasználó létrehozása POST kéréssel.
	 *
	 * Az id mező értékét extract().path() segítségével olvassa ki a data
	 * objektumból,
	 * majd kiírja a konzolra.
	 */
	@Test
	void createUserTestWithExtractId() {
		String requestBody = """
				{
				    "first_name": "Elek",
				    "last_name": "Teszt",
				    "email": "tesztelek@teszt.com"
				}
				""";

		Object newId = given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(201)
				.body("data.id", notNullValue())
				.body("data.first_name", equalTo("Elek"))
				.body("data.email", equalTo("tesztelek@teszt.com"))
				.extract().path("data.id");

		System.out.println("New id: " + newId);
	}

	/**
	 * Új felhasználó létrehozása POST kéréssel.
	 *
	 * A teljes választ Response objektumba menti,
	 * majd a válasz mezőit és a státuszkódot ellenőrzi.
	 */
	@Test
	void createUserTestWithResponseObject() {
		String requestBody = """
				{
				    "first_name": "Elek",
				    "last_name": "Teszt",
				    "email": "tesztelek@teszt.com"
				}
				""";

		Response responseObj = given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(201)
				.body("data.id", notNullValue())
				.body("data.first_name", equalTo("Elek"))
				.body("data.email", equalTo("tesztelek@teszt.com"))
				.extract().response();

		System.out.println("New id: " + responseObj.path("data.id") + "\nLast name: "
				+ responseObj.path("data.last_name"));

		assertEquals(201, responseObj.statusCode());
		assertEquals("Elek", responseObj.path("data.first_name"));
	}

	/**
	 * Új felhasználó létrehozása POJO használatával.
	 *
	 * A kérés törzsét User objektumból állítja össze,
	 * majd az új felhasználó azonosítóját extract().path("data.id") segítségével
	 * olvassa ki.
	 */
	@Test
	void createUserTestWithPOJO() {
		User user = new User();
		user.setFirst_name("Elek");
		user.setLast_name("Teszt");
		user.setEmail("tesztelek@teszt.com");

		Object newId = given()
				.contentType(ContentType.JSON)
				.body(user)
				.when()
				.post("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(201)
				.extract().path("data.id");

		System.out.println("New id: " + newId);
	}

	/**
	 * Új felhasználó létrehozása POJO használatával.
	 *
	 * A válasz data objektumát alakítja User objektummá
	 * (jsonPath().getObject("data", User.class)),
	 * majd kiolvassa a létrehozott felhasználó azonosítóját.
	 */
	@Test
	void createUserTestWithFullPOJO() {
		User user = new User();
		user.setFirst_name("Elek");
		user.setLast_name("Teszt");
		user.setEmail("tesztelek@teszt.com");

		user = given()
				.contentType(ContentType.JSON)
				.body(user)
				.when()
				.post("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(201)
				.extract()
				.jsonPath()
				.getObject("data", User.class);

		System.out.println("New id: " + user.getId());
	}
}