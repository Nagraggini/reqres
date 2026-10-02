package api.deleteUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

class UsersDeleteTest extends BaseApiTest {

	/**
	 * Felhasználó törlésének ellenőrzése.
	 *
	 * A teszt elküld egy DELETE kérést a megadott felhasználó azonosítójára,
	 * majd ellenőrzi, hogy a szerver 204 No Content státuszkóddal válaszol.
	 * Végül ellenőrzi, hogy a válasz törzse üres.
	 */
	@Test
	@Disabled // Lejárt kulccsal nem lehet törölni.
	void deleteUser() {
		String id = "e97f94cb-c1c8-463b-9e08-58144433a7da";

		Response response = given()
				.log().all()
				.when()
				.delete("/users/records/" + id)
				.then()
				.log().all()
				.statusCode(204)
				.extract()
				.response();

		assertEquals("", response.asString());
	}

}