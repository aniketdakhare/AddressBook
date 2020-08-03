package com.bridgelabz.addressbooktest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class AddressBookDetailsRESTAssure
{
    @Before
    public void setUp()
    {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4000;
    }

    public Response getAddressBookList()
    {
        Response response = RestAssured.get("/AddressBook/list");
        return response;
    }

    @Test
    public void onCallingList_ReturnAddressBookList()
    {
        Response response = getAddressBookList();
        System.out.println("AT FIRST: " + response.asString());
        response.then().body("id", Matchers.hasItems(1, 2));
        response.then().body("firstName", Matchers.hasItems("Aniket"));
    }

    @Test
    public void givenAddressBookDetails_OnPost_ShouldReturnAddedAddressBook()
    {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"firstName\": \"Snehal\", \"lastName\": \"Gunde\", \"address\": \"Shri Ram Nagar\", " +
                        "\"city\": \"Bhadrawati\", \"state\": \"Bihar\", \"zipCode\": \"878786\"," +
                        "\"phone\": \"87878987\"}")
                .when()
                .post("/AddressBook/create");
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("firstName", Matchers.is("Snehal"));
    }


    @Test
    public void givenAddressBookDetails_OnUpdate_ShouldReturnUpdatedAddressBook()
    {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"firstName\": \"Aniket\", \"lastName\": \"Dakhare\", \"address\": \"Nagpur\", " +
                        "\"city\": \"Gondia\", \"state\": \"Bihar\", \"zipCode\": \"457885\"," +
                        "\"phone\": \"9999999999\"}")
                .when()
                .put("/AddressBook/update/4");
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("firstName", Matchers.is("Aniket"));
    }

    @Test
    public void givenAddressBookId_OnDelete_ShouldReturnSuccessStatus()
    {
        Response response = RestAssured.delete("/AddressBook/delete/6");
        int statusCode = response.getStatusCode();
        MatcherAssert.assertThat(statusCode, CoreMatchers.is(200));
        response = getAddressBookList();
        System.out.println("AT END: " + response.asString());
        response.then().body("id", Matchers.not(Integer.class));
    }
}
