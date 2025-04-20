package api.test;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class OAuthTest {
	String clientId = "1PYQCrVEj5jPTpxSlZOlVYk9WBBcfjAD1msMDDLDuRYWPyvGMt";
	String clientSecret = "JiXw6IvqDIGUVZvnI1b7DA0MZbeUGs6DjaLyflBd";

	@Test
	public void getAnimalDetails() {
		// Step 1: Get Access Token
		Response tokenResponse = given().baseUri("https://api.petfinder.com").basePath("/v2/oauth2/token")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("grant_type", "client_credentials").formParam("client_id", clientId)
				.formParam("client_secret", clientSecret).when().post().then().statusCode(200).extract().response();
		System.out.println(tokenResponse.prettyPrint());
		// String accessToken = tokenResponse.path("access_token");
		String accessToken = tokenResponse.jsonPath().getString("access_token");
		System.out.println("Token is " + accessToken);
		// Step 2: Use token to fetch animal info
		given().baseUri("https://api.petfinder.com").basePath("/v2/animals/76093542")
				.header("Authorization", "Bearer " + accessToken).when().get().then().statusCode(200).log().all();

	}
}
