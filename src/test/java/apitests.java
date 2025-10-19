import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        int responsecode= resp.getStatusCode();
        System.out.println("the response status code is " +responsecode);
        String responsecotentype= resp.contentType();
        System.out.println("the response content type is " +responsecotentype);
        long responsetime =resp.timeIn(TimeUnit.MILLISECONDS);
        System.out.println("the response time is " +responsetime +" milliseconds");
        String responsebody=resp.getBody().asString();
        System.out.println("the response body is " +responsebody);
        Headers responseheaders= resp.getHeaders();
        List<Header> headers=responseheaders.asList();
        System.out.println("the headers are " + headers);



        Assert.assertEquals(responsecode,200);
        Assert.assertEquals(responsecotentype,"application/json");
        Assert.assertEquals(resp.jsonPath().getString("country"),"United States");
        Assert.assertEquals(resp.jsonPath().getString("places[0].longitude"),"-118.4065");
        Assert.assertEquals(resp.jsonPath().getString("places[0].latitude"),"34.0901");
        Assert.assertEquals(responseheaders.getValue("Connection"),"keep-alive");
        Assert.assertTrue(responsebody.contains("places"));

    }




/*
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

 */




}

