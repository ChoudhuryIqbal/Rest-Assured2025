package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.UserEndPoints;
import api.endpoint.UserEndPointsProperty;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestsProperties {

    Faker faker;
    User userPayload;
    public Logger logger;

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
        //logger
        logger=LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {
    	logger.info("Creating User");
        Response response = UserEndPointsProperty.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User Created");
    }

    @Test(priority = 2)
    public void testGetUserByName() {
    	logger.info("Get User By Name");
        Response response = UserEndPointsProperty.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("username"), userPayload.getUsername());
   
    }

    @Test(priority = 3)
    public void testUpdateUser() {
    	logger.info("Updating User");
        // Modify some fields
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPointsProperty.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200);

        // Verify update
        Response updatedResponse = UserEndPointsProperty.readUser(userPayload.getUsername());
        Assert.assertEquals(updatedResponse.getStatusCode(), 200);
        Assert.assertEquals(updatedResponse.jsonPath().getString("firstName"), userPayload.getFirstName());
    }

    @Test(priority = 4)
    public void testDeleteUser() {
    	logger.info("Deleting User");
        Response response = UserEndPointsProperty.deleteUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
