package api.UserList;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.JSONAssertion;

class UserList extends BaseApiTest{

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
	
	/**
	 * Leellenőrizzük, hogy egy adott email cím benne van-e a listában.
	 */


}
