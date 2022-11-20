package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	public void deleteEmployee() throws InterruptedException {
		logger.info("*********************** Started TC005_Delete_Employee_Record *********************");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/employees");

		// First get the Jsonpath object instance from the response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// CaptureID
		String empID = jsonPathEvaluator.get("[0].id");

		response = httpRequest.request(Method.DELETE, "/delete/" + empID); // pass id to delete record

		Thread.sleep(5000);
	}

	@Test
	public void checkResponseBody() {
		logger.info("*************** Checking Response Body ****************");

		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
		
	}

	@Test
	public void checkStatusCode() {
		logger.info("************** Checking Status Code ****************");

		int statuscode = response.getStatusCode(); // Getting status code
		logger.info("Status Code is ==>" + statuscode); // 200
		Assert.assertEquals(statuscode, 200);
	}

	@Test
	public void checkstatusLine() {
		logger.info("************* Checking Status Line ***********");

		String statusLine = response.getStatusLine(); // Getting Status line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void checkserverType() {
		logger.info("************ Checking Server Type ****************");

		String serverType = response.header("Server");
		logger.info("Server Type is ==>" + serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1");

	}

	@Test
	public void checkcontentEncoding() {
		logger.info("************ Checking Content Encoding ****************");

		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");

	}

	@Test
	public void checkContentLength() {
		logger.info("************ Checking Content Length ****************");

		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==>" + contentLength);

		Assert.assertTrue(Integer.parseInt(contentLength) < 1500);

	}

	@AfterClass
	public void tearDown() {
		logger.info("************** Finished TC005_Delete_Employee_Record *******************");
	}

}
