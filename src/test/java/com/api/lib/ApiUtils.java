package com.api.lib;

import com.pojos.Users;
import org.apache.juneau.json.*;
import org.json.simple.JSONObject;

import com.api.reports.ReportUtil;
import com.api.utilities.CommonUtils;

import io.restassured.response.Response;

public class ApiUtils extends HttpUtils {

	/**
	 *
	 * @param URI
	 * @param response
	 */
	public static void validateResponseHeader (String URI, Response response) {
		if (response.header("content-type").contains("application/json")) {
			ReportUtil.markPassed("Response header is correctly validated");
		} else {
			ReportUtil.markFailed("Reponse header is not validated");
		}
	}

	/**
	 *
	 * @param response
	 * @return
	 */
	public static long getResponseTime (Response response) {

		return response.getTime();
	}

	/**
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject generateRequestBody () {
		JSONObject json = new JSONObject();
		String random = CommonUtils.getRandomName ("RAND_FirstName");
		json.put("name", random);
		json.put("gender", "male");
		json.put("email", random + "@email.com");
		json.put("status", "active");
		return json;
	}

	/**
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject updateRequestBody (JSONObject requestBody) {
		JSONObject json = new JSONObject();
		requestBody.forEach((key, value) -> {
			if (key.equals("name")) {
				json.put(key, value + "_UPDATED");
			}
		});
		return json;
	}

	/*************************************************************************************/
	public static String createUserRequestPayload1() {
		String jsonRequest ="";
		try {
			JsonSerializer jsonSerializer = JsonSerializer.DEFAULT_READABLE;
			String random = CommonUtils.getRandomName("RAND_FirstName");
			String email = random + "@email.com";
			Users userPayload = new Users();
			userPayload.setName(random);
			userPayload.setGender("Male");
			userPayload.setEmail(email);
			userPayload.setStatus("Active");
		}catch (Throwable t){
			System.out.println(t.getMessage());
		}
		return jsonRequest;
	}

}