package api.postUser;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import io.restassured.response.Response;
import model.User;

class UserCreationPozitiveTest extends BaseApiTest{

	/**
	 * Új felhasználó létrehozása POST kéréssel.
	 *
	 * Ellenőrzi, hogy:
	 * - a státuszkód 201,
	 * - a válasz tartalmaz id mezőt,
	 * - a first_name és email mezők értéke megegyezik a küldött adatokkal.
	 */
	@Test
	void createUserTest() {
		String requestBody="""
				{
					"first_name":"Elek",
					"last_name":"Teszt",
					"email":"tesztelek@teszt.com"
				}
				""";
		
		given().body(requestBody)
		.when().post("api/users")
		.then().log().ifValidationFails()
		// Nincs köztes réteg, nem kell a "data."
			.statusCode(201).body("id",notNullValue())
			.body("first_name", equalTo("Elek"))
			.body("email",equalTo("tesztelek@teszt.com"));
	}
	
	/**
	 * Új felhasználó létrehozása POST kéréssel.
	 *
	 * Az id mező értékét extract().path() segítségével olvassa ki,
	 * majd kiírja a konzolra.
	 */
	@Test
	void createUserTestWithExtractId() {
		String requestBody="""
				{
					"first_name":"Elek",
					"last_name":"Teszt",
					"email":"tesztelek@teszt.com"
				}
				""";
		
		String newId=given().body(requestBody)
		.when().post("api/users")
		.then().log().ifValidationFails()
		// Nincs köztes réteg, nem kell a "data."
			.statusCode(201).body("id",notNullValue())
			.body("first_name", equalTo("Elek"))
			.body("email",equalTo("tesztelek@teszt.com")).extract().path("id");
		System.out.println("New id: "+newId);
	}
	
	/**
	 * Új felhasználó létrehozása POST kéréssel.
	 *
	 * A teljes választ Response objektumba menti,
	 * majd a válasz mezőit és a státuszkódot ellenőrzi.
	 */
	@Test
	void createUserTestWithResponseObject() {
		String requestBody="""
				{
					"first_name":"Elek",
					"last_name":"Teszt",
					"email":"tesztelek@teszt.com"
				}
				""";
		
		Response responseObj=given().body(requestBody)
		.when().post("api/users")
		.then().log().ifValidationFails()
		// Nincs köztes réteg, nem kell a "data."
			.statusCode(201).body("id",notNullValue())
			.body("first_name", equalTo("Elek"))
			.body("email",equalTo("tesztelek@teszt.com")).extract().response();
		
		System.out.println("New id: "+responseObj.path("id")+"\nLast name: "
		+responseObj.path("last_name"));
		
		Integer statusCode=201;
		assertTrue(statusCode.equals(responseObj.statusCode()));
		assertEquals("Elek",responseObj.path("first_name"));
	}
	
	/**
	 * Új felhasználó létrehozása POJO használatával.
	 *
	 * A kérés törzsét User objektumból állítja össze,
	 * majd az új felhasználó azonosítóját extract().path() segítségével olvassa ki.
	 */
	@Test
	void createUserTestWithPOJO() {
		User user=new User();
		
		user.setFirst_name("Elek");
		user.setLast_name("Teszt");
		user.setEmail("tesztelek@teszt.com");
		
		String newId=
			given().body(user).when().post("api/users")
				.then().log().ifValidationFails()
				.statusCode(201).extract().path("id");
	
		System.out.println("New id: "+newId);
		}
	
	/**
	 * Új felhasználó létrehozása POJO használatával.
	 *
	 * A választ teljes egészében User objektummá alakítja,
	 * majd kiolvassa a létrehozott felhasználó azonosítóját.
	 */
	@Test
	void createUserTestWithFullPOJO() {
		User user=new User();
		
		user.setFirst_name("Elek");
		user.setLast_name("Teszt");
		user.setEmail("tesztelek@teszt.com");
		
		user=
			given().body(user).when().post("api/users")
				.then().log().ifValidationFails()
				.statusCode(201).extract().as(User.class);
	
		System.out.println("New id: "+user.getId());
		}
}












