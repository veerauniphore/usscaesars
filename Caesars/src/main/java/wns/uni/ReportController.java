package wns.uni;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiji.bind.Poiji;

import entity.AnalyticsRequest;
import entity.AnalyticsResponse;
import entity.Reportreq;
import util.ExcelData;
import util.ExcelReport;


@RestController
public class ReportController {
	
	@Value("${tokenUrl}")
	private String tokenUrl;
	
	@Value("${analyticsUrl}")
	private  String analyticsUrl;
	
	@Value("${excelPath}")
	private String excelDownloadPath;
	
	@Value("${lastIntentReport}")
	private Boolean lastIntentReport;
	
	@Value("${lastIntentNumber}")
	private int lastIntentNumber;

	   private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	   private static RestTemplate restTemplate = new RestTemplate();
	   ExcelReport xlreport = new ExcelReport();

	   @RequestMapping(
	      value = {"/getReport"},
	      method = {RequestMethod.POST}
	   )
	   public ResponseEntity<Map<String, String>> generateReport(@RequestBody Reportreq report) {
	      String output = "";
	      String file = "";
	      boolean success = true;
	      HashMap errorResponse = new HashMap();

	      try {
	         logger.info("request " + report.toString());
	         if (report.getUsername().equals("2")) {
	            file = "call_report.xlsx";
	         } else {
	            file = "call_report.xlsx";
	         }

	         ResponseEntity<APIToken> token = this.getApiToken(report.getUsername(), report.getPassword());
	         String token1 = ((APIToken)token.getBody()).getToken();
	         System.out.println("token "+token1);
	         HttpHeaders headers = new HttpHeaders();
	         headers.add("Accept", "*/*");
	         headers.setContentType(MediaType.APPLICATION_JSON);
	         headers.add("Authorization", "Bearer " + token1);
	         System.out.println("req  "+report.getStartDate()+report.getEndDate()+report.getChannel()+report.getUsername()+report.getLangname()+report.getActive());
	         AnalyticsRequest req = new AnalyticsRequest(report.getStartDate(), report.getEndDate(), report.getChannel(), report.getUsername(), report.getLangname(), report.getActive());
	         HttpEntity<AnalyticsRequest> requestBody = new HttpEntity(req, headers);
	         ResponseEntity<String> res = this.getAnalyticsReport(requestBody);
	         ObjectMapper mapper = new ObjectMapper();
	         System.out.println("res.getBody()  "+res.getBody());
	         AnalyticsResponse[] aresp = (AnalyticsResponse[])mapper.readValue((String)res.getBody(), AnalyticsResponse[].class);
		      System.out.println("excelDownloadPath >>" + excelDownloadPath);

	         output = this.xlreport.createFile(aresp, file,excelDownloadPath);
	         if(lastIntentReport) {
	        	 try {
					xlreport.lastIntnetReport(lastIntentNumber,aresp,file,excelDownloadPath);
				} catch (Throwable e) { 
					e.printStackTrace();
				}
	         }
	         
	         errorResponse.put("message", output);
	         errorResponse.put("status", HttpStatus.OK.toString());
	      } catch (Exception var14) {
	         errorResponse.put("message", "Some error occurred to fetch data from API.");
	         errorResponse.put("status", HttpStatus.NOT_FOUND.toString());
	         success = false;
	         var14.printStackTrace();
	      }

	      return success ? new ResponseEntity(errorResponse, HttpStatus.OK) : new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
	   }

	   public ResponseEntity<APIToken> getApiToken(String user, String pwd) throws Exception {
		   System.out.println("tokenUrl"+tokenUrl);
	      ResponseEntity<APIToken> token = restTemplate.postForEntity(tokenUrl + user + "&password=" + pwd, "", APIToken.class, new Object[0]);
	     
	      return token;
	   }

	   public ResponseEntity<String> getAnalyticsReport(HttpEntity<AnalyticsRequest> req) {
	      ResponseEntity analytics = null;
	      System.out.println("analyticsUrl"+analyticsUrl);
	      try {
	         logger.info("Request  " + req.toString());
	         System.out.println("req data "+req.toString());
	         analytics = restTemplate.postForEntity(analyticsUrl, req, String.class, new Object[0]);
	      } catch (Exception var4) {
	         var4.printStackTrace();
	      }

	      return analytics;
	   }
	   
	  
}

