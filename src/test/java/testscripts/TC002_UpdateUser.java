package testscripts;

import com.api.console.Logging;
import com.api.lib.ApiUtils;
import com.api.lib.HttpUtils;
import com.api.reports.ReportUtil;
import com.api.utilities.Constants;
import com.api.utilities.ResponseCodeFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TC002_UpdateUser extends HttpUtils {
    String email = "";
    String name = "";
    int id;
    JSONObject requestBody;

    @Test(priority = 2)
    public void Put_UpdateUser() {
            String tcId = "TC002";
            if (isRunnable(tcId)) {
                endpoint = endpoint + Constants.FRONT_SLASH + id;
                Response response = put(endpoint, ApiUtils.updateRequestBody(requestBody));
                if (response.getStatusCode() == ResponseCodeFactory.RESPONSE_CODE_200) {
                    JsonPath jsonPath = response.jsonPath();
                    name = jsonPath.get("name");
                    ReportUtil.markPassed("UPDATE user is successful with the response body: " + jsonPath.prettify());
                } else {
                    ReportUtil.markFailed("UPDATE user is not successful");
                }
            }
        }
    }

