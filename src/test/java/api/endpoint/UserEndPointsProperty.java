package api.endpoint;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsProperty {
	static ResourceBundle getURL() {
		return ResourceBundle.getBundle("routes");
	}

	public static Response createUser(User payload) {
		String post_url = getURL().getString("post_url");
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).log().all().when()
				.post(post_url);
	}

	public static Response readUser(String username) {
		String get_url = getURL().getString("get_url");
		return given().pathParam("username", username).when().get(get_url);
	}

	public static Response updateUser(String username, User payload) {
		String update_url = getURL().getString("update_url");
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", username)
				.body(payload).log().all().when().put(update_url);
	}

	public static Response deleteUser(String username) {
		String delete_url = getURL().getString("delete_url");
		return given().pathParam("username", username).log().all().when().delete(delete_url);
	}
}
