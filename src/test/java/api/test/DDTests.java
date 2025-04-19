package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.utilities.DataProviders;
import api.endpoint.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class DDTests {
	Faker faker;
	
	    @Test(priority = 1,dataProvider="Data",dataProviderClass=DataProviders.class)
	    public void testPostUser(String userId, String userName,String fname,String lname,String uersemail, String pwd,String ph) {
	    
	    	User userPayload=new User();
	    	faker=new Faker();
	        try {
	            userPayload.setId(Integer.parseInt(userId));
	        } catch (NumberFormatException e) {
	            userPayload.setId(faker.idNumber().hashCode());
	        }
	    	userPayload.setUsername(userName);
	    	userPayload.setFirstName(fname);
	    	userPayload.setLastname(lname);
	    	userPayload.setEmail(uersemail);
	    	userPayload.setPassword(pwd);
	    	userPayload.setPhone(ph);    	
	    	Response response=UserEndPoints.createUser(userPayload);
	    	response.then().log().all();	
	    	Assert.assertEquals(response.getStatusCode(), 200);
	    }

}
