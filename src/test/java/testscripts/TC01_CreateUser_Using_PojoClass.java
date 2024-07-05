package testscripts;

import com.api.base.DriverScript;
import com.api.console.Logging;
import com.api.lib.ApiUtils;
import com.api.lib.HttpUtils;
import com.pojos.Users;
import com.api.reports.ReportUtil;
import com.api.utilities.Constants;
import com.api.utilities.ResponseCodeFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TC01_CreateUser_Using_PojoClass extends DriverScript {
    String email = "";
    String name = "";
    int id;
    String requestBody;

    @Test(priority = 1)
    public void POST_CreateUser() {
        String tcId = "TC01";
        if (isRunnable(tcId)) {
             Response response = HttpUtils.createUser(endpoint);
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
