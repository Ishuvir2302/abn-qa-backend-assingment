package utilities;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class requestUtility {
    RequestSpecification request;
    JSONObject requestParams;

    public Response response;

    public RequestSpecification baseRequest(String baseURIL) {
        RestAssured.baseURI = baseURIL;
        return request = RestAssured.given();
    }

    public void issueRequestParam(String baseURIL, String token) {
        bodyHeader(baseURIL, token);
        String requestBody = "{ \"title\": \"New Issue\", \"description\": \"This is a new issue created via IssueAPI\", \"labels\": [\"bug\"] }";
        request.body(requestBody);

    }

    public void issueUpdateParam(String baseURIL, String token, String value) {
        bodyHeader(baseURIL, token);
        if (value.contains("-")) {
            request.body("{\"due_date\": \"" + value + "\"}");
        } else {
            request.body("{\"title\": \"" + value + "\"}");
        }
    }

    public void projectRequest(String baseURIL, String token) {
        baseRequest(baseURIL);
        requestOath2Token(token);
    }

    public void issueRequestParam(String baseURIL, String token, int destprojectID) {

        bodyHeader(baseURIL, token);
        String requestBody = "{\"to_project_id\": " + destprojectID + "}";
        System.out.println(requestBody);
        request.body(requestBody);

    }

    public void bodyHeader(String baseURIL, String token)

    {
        baseRequest(baseURIL);
        request.header("Private-Token", token);
        request.header("Content-Type", "application/json");
    }

    public void getRequestParam(String baseURIL, String token) {
        baseRequest(baseURIL);
        requestOath2Token(token);

    }

    public void requestOath2Token(String tokenValue) {

        request.header("Authorization", "Bearer " + tokenValue);
    }

    public void requestbodyupdate(String sampleText, String Key) {
        requestParams.put(Key, sampleText);
    }
}
