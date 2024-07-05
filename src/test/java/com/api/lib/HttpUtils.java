package com.api.lib;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojos.Users;
import com.api.utilities.CommonUtils;
import io.restassured.http.ContentType;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.serializer.SerializeException;
import org.json.simple.JSONObject;
import com.api.reports.ReportUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpUtils extends AuthFactory {

	static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
	static final String APPLICATION_CONTENT_TYPE []= {"application/json", "application/javascript", "text/javascript", "text/json"};
	static final String AUTHORIZATION = "Authorization";

	/**
	 *
	 * @param endpoint
	 * @return
	 */
	public static Response get(String endpoint) {
		try {
			RequestSpecification request = RestAssured.given();
			Response response = request
					.header(AUTHORIZATION, "Bearer " + BEARER_TOKEN)
					.get(endpoint);
			return response;
		} catch (Throwable t) {
			ReportUtil.markFailed("GET command is failed for the testCaseID: " + testCaseId);
			t.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * @return
	 */
	public static RequestSpecification request() {
		RequestSpecification request = RestAssured.given();
		return request.header(AUTHORIZATION, "Bearer " + BEARER_TOKEN);
	}

	/**
	 *
	 * @param endpoint
	 * @param json
	 * @return
	 */
	public static Response post(String endpoint, JSONObject json) {
		try {
			Response response = given()
					.header(AUTHORIZATION, "Bearer " + BEARER_TOKEN)
					.accept(APPLICATION_JSON_CONTENT_TYPE)
					.contentType(APPLICATION_JSON_CONTENT_TYPE)
					.body(json)
					.when()
					.post(endpoint);
			return response;
		} catch (Throwable t) {
			ReportUtil.markFailed("POST command is failed for the testCaseID: " + testCaseId);
			t.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * @param endpoint
	 * @param json
	 * @return
	 */
	public static Response put(String endpoint, JSONObject json) {
		try {
			Response response = given()
					.header("Authorization", "Bearer " + BEARER_TOKEN)
					.accept(APPLICATION_JSON_CONTENT_TYPE)
					.contentType(APPLICATION_JSON_CONTENT_TYPE)
					.body(json)
					.when()
					.put(endpoint);
			return response;
		} catch (Throwable t) {
			ReportUtil.markFailed("PUT command is failed for the testCaseID: " + testCaseId);
			t.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * @param endpoint
	 * @return
	 */
	public static Response delete(String endpoint) {
		try {
			Response response = given()
					.header(AUTHORIZATION, "Bearer " + BEARER_TOKEN)
					.accept(APPLICATION_JSON_CONTENT_TYPE)
					.contentType(APPLICATION_JSON_CONTENT_TYPE)
					.when()
					.delete(endpoint);
			return response;
		} catch (Throwable t) {
			ReportUtil.markFailed("DELETE command is failed for the testCaseID: " + testCaseId);
			t.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * @param endpoint
	 * @param json
	 * @return
	 */
	public static Response patch(String endpoint, JSONObject json) {
		try {
			Response response = given()
					.header(AUTHORIZATION, "Bearer " + BEARER_TOKEN)
					.accept(APPLICATION_JSON_CONTENT_TYPE)
					.contentType(APPLICATION_JSON_CONTENT_TYPE)
					.body(json)
					.when()
					.patch(endpoint);
			return response;
		} catch (Throwable t) {
			ReportUtil.markFailed("PATCH command is failed for the testCaseID: " + testCaseId);
			t.printStackTrace();
			return null;
		}
	}

	/*********************************Using Pojo Clases**************************************/
	static Users userPayload = new Users();
	static ObjectMapper object = new ObjectMapper();
	static String userRequest="";
public static void createUserRequestPayload() {
	try {
		String random = CommonUtils.getRandomName("RAND_FirstName");
		String email = random + "@email.com";
		userPayload.setName(random);
		userPayload.setGender("male");
		userPayload.setEmail(email);
		userPayload.setStatus("active");
		userRequest = object.writeValueAsString(userPayload);
		System.out.println(userRequest);
	}catch (Throwable t){
		System.out.println(t.getMessage());
	}
			}
	public static Response createUser(String endPoint)
	{
		createUserRequestPayload();
		Response response = given()
				.header(AUTHORIZATION, "Bearer " + BEARER_TOKEN)
				.accept(APPLICATION_JSON_CONTENT_TYPE)
				.contentType(APPLICATION_JSON_CONTENT_TYPE)
				.body(userRequest)
				.when()
				.post(endPoint);
		return response;
	}
	public static Response GetUser(String userName)
	{
		Response response = given()
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.when()
				.get(endpoint);

		return response;
	}
	public static Response UpdateUser(String userName, Users payload)
	{
		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when()
				.put(endpoint);

		return response;
	}
	public static Response DeleteUser(String userName)
	{
		Response response = given()
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.when()
				.delete(endpoint);

		return response;
	}
}