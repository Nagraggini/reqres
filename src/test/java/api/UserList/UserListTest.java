package api.UserList;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.JSONAssertion;

class UserListTest extends BaseApiTest{

	/**
	 * Összes felhasználó lekérése a második oldalról és ellenőrizzük, hogy a válasz nem üres.
	 * Nagyobb, mint nulla a mérete.
	 * A data típusból 6 db van-e.
	 */
	@Test
	void getUserListFromSecondPage() {
		given().accept(ContentType.JSON).log().ifValidationFails()
		.get("/api/users?page=2")
		.then().statusCode(200).log().ifValidationFails()
		.body("$", notNullValue())
		.body("size()",greaterThan(0))
		.body("data.size()", equalTo(6));
	}
	
	/**
	 * Összes felhasználó lekérés és oldalszám meg pár adat ellenőrzése.
	 */
	@Test
	void checkFullListOfUsers() {
		given().accept(ContentType.JSON).when()
		.get("api/users").then().statusCode(200).body("page", equalTo(1))
		.body("per_page",greaterThan(5))
		.body("total", lessThan(13))
		.body("total_pages",instanceOf(Integer.class))
		.time(lessThan(3000L)); //3 másodpercnél gyorsabban jön a válasz.
	
	}
	
	/**
	 * Minden felhasználónak van-e avatarja.
	 */
	@Test
	void everyUsersHaveAvatar() {
		given().accept(ContentType.JSON).when().get("/api/users")
		.then().log().ifValidationFails().statusCode(200)
		.body("data", everyItem(hasKey("avatar")));		
	}
	
	/**
	 * Minden felhasználónak van valid email címe.
	 */
	@Test
	void everyUsersHasValidEmailAddress() {
		given().accept(ContentType.JSON).when().get("api/users")
		.then().log().ifValidationFails().statusCode(200)
		.body("data.email", everyItem(containsString("@")))
		.body("data.email",everyItem(endsWith(".in")) );
	}
	

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
	 * Ellenőrzése az utolsó felhasználó email mezőjénél, hogy van-e benne 
	 * @ jel és a keresztneve nem nulla.
	 */
	@Test
	void checkLastUserEmail() {
		given().accept(ContentType.JSON).when().get("/api/users")
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.body("data[5].first_name", notNullValue())
		.body("data[5].email",containsString("@"));
	}
	
	/**
	 * Leellenőrizzük, hogy egy adott email cím benne van-e a listában.
	 */
	@Test
	void checkOneEmailAddressIsContain() {
		given().accept(ContentType.JSON).when().get("/api/users").then()
		.statusCode(200).log().ifValidationFails()
		.body("data.email", hasItem("janet.weaver@reqres.in"));
	}
	
	
	/**
	 * Minden id nagyobb, mint 0 és nem üres az értékük.
	 */
	@Test
	void checkEveryItemHasLegalId() {
		given().accept(ContentType.JSON)
		.when().get("api/users").then().log().ifValidationFails()
		.statusCode(200).body("data.id", everyItem(allOf(greaterThan(0),notNullValue())));
	}
	
	
	/**
	 * Ellenőrizzük, hogy nem létezik-e Jane keresztnév a listában. 
	 */
	@Test
	void checkSpecificFirstNameInTheList() {
		given().accept(ContentType.JSON).when().get("api/users")
		.then().log().ifValidationFails().statusCode(200)
		.body("data.first_name", not(hasItem("Jane")));
	}

}














