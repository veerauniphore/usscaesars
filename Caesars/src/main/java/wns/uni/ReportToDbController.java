package wns.uni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.AgentReport;
import entity.AnalyticsRequest;
import entity.AnalyticsResponse;
import entity.Intent;
import entity.Reportreq;
import util.DBConncetivity;
import util.ReportUtil;

@RestController
public class ReportToDbController {

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
	
	@Value("${POSTGRESQL_URL}")
	private String DB_url;
	
	@Value("${POSTGRESQL_USERNAME}")
	private String DB_USERNAME;
	
	@Value("${POSTGRESQL_PASSWORD}")
	private String DB_PASSWORD;
	
	@Value("${POSTGRESQL_DATABASE}")
	private String DB_DATABASE;
	
	@Value("${channel}")
	private String channel;
	
	@Value("${vaname}")
	private String vaname;
	
	@Value("${langname}")
	private String langname;
	
	@Value("${active}")
	private String active;
	
	@Value("${password}")
	private String password;
	
	
	
	@Autowired
	ReportController reportController;
	private static final Logger logger = LoggerFactory.getLogger(ReportToDbController.class);
	private static RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = { "/ReportToDb" }, method = { RequestMethod.POST })
	public String getDataFromApi(@RequestBody Reportreq report) throws Exception {
		
		
		String status = apicalling(report.getStartDate(), report.getEndDate(), report.getChannel(), report.getUsername(), report.getLangname(), report.getActive(),report.getPassword());

         if(status.equals("success")) {
        	 return "Data sucessfully inserted in DB";
         }else {
        	 return "Data insert faild";
         }
	}
	
	public String apicalling(String previousDate,String currentDate,String channel,String VA_username,String langname, String active,String password ) throws Exception {
		
			System.out.println("getDataFromApi ");
			String status = "";
			 ResponseEntity<APIToken> token = reportController.getApiToken(VA_username, password);
	         String token1 = ((APIToken)token.getBody()).getToken();
	         System.out.println("token "+token1);
	         HttpHeaders headers = new HttpHeaders();
	         headers.add("Accept", "*/*");
	         headers.setContentType(MediaType.APPLICATION_JSON);
	         logger.info("token1 " + token1);
	         headers.add("Authorization", "Bearer " + token1);
//	         System.out.println("req  "+report.getStartDate()+report.getEndDate()+report.getChannel()+report.getUsername()+report.getLangname()+report.getActive());
//	         AnalyticsRequest req = new AnalyticsRequest(report.getStartDate(), report.getEndDate(), report.getChannel(), report.getUsername(), report.getLangname(), report.getActive());
	         AnalyticsRequest req = new AnalyticsRequest(previousDate, currentDate, channel, VA_username, langname, active);
	         HttpEntity<AnalyticsRequest> requestBody = new HttpEntity(req, headers);
	         ResponseEntity<String> res = reportController.getAnalyticsReport(requestBody);
	         System.out.println("res "+res.getBody());
	         logger.info("analytics api response " + res.getBody());
	         ObjectMapper mapper = new ObjectMapper();
	         AnalyticsResponse[] aresp = (AnalyticsResponse[])mapper.readValue((String)res.getBody(), AnalyticsResponse[].class);
	         status = createJsonForDb(aresp);
		
		return "success";
	}
	
	public void dataToDBScheduler() throws Exception {
//		 logger.info("request " + report.toString());
		Calendar cal  = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
	    String previousDate = s.format(new Date(cal.getTimeInMillis()));
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

		String status = "";
		 ResponseEntity<APIToken> token = reportController.getApiToken(vaname, password);
        String token1 = ((APIToken)token.getBody()).getToken();
        System.out.println("token "+token1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.setContentType(MediaType.APPLICATION_JSON);
        logger.info("token1 " + token1);
        headers.add("Authorization", "Bearer " + token1);
//        System.out.println("req  "+report.getStartDate()+report.getEndDate()+report.getChannel()+report.getUsername()+report.getLangname()+report.getActive());
        AnalyticsRequest req = new AnalyticsRequest(previousDate, currentDate, channel, vaname, langname, active);
        HttpEntity<AnalyticsRequest> requestBody = new HttpEntity(req, headers);
        RestTemplate template = new RestTemplate();
        Map<String, String> bodyParamMap = new HashMap<String, String>();
//        JSONObject bodyParamMap = new JSONObject();
        bodyParamMap.put("startDate", previousDate );
        bodyParamMap.put("endDate", currentDate);
        bodyParamMap.put("channel", channel);
        bodyParamMap.put("username", vaname);
        bodyParamMap.put("langname", langname);
        bodyParamMap.put("active", active);
        System.out.println("bodyParamMap "+bodyParamMap.toString());
        String reqBodyData = new ObjectMapper().writeValueAsString(bodyParamMap);
        HttpEntity<String> requestEnty = new HttpEntity<>(reqBodyData, headers);
        ResponseEntity<String> res = template.postForEntity(analyticsUrl, requestEnty, String.class);
        System.out.println("res "+res.getBody());
        logger.info("analytics api response " + res.getBody());
        ObjectMapper mapper = new ObjectMapper();
        AnalyticsResponse[] aresp = (AnalyticsResponse[])mapper.readValue((String)res.getBody(), AnalyticsResponse[].class);
        status = createJsonForDb(aresp);

       
	}
	
	public String createJsonForDb(AnalyticsResponse[] resps) {
		System.out.println("createJsonForDb ");
		String status = "";
		  AnalyticsResponse analyticsResponse = new AnalyticsResponse();
		  List<JSONObject> report = new ArrayList();
	      
	      AnalyticsResponse[] var3 = resps;
	      int var4 = resps.length;
	      Intent intent;
	      Intent lastIntent;
	      
	      for(int var5 = 0; var5 < var4; ++var5) {
	         AnalyticsResponse resp = var3[var5];
//	         System.out.println("getIntentdata: " + resp.getIntentdata());
	         if(resp.getIntentdata() != null && resp.getIntentdata().size() >0) {
	        	 
	        	 		         
	        	 int siz = resp.getIntentdata().size();
//	        	 System.out.println("siz: " + siz);
	        	 
	        	 if(siz>=2) {
	        		  intent = resp.getIntentdata().get(siz - lastIntentNumber);
	        	 }else {
	        		  intent = resp.getIntentdata().get(siz - 1);
	        	 }
	        	 lastIntent = resp.getIntentdata().get(siz - 1);
	        	 

	        	 Map<String,String> reportData = new HashMap<String, String>();
	        	 
	        	    reportData.put("vaName", intent.getVaName());
		            reportData.put("Chat ID", intent.getSessionId());
		            reportData.put("Create Date", ReportUtil.getDate(intent.getCreated_date()));
		            reportData.put("Intent Triggered", intent.getIntentName());
		            reportData.put("Last Intent ", lastIntent.getIntentName());
		            JSONObject jsonData = new JSONObject(reportData);
		            report.add(jsonData);
//	            System.out.println("report : " + report);
	      }
	      }
	      logger.info("final data" + report);
	       status = jsonToDb(report);
	       
	       return  status;
	}
	
	public String  jsonToDb(List<JSONObject> report) {
		System.out.println("report: "+report);
		String status = "";
		DBConncetivity dbConncetivity = new DBConncetivity();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection =dbConncetivity.DBConnection(DB_url,DB_USERNAME,DB_PASSWORD,DB_DATABASE);
		String SQL = "select * from udf_save_intent_report('"+report+"')";
		logger.info("SQL query" + SQL);
		System.out.println("SQL: "+SQL);
		
		if(null != connection) {
    		
			try {
				pstmt = connection.prepareStatement(SQL);
			    rs = pstmt.executeQuery();
				   
		            if(rs.next()) {			            	
		            	System.out.println(" data updated in DB ");
		    	}
		            
			} catch (SQLException e) {
//				e.printStackTrace();
			}finally {
		    	  try {
		    		  if(connection != null){
//		    			  rs.close();
		    			  pstmt.close();
		    			  connection.close();
		    		  }
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		      }			
         
    	}
		return "success";
	}
//	public static void main(String args[]) throws java.text.ParseException {
//		
//		System.out.println("timeStamp "+result);
//		
//	}
	

	
}
