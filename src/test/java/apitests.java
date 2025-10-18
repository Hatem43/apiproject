import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class apitests {

    public apiclass api;


    @BeforeMethod
    public void setup(){
        api=new apiclass();

    }

    @Test(description = "valid country and valid postalcode")
    public void testcountryandpostalcode() {
        String country = "us";
        String postalCode = "90210";


        Response resp=api.getLocation(country,postalCode);
        Assert.assertEquals(resp.getStatusCode(),200);
        Assert.assertEquals(resp.contentType(),"application/json");
        Assert.assertEquals(resp.jsonPath().getString("country"),"United States");
        Assert.assertEquals(resp.jsonPath().getString("places[0].longitude"),"-118.4065");
    }




    @Test(description = "invalid country and valid postalcode")
    public void testinvalidcountryandvalidpostalcode() {
        String invalidcountry = "sss";
        String validpostalCode = "90210";

        Response resp=api.getLocation(invalidcountry,validpostalCode);
        Assert.assertTrue(resp.getStatusCode()==404 || resp.getStatusCode()==500);
    }

    @Test(description = "valid country invalid postalcode")
    public void testvalidcountryandinvalidpostalcode() {
        String invalidcountry = "us";
        String validpostalCode = "abcde";

        Response resp=api.getLocation(invalidcountry,validpostalCode);
        Assert.assertTrue(resp.getStatusCode()==404 || resp.getStatusCode()==500);
    }

    @Test(description = "invalid country invalid postalcode")
    public void testinvalidcountryandinvalidpostalcode() {
        String invalidcountry = "xxx";
        String validpostalCode = "00000";

        Response resp=api.getLocation(invalidcountry,validpostalCode);
        Assert.assertTrue(resp.getStatusCode()==404 || resp.getStatusCode()==500);
    }

}
