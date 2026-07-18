package api.UserList;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import io.restassured.http.ContentType;

class UserList extends BaseApiTest{

	/**
	 * Összes felhazsnáló lekérése.
	 */
	@Test
	void getUserList() {
		given().accept(ContentType.JSON).log().ifValidationFails().get("/api/users?page=2")
		.then().statusCode(200).log().ifValidationFails();
	}

}
