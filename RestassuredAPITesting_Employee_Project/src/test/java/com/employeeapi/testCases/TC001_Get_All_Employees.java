package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase{
	
	@BeforeClass
	public void getAllEmployees() throws InterruptedException
	{
		logger.info("************** Started TC001_Get_All_Employees *******************");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET,"/employees");
		
		Thread.sleep(3);
	}
	
	@Test
	public void checkResponseBody()
	{
		logger.info("*************** Checking Response Body ****************");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" +responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	public void checkStatusCode()
	{
		logger.info("************** Checking Status Code ****************");
		
		int statuscode = response.getStatusCode(); // Getting status code
		logger.info("Status Code is ==>" +statuscode); //200
		Assert.assertEquals(statuscode, 200);
	}
	
	@Test
	public void checkResponseTime()
	{
		logger.info("************** Checking Response Time *************");
		
		long responseTime=response.getTime(); // Getting the Status line
		logger.info("Response Time is ==>" +responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		
		Assert.assertTrue(responseTime<2000);
	}
	
	@Test
	public void checkstatusLine()
	{
		logger.info("************* Checking Status Line ***********");
		
		String statusLine = response.getStatusLine(); // Getting Status line
		logger.info("Status Line is ==>" +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void checkContentType()
	{
		logger.info("************ Checking Content Type ****************");
		
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==>" +contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test
	public void checkserverType()
	{
		logger.info("************ Checking Server Type ****************");

		String serverType = response.header("Server");
		logger.info("Server Type is ==>" + serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1");

	}
	
	@Test
	public void checkcontentEncoding()
	{
		logger.info("************ Checking Content Encoding ****************");

		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");

	}
	
	@Test
	public void checkContentLength()
	{
		logger.info("************ Checking Content Length ****************");

		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==>" + contentLength);
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);

	}
	
	@Test
	public void checkCookies()
	{
		logger.info("************ Checking Cookies ****************");

		String cookie = response.getCookie("PHPSESSID");
		
		//Assert.assertEquals(cookie, "HFJHKJKJJ8989898wewehwjehwe");

	}
	
	@AfterClass
	public void tearDown()
	{
		logger.info("************** Finished TC001_Get_All_Employees *******************");
	}
}
