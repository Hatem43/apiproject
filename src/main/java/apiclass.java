import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class apiclass {


    public Response getLocation(String country,String postalCode){

        return given()
                .baseUri("https://api.zippopotam.us")
                .pathParam("country", country)
                .pathParam("postalCode", postalCode)
                .when()
                .get("/{country}/{postalCode}")
                .then()
                .extract()
                .response();
    }

}
