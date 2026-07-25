package api.deleteUser;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;

class UsersDeleteTest extends BaseApiTest{

	/**
     * Felhasználó törlésének ellenőrzése.
     *
     * A teszt elküld egy DELETE kérést a megadott felhasználó azonosítójára,
     * majd ellenőrzi, hogy a szerver 204 No Content státuszkóddal válaszol.
     * Végül ellenőrzi, hogy a válasz törzse üres.
     */
	@Test
	void deleteUser() {
		String id = "8";
	
		Response response=given().when().delete("api/users/"+id)
		.then().log().ifValidationFails().statusCode(204)
		//.body("$",anyOf(anEmptyMap(),nullValue(),emptyOrNullString()))
		.extract().response();
		
		assertEquals("", response.asString());	
	
	}

}
