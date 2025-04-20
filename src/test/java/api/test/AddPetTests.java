package api.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import java.io.File;
import java.nio.file.Paths;

public class AddPetTests {
    Faker faker;
    String pet_id;

    @Test(priority=1)
    public void createPetWithDynamicData() throws Exception {
        faker = new Faker();
        String filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "pet.json").toString();

        // Load JSON template
        String jsonTemplate = api.utilities.JsonUtil.loadJson(filePath);
        String id = String.valueOf(System.currentTimeMillis() % 10000);
        pet_id = id;

        // Replace placeholder values
        String[][] replacements = {
            {"0", id},
            {"defaultCategory", faker.animal().name()},
            {"defaultName", faker.cat().name()},
            {"defaultUrl", "https://catphotos.com/" + faker.color().name()},
            {"defaultTag", faker.cat().breed()},
            {"defaultStatus", "available"}
        };

        String updatedJson = api.utilities.JsonUtil.updateJsonTemplate(jsonTemplate, replacements);

        // Send request with Rest Assured
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(updatedJson).log().all()
            .when()
                .post("/pet")
            .then()
                .statusCode(200);
    }
    
    @Test(priority=2)
    public void uploadImage() throws Exception {
    	File imageFilePath=new File(Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "pet.jpg").toString());
      
       Response response=  RestAssured. given()
                .multiPart("file",imageFilePath)
                .pathParam("petId",pet_id )
                .accept("application/json")
                .log().all()
                .when().post("/pet/{petId}/uploadImage");
       
    System.out.println("Response Body "+   response.asPrettyString());
                
                
        Assert.assertEquals(response.statusCode(),200);
             
    }
}
