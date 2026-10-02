package api.UserList;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import static io.restassured.RestAssured.given;

class UserListTest extends BaseApiTest {

	/**
	 * Testing page 2 query.
	 */
	@Test
	void getUserListFromSecondPage() {
		given()
				.log().ifValidationFails()
				.when()
				.get("/users/records?page=2")
				.then()
				.statusCode(200)
				.log().ifValidationFails()
				.body("$", notNullValue())
				.body("data.size()", greaterThan(0));
	}

	/**
	 * Testing check user list page count and per page parameters.
	 */
	@Test
	void checkFullListOfUsers() {
		given()
				.when()
				.get("/users/records")
				.then()
				.statusCode(200)
				.body("page", equalTo(1))
				.body("per_page", greaterThan(0))
				.time(lessThan(3000L));
	}

	/**
	 * Testing avatar availability for every user.
	 */
	@Test
	void everyUsersHaveAvatar() {
		given()
				.when()
				.get("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("data", everyItem(hasKey("avatar")));
	}

	/**
	 * Testing email formats for every user.
	 */
	@Test
	void everyUsersHasValidEmailAddress() {
		given()
				.when()
				.get("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("data.email", everyItem(containsString("@")));
	}

	/**
	 * Testing first user first name existence.
	 */
	@Test
	void checkOneUserFirstName() {
		given()
				.when()
				.get("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("data[0].first_name", notNullValue());
	}

	/**
	 * Testing last user email and first name.
	 */
	@Test
	void checkLastUserEmail() {
		given()
				.when()
				.get("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("data[-1].first_name", notNullValue())
				.body("data[-1].email", containsString("@"));
	}

	/**
	 * Testing list contains emails.
	 */
	@Test
	void checkOneEmailAddressIsContain() {
		given()
				.when()
				.get("/users/records")
				.then()
				.statusCode(200)
				.log().ifValidationFails()
				.body("data.email", not(empty()));
	}

	/**
	 * Testing id existence for every item.
	 */
	@Test
	void checkEveryItemHasLegalId() {
		given()
				.when()
				.get("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("data.id", everyItem(notNullValue()));
	}

	/**
	 * Testing negative match for first name.
	 */
	@Test
	void checkSpecificFirstNameInTheList() {
		given()
				.when()
				.get("/users/records")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("data.first_name", not(hasItem("JaneDoeNonExistent")));
	}
}