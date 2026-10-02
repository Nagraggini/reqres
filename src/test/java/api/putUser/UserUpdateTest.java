package api.putUser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import static io.restassured.RestAssured.given;
import model.User;

class UserUpdateTest extends BaseApiTest {

	private static final Logger LOG = LogManager.getLogger(UserUpdateTest.class);

	/**
	 * Update user with JSON string body.
	 */
	@Test
	@Disabled // Lejárt kulccsal nem lehet törölni.
	void updateUserTest() {
		String requestBody = """
				{
				    "first_name": "Jane",
				    "last_name": "Doe"
				}
				""";

		User user = given()
				.body(requestBody)
				.when()
				.put("/users/records/1")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.extract()
				.jsonPath()
				.getObject("data", User.class);

		assertNotNull(user);
		assertEquals("Jane", user.getFirst_name());
		assertEquals("Doe", user.getLast_name());
	}

	/**
	 * Update user with Log4j logging.
	 */
	@Test
	@Disabled // Lejárt kulccsal nem lehet CRUD műveleteket csinálni.
	void updateUserTestWithLOG() {
		LOG.info("updateUserTestWithLOG indítása");

		String requestBody = """
				{
				    "first_name": "Jane",
				    "last_name": "Doe"
				}
				""";

		String userID = "1";
		LOG.info("Módosítandó id: {}", userID);

		try {
			User user = given()
					.body(requestBody)
					.when()
					.put("/users/records/" + userID)
					.then()
					.log().ifValidationFails()
					.statusCode(200)
					.extract()
					.jsonPath()
					.getObject("data", User.class);

			assertEquals("Jane", user.getFirst_name());
			assertEquals("Doe", user.getLast_name());
			LOG.info("updateUserTestWithLOG sikeresen lefutott");

		} catch (Exception e) {
			LOG.error("Hiba történt a teszt futtatása közben: ", e);
			throw e;
		}
	}

	/**
	 * Update user with POJO mapping.
	 */
	@Test
	@Disabled // Lejárt kulccsal nem lehet CRUD műveleteket csinálni.
	void updateUserTestWithPOJO() {
		User user = new User();
		user.setFirst_name("Amanda");

		user = given()
				.body(user)
				.when()
				.put("/users/records/3")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.extract()
				.jsonPath()
				.getObject("data", User.class);

		assertNotNull(user);
		assertEquals("Amanda", user.getFirst_name());
	}
}