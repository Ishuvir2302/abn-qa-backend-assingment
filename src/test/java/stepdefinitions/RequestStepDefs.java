package stepdefinitions;

import org.apache.log4j.Logger;
import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataprovider.configReader;
import helper.loggerHelper;
import utilities.responseUtility;

public class RequestStepDefs extends configReader {

    private static Logger log = loggerHelper.getLogger(RequestStepDefs.class);

    responseUtility res = new responseUtility();
    String statusLineCode = "HTTP/1.1 200 OK";
    String errorStatusLine = "HTTP/1.1 401 Unauthorized";

    @Given("^I created the GET request with Invalid token$")
    public void i_created_the_GET_request_with_Invalid_token() {
        res.getRequestParam(getBaseUrl(), getInvalidToken());

    }

    @Then("^I validate the response status is '(\\d+)'$")
    public void i_validate_the_response_status_is(int codeS) {
        int statuscode = res.getStatusCode();
        Assert.assertEquals(codeS, statuscode);
        log.info(" response code  " + statuscode);
        log.info(" response code " + res.getBody());

    }

    /*
     * Verifying if response shows type as 'ISSUE'
     */
    @And("^I verify the issue type in response$")
    public void I_verify_the_issue_type_in_response() {
        String type = res.getIssueType();
        log.info("Type present is response: " + type);
        Assert.assertEquals("ISSUE", type);

    }

    @And("^I verify the issue count in response$")
    public void I_verify_the_issue_count_in_response() {
        String issueCount = res.getCount();
        log.info("Number of issue recieved : " + issueCount);
    }

    @Then("^I validate the Status line code$")
    public void i_validate_the_Status_line_code() {
        String StatusLine = res.getStatusLineCode();
        log.info("Status line code: " + StatusLine);
        Assert.assertEquals(statusLineCode, StatusLine);
    }

    @Then("^I validate the error Status line code$")
    public void i_validate_the_error_Status_line_code() {
        String StatusLine = res.getStatusLineCode();
        log.info("Status line code: " + StatusLine);
        Assert.assertEquals(errorStatusLine, StatusLine);
    }

    @Given("^I created the GET request with Valid token$")
    public void i_created_the_GET_request_with_Valid_token() {
        res.getRequestParam(getBaseUrl(), getToken());

    }

    @When("^I sent the request with issue route$")
    public void i_sent_the_request_with_issue_route() {
        res.sendRequest(responseUtility.GET_REQUEST, issueApiroute());
    }

    @When("^I sent the request with group route$")
    public void i_sent_the_request_with_group_route() throws Throwable {
        res.sendRequest(responseUtility.GET_REQUEST, groupRoute());
    }

    @Then("^I validate the header count$")
    public void i_validate_the_header_count() throws Throwable {
        log.info("Header count" + res.headerCount());
        Assert.assertEquals(36, res.headerCount());
    }

    @Then("^I logged all headers$")
    public void i_logged_all_headers() throws Throwable {
        res.logHeaders();
    }

    @Then("^I validated the Content-Type header response$")
    public void i_validated_the_Content_Type_header_response() throws Throwable {
        Assert.assertEquals("application/json", res.getContentTypeHeader());
        log.info("contenttype header value: " + res.getContentTypeHeader());
    }

    @When("^I sent the request with \"([^\"]*)\"$")
    public void i_sent_the_request_with(String projectNum) {
        res.sendRequest(responseUtility.GET_REQUEST, projectApiRoute() + "/" + projectNum);
    }

    @Then("^I verified the response body contains \"([^\"]*)\"$")
    public void i_verified_the_response_body_contains(String projectNumber) {
        log.info("Project id in Response:  " + res.getProjectByID());
        Assert.assertEquals(projectNumber, res.getProjectByID());
    }

    @Then("^I verified the response body$")
    public void i_verified_the_response_body() {
        log.info(res.getBody());

    }

    @When("^I sent the request with \"([^\"]*)\" & \"([^\"]*)\"$")
    public void i_sent_the_request_with(String projectNumber, String issueNumber) {
        res.sendRequest(responseUtility.GET_REQUEST, projectApiRoute() + "/" + projectNumber + issueApiroute());
    }

    @Then("^I verified \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void i_verified_contains(String projectID, String issueID) {
        for (int issue : res.getIssueID()) {
            if (issue == Integer.parseInt(issueID)) {
                log.info("Issue " + issueID + " exist in " + "Project " + projectID);
            }
        }
    }

    @Then("^I validated the \"([^\"]*)\" name$")
    public void i_validated_the_author_name(String author) {
        Assert.assertEquals(author, res.getIssueAuthor());
    }

}
