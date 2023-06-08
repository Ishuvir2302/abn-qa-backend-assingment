package stepdefinitions;

import org.apache.log4j.Logger;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataprovider.configReader;
import helper.loggerHelper;
import utilities.responseUtility;

public class updateStepDefs extends configReader {
    private static Logger log = loggerHelper.getLogger(updateStepDefs.class);

    responseUtility res = new responseUtility();
    String statusLineCode = "HTTP/1.1 401 Unauthorized";

    int issueValue;

    @Given("^I created the put request with updated \"([^\"]*)\"$")
    public void i_created_the_put_request_with_updated(String value) throws Throwable {
        res.issueUpdateParam(getBaseUrl(), getToken(), value);
    }

    @Given("^I created the put request with invalid token")
    public void i_created_the_put_request_with_updated() throws Throwable {
        res.projectRequest(getBaseUrl(), getInvalidToken());
    }

    @Given("^I created issue for deletion in '(\\d+)'$")
    public void i_created_issue_for_deletion_in(int Project) {
        res.issueRequestParam(getBaseUrl(), getToken());
        String route = projectApiRoute() + "/" + Project + issueApiroute();
        res.sendRequest(responseUtility.POST_REQUEST, route);
        issueValue = res.getMovedIssueID();
        log.info("Issue is created with iid:" + issueValue);

    }

    @When("^I sent the delete request with issue in '(\\d+)'$")
    public void i_sent_the_delete_request_with_issue_in(int project) throws Throwable {
        log.info("Issue to be deleted:" + issueValue);
        res.projectRequest(getBaseUrl(), getToken());
        res.sendRequest(responseUtility.DELETE_REQUEST,
                projectApiRoute() + "/" + project + issueApiroute() + "/" + issueValue);
    }

    @When("^I sent the invalid delete request with issue in '(\\d+)'$")
    public void i_sent_the_invalid_delete_request_with_issue_in(int project) throws Throwable {
        log.info("Issue to be deleted:" + issueValue);
        res.projectRequest(getBaseUrl(), getInvalidToken());
        res.sendRequest(responseUtility.DELETE_REQUEST,
                projectApiRoute() + "/" + project + issueApiroute() + "/" + issueValue);
    }

    @Then("^I verified the delete status code as '(\\d+)'$")
    public void i_verified_the_delete_status_code_in(int code) throws Throwable {
        Assert.assertEquals(code, res.getStatusCode());
        if (code == 401) {
            log.info(res.getBody());
            log.info("Unauthorised access");
        } else {
            log.info("Issue is deleted ");
        }
    }

    @When("^I updated the (\\d+) in (\\d+)$")
    public void i_updated_the_in(int issue, int project) {
        log.info("route:  " + projectApiRoute() + "/" + project + issueApiroute() + "/" + issue);
        res.sendRequest(responseUtility.PUT_REQUEST, projectApiRoute() + "/" + project + issueApiroute() + "/" + issue);
    }

    @When("^I moved the (\\d+) in (\\d+)$")
    public void i_moved_the_in(int issue, int project) {
        log.info("route:  " + projectApiRoute() + "/" + project + issueApiroute() + "/" + issue + "/reorder");
        res.sendRequest(responseUtility.PUT_REQUEST,
                projectApiRoute() + "/" + project + issueApiroute() + "/" + issue + "/reorder");
    }

    @Then("^I verified response Status_as '(\\d+)'$")
    public void i_verified_response_Status_as(int code) {
        int statuscode = res.getStatusCode();
        Assert.assertEquals(code, statuscode);
        log.info(" response code " + statuscode);
        log.info(" response code " + res.getBody());
    }

    @Then("^(\\d+) is updated with \"([^\"]*)\"$")
    public void is_updated_with(int issue, String value) {
        Assert.assertEquals(value, res.getTitle(value));
        log.info("Issue is update with :  " + res.getTitle(value));
    }

    @Then("^I verified the delete status code$")
    public void i_verified_the_delete_status_code() throws Throwable {

    }

    @When("^I sent the request with issue$")
    public void i_sent_the_request_with_issue() {
        res.sendRequest(responseUtility.DELETE_REQUEST, issueApiroute());
    }

    @Then("^I verified the deleted issue from Get request in '(\\d+)'$")
    public void i_verified_the_deleted_issue_from_Get_request_in(int projectNum) throws Throwable {
        res.getRequestParam(getBaseUrl(), getToken());
        res.sendRequest(responseUtility.GET_REQUEST,
                projectApiRoute() + "/" + projectNum + issueApiroute() + "/" + issueValue);
        Assert.assertEquals(404, res.getStatusCode());
        log.info("Issue is not found");
    }

    @Then("^I verified status line code$")
    public void i_verified_status_line_code() {
        Assert.assertEquals(statusLineCode, res.getStatusLineCode());
    }

    @Then("^I validate the response code is '(\\d+)'$")
    public void i_validate_the_response_status_is(int codeS) {
        int statuscode = res.getStatusCode();
        Assert.assertEquals(codeS, statuscode);

        log.info(" response code " + statuscode);
    }
}
