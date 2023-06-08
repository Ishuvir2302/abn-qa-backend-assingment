package stepdefinitions;

import org.apache.log4j.Logger;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataprovider.configReader;
import helper.loggerHelper;
import utilities.responseUtility;

public class createStepDefs extends configReader {

    private static Logger log = loggerHelper.getLogger(createStepDefs.class);
    responseUtility res = new responseUtility();

    @Given("^I created the POST request with Valid token$")
    public void i_created_the_POST_request_with_Valid_token() {
        res.issueRequestParam(getBaseUrl(), getToken());
    }

    @Given("^I created the POST request with inValid token$")
    public void i_created_the_POST_request_with_inValid_token() {
        res.issueRequestParam(getBaseUrl(), getInvalidToken());
    }

    @Given("^I created the POST request with (\\d+)$")
    public void i_created_the_POST_request_with(int destinationProject) {
        res.issueRequestParam(getBaseUrl(), getInvalidToken(), destinationProject);
    }

    @When("^I created issue in \"([^\"]*)\"$")
    public void i_created_issue_in(String Project) {
        log.info("route:  " + projectApiRoute() + "/" + Project + issueApiroute());
        String route = projectApiRoute() + "/" + Project + issueApiroute();
        res.sendRequest(responseUtility.POST_REQUEST, route);
    }

    @Then("^Issue ID is generated in <\"([^\"]*)\">$")
    public void issue_ID_is_generated_in(String ProjectId) throws Throwable {
        log.info("Issue is created " + res.getGeneratedIssueID());

        Assert.assertEquals(ProjectId, res.getProjectID());
        log.info("Issue " + res.getGeneratedIssueID() + "::" + "generated in " + ProjectId);
    }

    @Then("^I validate the response status is(\\d+)$")
    public void user_validate_the_response_status_is(int httpCode) {
        int code = res.response.getStatusCode();
        Assert.assertEquals(httpCode, code);
        if (code == 200) {
            log.info("POST Request sucessfull PASS" + code);
        } else if (code == 401) {
            log.info("POST Request Failed: need to pass X-Master-Key in the header" + code);
        } else {
            log.info("BAD Request or Forbidden Request" + code);
        }

    }

    @Then("^I verified response Body throws \"([^\"]*)\"$")
    public void i_verified_response_Body_throws_Unauthorized(String invalidToken) {
        String body = res.getBody();
        try {
            if (body.contains(invalidToken)) {
                log.info("Unauthorised 401 issue");
            }
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @When("^I moved (\\d+) to (\\d+)$")
    public void i_moved_to(int IssueID, int projectID) throws Throwable {
        log.info("route:  " + projectApiRoute() + "/" + projectID + issueApiroute() + "/" + IssueID + "/move");
        res.sendRequest(responseUtility.POST_REQUEST,
                projectApiRoute() + "/" + projectID + issueApiroute() + "/" + IssueID + "/move");
    }

    @Then("^I verified (\\d+) existance in (\\d+)$")
    public void i_verified_existance_in(int IssueID, int projectID) throws Throwable {
        Assert.assertEquals(IssueID, res.getMovedIssueID());
        log.info("Issue is moved " + res.getMovedIssueID());
        Assert.assertEquals(projectID, res.getProjectID());
        log.info("Issue " + res.getMovedIssueID() + "::" + "Moved in " + projectID);

    }

    @Then("^I verified response Status_is '(\\d+)'$")
    public void i_verified_response_Status_is(int codeS) {
        int statuscode = res.getStatusCode();
        Assert.assertEquals(codeS, statuscode);
        log.info(" response code " + statuscode);
        log.info(" response code " + res.getBody());

    }

    @Then("^I verified subscribe Status$")
    public void i_verified_subscribe_Status() {
        int statuscode = res.getStatusCode();
        try {
            if (statuscode == 201) {
                log.info(" Subscribed/Unsubscribed to an issue " + statuscode);
                log.info(" response code " + res.getBody());
            } else if (statuscode == 304) {
                log.info(" Already Subscribed/Unsubscribed to an issue " + statuscode);
                log.info(" response code " + res.getBody());
            }
        } catch (Exception issue) {
            Assert.fail();
        }

    }

    @Given("^I created the POST request with Valid OATH(\\d+) token$")
    public void i_created_the_POST_request_with_Valid_OATH_token(int arg1) throws Throwable {
        res.projectRequest(getBaseUrl(), getToken());
    }

    @When("^I sent the to subscribe issue '(\\d+)' in project '(\\d+)'$")
    public void i_sent_the_to_subscribe_issue_in_project(int issueID, int projectID) throws Throwable {
        res.sendRequest(responseUtility.POST_REQUEST,
                projectApiRoute() + "/" + projectID + issueApiroute() + "/" + issueID + "/subscribe");
    }

    @When("^I sent the to unsubscribe issue '(\\d+)' in project '(\\d+)'$")
    public void i_sent_the_to_unsubscribe_issue_in_project(int issueID, int projectID) throws Throwable {
        res.sendRequest(responseUtility.POST_REQUEST,
                projectApiRoute() + "/" + projectID + issueApiroute() + "/" + issueID + "/unsubscribe");
    }

    @Then("^I validate the status line code$")
    public void i_validate_the_status_line_code() {

        try {
            if (res.getStatusLineCode().contains("201")) {
                log.info(" Subscribed/Unsubscribed to an issue " + res.getStatusLineCode());

            } else if (res.getStatusLineCode().contains("304")) {
                log.info(" Subscribed/Unsubscribed to an issue " + res.getStatusLineCode());

            }
        } catch (Exception issue) {
            Assert.fail();
        }
    }

    @Then("^I validate the subscribe status in response body$")
    public void i_validate_the_subscribe_status_in_response_body() throws Throwable {
        Assert.assertEquals(false, res.getSubscribeStatus());
        log.info(" Issue subscription status: " + res.getSubscribeStatus());
    }

}
