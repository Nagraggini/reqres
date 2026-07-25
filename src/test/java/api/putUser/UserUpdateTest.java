package api.putUser;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.*;
import  io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import api.base.BaseApiTest;
import model.User;

class UserUpdateTest extends BaseApiTest{

	//import org.apache.logging.log4j.Logger; 
		//A végén lévő zárójelben magát az osztályt kell megadni. 
		private static final Logger LOG=LogManager.getLogger(UserUpdateTest.class);
		
	/**
	 * Ellenőrzi, hogy egy felhasználó adatai sikeresen frissíthetők
	 * JSON request body használatával.
	 *
	 * A teszt PUT kérést küld az /api/users/1 végpontra,
	 * majd ellenőrzi, hogy a válasz státuszkódja 200,
	 * valamint a first_name és last_name mezők a várt értékeket tartalmazzák.
	 */
	@Test
	void updateUserTest() {
	String responseBody="""	
			{		  
			   		"first_name": "Jane",
			  		"last_name": "Doe"	
			  		}		
				""";
	User user=given().body(responseBody).when().put("api/users/1")
	.then().log().ifValidationFails().statusCode(200).extract().jsonPath()
	.getObject("", User.class);
	
	assertNotNull(user);
	assertEquals("Jane",user.getFirst_name());
	assertEquals("Doe",user.getLast_name());
	}
	
	/**
	 * Ellenőrzi, hogy egy felhasználó adatai sikeresen frissíthetők
	 * JSON request body használatával.
	 *
	 * A teszt PUT kérést küld az /api/users/1 végpontra,
	 * majd ellenőrzi, hogy a válasz státuszkódja 200,
	 * valamint a first_name és last_name mezők a várt értékeket tartalmazzák.
	 * Loggolással.
	 */
	@Test
	void updateUserTestWithLOG() {
		LOG.info("updateUserTestWithLOG indítása, felhasználó módosítása");
		
	String responseBody="""	
			{		  
			   		"first_name": "Jane",
			  		"last_name": "Doe"	
			  		}		
				""";
	
	int userID=1;	
	LOG.info("Módosítandó id: {}",userID);
	
	//Kijelölöd a lenti blokkot. Jobb klikk -> Surround with -> Try-catch Block
		try {		
			
			User user=given().body(responseBody).when().put("api/users/"+userID)
			.then().log().ifValidationFails().statusCode(200).extract().jsonPath()
			.getObject("", User.class);
				
			assertEquals("Jane",user.getFirst_name());
			assertEquals("Doe",user.getLast_name());
			LOG.info("updateUserTestWithLOG sikeresen lefutott");
			
		} catch (Exception e) {
			LOG.error("Hiba történt a teszt futtatása közben: ",e);
			throw e;
		}
	}
	
	/**
	 * Ellenőrzi egy felhasználó frissítését POJO használatával.
	 *
	 * A teszt egy User objektumot küld a PUT kérés törzsében,
	 * majd ellenőrzi a válaszban szereplő first_name mezőt.
	 * Ezután GET kéréssel lekéri a felhasználó adatait,
	 * és ellenőrzi, hogy a last_name mező értéke "Wong".
	 *
	 * Megjegyzés: a ReqRes API nem menti el a módosításokat,
	 * ezért a GET kérés mindig az eredeti adatokat adja vissza.
	 */
	@Test
	void updateUserTestWithPOJO() {
		User user=new User();
		user.setFirst_name("Amanda");
		
		// Ilyen esetben a mezőköz üres "" .
	user=given().body(user).when().put("api/users/3").then().log().ifValidationFails()
		.statusCode(200).extract().jsonPath().getObject("", User.class);
	
	user.setLast_name(given().when().get("api/users/3").then().log()
			.ifValidationFails().statusCode(200).extract().path("data.last_name"));
	
	assertTrue(user.getLast_name().equals("Wong"));
	}
	
}
