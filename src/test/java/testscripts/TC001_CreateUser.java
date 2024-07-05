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

public class TC001_CreateUser extends HttpUtils {
    String email = "";
    String name = "";
    int id;
    JSONObject requestBody;

    @Test(priority = 1)
    public void POST_CreateUser() {
        String tcId = "TC001";
        if (isRunnable(tcId)) {
            requestBody = ApiUtils.generateRequestBody();
            System.out.println(requestBody);
            Response response = post(endpoint, requestBody);
            response.prettyPrint();
            if (response.getStatusCode() == ResponseCodeFactory.RESPONSE_CODE_201) {
                JsonPath jsonPath = response.jsonPath();
                id = jsonPath.get(Constants.ID_KEY);
                email = jsonPath.get("email");
                name = jsonPath.get("name");
                Logging.info("CREATE user is successful with email");
                ReportUtil.markPassed("CREATE user is successful with email: " + email);
            } else {
                ReportUtil.markFailed("CREATE user is not successful");
            }
        }
    }
}
