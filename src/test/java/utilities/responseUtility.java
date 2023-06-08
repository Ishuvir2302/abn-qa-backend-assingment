package utilities;

import java.util.List;

import org.apache.log4j.Logger;

import helper.loggerHelper;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class responseUtility extends requestUtility {

    private static Logger log = loggerHelper.getLogger(responseUtility.class);

    public static final int GET_REQUEST = 0;
    public static final int POST_REQUEST = 1;
    public static final int DELETE_REQUEST = 2;
    public static final int PUT_REQUEST = 3;
    public Response response;
    public JsonPath jsonPathEvaluator;
    public Headers allHeaders;

    public int getStatusCode() {
        return response.getStatusCode();

    }

    public String getBody() {
        return response.body().asString();

    }

    public String getIssueType() {
        jsonEvua();
        List<String> allIssueType = jsonPathEvaluator.getList("type");
        String issueType = allIssueType.get(0);
        log.info(issueType);
        return issueType;
    }

    public String getIssuetitle() {
        jsonEvua();
        List<String> allIssueTitle = jsonPathEvaluator.getList("title");
        String issueTitle = allIssueTitle.get(0);
        log.info(issueTitle);
        return issueTitle;
    }

    public List<Integer> getIssueID() {
        jsonEvua();
        List<Integer> allIssueType = jsonPathEvaluator.getList("id");
        log.info(allIssueType);
        return allIssueType;
    }

    public int getGeneratedIssueID() {
        jsonEvua();
        int IssueId = jsonPathEvaluator.get("id");
        log.info(IssueId);
        return IssueId;
    }

    public int getMovedIssueID() {
        jsonEvua();
        int IssueId = jsonPathEvaluator.get("iid");
        log.info(IssueId);
        return IssueId;
    }

    public String getIssueAuthor() {
        jsonEvua();
        List<String> allIssueType = jsonPathEvaluator.get("author.name");
        String issueAuthor = allIssueType.get(0);
        log.info("Author of issue: " + issueAuthor);
        return issueAuthor;
    }

    public String getCount() {
        jsonEvua();
        String issueType = jsonPathEvaluator.get("type.size()").toString();
        log.info(issueType);
        return issueType;
    }

    public String getProjectID() {
        jsonEvua();
        int projectid = jsonPathEvaluator.get("project_id");
        return Integer.toString(projectid);
    }

    public String getTitle(String value) {
        jsonEvua();
        if (value.contains("-")) {
            return jsonPathEvaluator.get("due_date");
        } else {

            return jsonPathEvaluator.get("title");
        }

    }

    public int getIssuePosition(int pos) {

        jsonEvua();
        List<Integer> allIssueType = jsonPathEvaluator.getList("id");
        log.info(allIssueType.get(pos));
        return allIssueType.get(pos);

    }

    public Boolean getSubscribeStatus() {
        jsonEvua();
        Boolean subscription = jsonPathEvaluator.get("subscribed");
        return subscription;
    }

    public String getProjectByID() {
        jsonEvua();
        int projectid = jsonPathEvaluator.get("id");
        return Integer.toString(projectid);
    }

    public JsonPath jsonEvua() {
        jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator;

    }

    public String getFailMessage() {
        jsonEvua();
        String errorMessage = response.asString();
        return errorMessage;
    }

    public int headerCount() {
        allHeaders = response.headers();
        int headerSize = allHeaders.size();
        return headerSize;
    }

    public void logHeaders() {
        allHeaders = response.headers();
        for (Header header : allHeaders) {
            log.info("Key: " + header.getName() + " Value: " + header.getValue());
        }

    }

    public String getContentTypeHeader() {

        String contentHeader = response.header("Content-Type");
        return contentHeader;
    }

    public String getStatusLineCode() {
        return response.statusLine();
    }

    public Response sendRequest(int requestType, String route) {

        // need to add a switch based on request type
        switch (requestType) {
        case responseUtility.GET_REQUEST:
            if (null == request) {
                response = RestAssured.when().get(route);
            } else {

                response = request.get(route);
            }
            break;
        case responseUtility.POST_REQUEST:
            if (null == request) {
                response = RestAssured.when().post(route);
            } else {
                response = request.post(route);
            }
            break;
        case responseUtility.DELETE_REQUEST:
            if (null == request) {
                response = RestAssured.when().delete(route);
            } else {
                response = request.delete(route);
            }
            break;
        case responseUtility.PUT_REQUEST:
            if (null == request) {
                response = RestAssured.when().put(route);
            } else {
                response = request.put(route);
            }
            break;
        default:
            if (null == request) {
                response = RestAssured.when().post(route);
            } else {
                response = request.post(route);
            }
            response = request.post(route);
            break;
        }
        return response;

    }

}
