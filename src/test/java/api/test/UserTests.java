package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void setUpData() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setUsername(faker.name().username());
    }

    @Test(priority = 1)
    public void testPostUser() {
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGetUserByName() {
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("username"), userPayload.getUsername());
    }

    @Test(priority = 3)
    public void testUpdateUser() {
        // Modify some fields
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200);

        // Verify update
        Response updatedResponse = UserEndPoints.readUser(userPayload.getUsername());
        Assert.assertEquals(updatedResponse.getStatusCode(), 200);
        Assert.assertEquals(updatedResponse.jsonPath().getString("firstName"), userPayload.getFirstName());
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
