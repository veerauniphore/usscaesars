package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import entity.AgentReport;
import entity.AnalyticsResponse;
import entity.Intent;
 
public class ExcelReport {
	
//	@Value("${path}")
//	private String excelDownloadPath;

	   private static final Logger logger = LoggerFactory.getLogger(ExcelReport.class);
	   XSSFWorkbook workbook = new XSSFWorkbook();
	   XSSFSheet spreadsheet;
	   XSSFRow row;
	   
	   XSSFWorkbook workbook1 = new XSSFWorkbook();
	   XSSFSheet spreadsheet1;
//	   private Environment env;

	   public ExcelReport() {
	      this.spreadsheet = this.workbook.createSheet("Report Data");
	      this.spreadsheet1 = this.workbook1.createSheet("Report Data");

	   }

	   public String createFile(AnalyticsResponse[] resp, String f,String excelDownloadPath) throws IOException {
	      int rowCount = 0;
	      List<AgentReport> report = this.getList(resp);
	      System.out.println("AgentReport size" + report.size());
	      logger.info("AgentReport size " + report.size());
	      Iterator var5 = report.iterator();

	      while(var5.hasNext()) {
	         AgentReport aReport = (AgentReport)var5.next();
	         ++rowCount;
	         Row row = this.spreadsheet.createRow(rowCount);
	         this.createHeaderRow(this.spreadsheet);
	         this.writeBook(aReport, row); 
	      }
	      System.out.println("excelDownloadPath" + excelDownloadPath);
	      FileOutputStream outputStream = new FileOutputStream(excelDownloadPath + "call_report_"+currentTime()+".xlsx");
	      Throwable var18 = null;

	      try {
	         this.workbook.write(outputStream);
	      } catch (Throwable var15) {
	         var18 = var15;
	         throw var15;
	      } finally {
	         if (outputStream != null) {
	            if (var18 != null) {
	               try {
	                  outputStream.close();
	               } catch (Throwable var14) {
	                  var18.addSuppressed(var14);
	               }
	            } else {
	               outputStream.close();
	            }
	         }

	      }

	      return "File genrated ..... /Agent_Report.xlsx";
	   }

	   private void writeBook(AgentReport aBook, Row row) {
	      Cell cell = row.createCell(1);
	      cell.setCellValue(aBook.getAgent_CSR());
	      cell = row.createCell(2);
	      cell.setCellValue(aBook.getChatId());
	      cell = row.createCell(3);
	      cell.setCellValue(aBook.getQuerByAgent());
	      cell = row.createCell(4);
	      cell.setCellValue(aBook.getCreatedDate());
	      cell = row.createCell(5);
	      cell.setCellValue(aBook.getTime());
	      cell = row.createCell(6);
	      cell.setCellValue(aBook.getIntentTriggered());
	      cell = row.createCell(7);
	      cell.setCellValue(aBook.getResponseProvided());
	      cell = row.createCell(0);
	      cell.setCellValue(aBook.getVaName());
	   }

	   public List<AgentReport> getList(AnalyticsResponse[] resps) {
	      List<AgentReport> agents = new ArrayList();
	      
	      logger.info("Size" + resps.length);
	      AnalyticsResponse[] var3 = resps;
	      int var4 = resps.length;

	      for(int var5 = 0; var5 < var4; ++var5) {
	         AnalyticsResponse resp = var3[var5];
//	         System.out.println("getIntentdata: " + resp.getIntentdata());
	         if(resp.getIntentdata() != null) {
	        	 
	         
	         Iterator var7 = resp.getIntentdata().iterator();

	         while(var7.hasNext()) {
	            Intent intent = (Intent)var7.next();
	            AgentReport agent = new AgentReport();
	            agent.setAgent_CSR(ReportUtil.getAgentID(resp.getSessiondata().getMetadata()));
	            agent.setChatId(intent.getSessionId());
	            agent.setCreatedDate(ReportUtil.getDate(intent.getCreated_date()));
	            agent.setTime(ReportUtil.getTime(intent.getCreated_date()));
	            agent.setIntentTriggered(intent.getIntentName());
	            agent.setQuerByAgent(intent.getUserRequest());
	            agent.setResponseProvided(intent.getResponsetext());
	            agent.setVaName(intent.getVaName());
	            if (agent.getAgent_CSR() != "" && agent.getAgent_CSR() != null) {
	               agents.add(agent);
	            }
	         }
	      }
	      }

	      return agents;
	   }

	   private void createHeaderRow(XSSFSheet sheet) {
//	      CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
	      Font font = sheet.getWorkbook().createFont();
	      font.setBold(true);
	      font.setFontHeightInPoints((short)12);
//	      cellStyle.setFont(font);
//	      cellStyle.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
	      Row row = sheet.createRow(0);
	      Cell cellTitle = row.createCell(1);
//	      cellTitle.setCellStyle(cellStyle);
	      cellTitle.setCellValue("State");
	      Cell cellAuthor = row.createCell(2);
//	      cellAuthor.setCellStyle(cellStyle);
	      cellAuthor.setCellValue("Chat ID");
	      Cell cellPrice = row.createCell(3);
//	      cellPrice.setCellStyle(cellStyle);
	      cellPrice.setCellValue("Query By Agent/CSR");
	      Cell cell2 = row.createCell(4);
//	      cell2.setCellStyle(cellStyle);
	      cell2.setCellValue("Create Date");
	      Cell cell3 = row.createCell(5);
//	      cell3.setCellStyle(cellStyle);
	      cell3.setCellValue("Time");
	      Cell cell4 = row.createCell(6);
//	      cell4.setCellStyle(cellStyle);
	      cell4.setCellValue("Intent Triggered");
	      Cell cell5 = row.createCell(7);
//	      cell5.setCellStyle(cellStyle);
	      cell5.setCellValue("Reponse provided ");
	      Cell cell6 = row.createCell(0);
//	      cell6.setCellStyle(cellStyle);
	      cell6.setCellValue("vaName ");
	   }
	   
	   public void lastIntnetReport(int lastIntentNumber, AnalyticsResponse[] resp, String f,String excelDownloadPath) throws Throwable {
		   System.out.println("lastIntnetReport");

		      int rowCount = 0;
		      List<AgentReport> report = this.lastIntnetReportList(resp,lastIntentNumber);
		      System.out.println("AgentReport size" + report.size());
		      logger.info("AgentReport size " + report.size());
		      Iterator var5 = report.iterator();

		      while(var5.hasNext()) {
		         AgentReport aReport = (AgentReport)var5.next();
		         ++rowCount;
		         Row row = this.spreadsheet1.createRow(rowCount);
		         this.lastIntnetReportHeader(this.spreadsheet1);
		         this.lastIntnetReportExcelData(aReport, row); 
		      }
		      System.out.println("excelDownloadPath" + excelDownloadPath);
		      System.out.println(excelDownloadPath+"lastIntnetReport.xlsx" +"===="+ excelDownloadPath + "lastIntnetReport_"+currentTime()+".xlsx");

		      FileOutputStream outputStream = new FileOutputStream(excelDownloadPath + "lastIntnetReport"+currentTime()+".xlsx");
		      Throwable var18 = null;

		      try {
		    	  	
		         this.workbook1.write(outputStream);
		      } catch (Throwable var15) {
		         var18 = var15;
		         throw var15;
		      } finally {
		         if (outputStream != null) {
		            if (var18 != null) {
		               try {
		                  outputStream.close();
		               } catch (Throwable var14) {
		                  var18.addSuppressed(var14);
		               }
		            } else {
		               outputStream.close();
		            }
		         }

		      }

	   }
	   private void lastIntnetReportHeader(XSSFSheet sheet) {
//		      CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		      Font font = sheet.getWorkbook().createFont();
		      font.setBold(true);
		      font.setFontHeightInPoints((short)12);
//		      cellStyle.setFont(font);
//		      cellStyle.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		      Row row = sheet.createRow(0);
		      
		      Cell cellAuthor = row.createCell(1);
//		      cellAuthor.setCellStyle(cellStyle);
		      cellAuthor.setCellValue("Chat ID");
		      
		      Cell cell2 = row.createCell(2);
//		      cell2.setCellStyle(cellStyle);
		      cell2.setCellValue("Create Date");
//		      Cell cell3 = row.createCell(5);
//		      cell3.setCellStyle(cellStyle);
//		      cell3.setCellValue("Time");
		      Cell cell4 = row.createCell(3);
//		      cell4.setCellStyle(cellStyle);
		      cell4.setCellValue("Intent Triggered");
		      Cell cell6 = row.createCell(0);
//		      cell6.setCellStyle(cellStyle);
		      cell6.setCellValue("vaName ");
		      Cell cell7 = row.createCell(4);
//		      cell7.setCellStyle(cellStyle);
		      cell7.setCellValue("Last Intent ");
		      Cell cell8 = row.createCell(5);
		      cell8.setCellValue("State"); 
		   } 
	   private void lastIntnetReportExcelData(AgentReport aBook, Row row) {
		      Cell cell = row.createCell(1);
		      cell.setCellValue(aBook.getChatId());
		      cell = row.createCell(2);
		      cell.setCellValue(aBook.getCreatedDate());
//		      cell = row.createCell(3);
//		      cell.setCellValue(aBook.getTime());
		      cell = row.createCell(3);
		      cell.setCellValue(aBook.getIntentTriggered());
		      cell = row.createCell(0);
		      cell.setCellValue(aBook.getVaName());
		      cell = row.createCell(4);
		      cell.setCellValue(aBook.getLastIntent());
		      cell = row.createCell(5);
		      cell.setCellValue(aBook.getAgent_CSR());
		   }
	   
	   public List<AgentReport> lastIntnetReportList(AnalyticsResponse[] resps, int lastIntentNumber) {
		   AnalyticsResponse analyticsResponse = new AnalyticsResponse();
		      List<AgentReport> agents = new ArrayList();
		      logger.info("Size" + resps.length);
		      AnalyticsResponse[] var3 = resps;
		      int var4 = resps.length;
		      Intent intent;
		      Intent lastIntent;
		      for(int var5 = 0; var5 < var4; ++var5) {
		         AnalyticsResponse resp = var3[var5];
		         System.out.println("getIntentdata: " + resp.getIntentdata());
		         if(resp.getIntentdata() != null) {
		        	 
		        	 		         
//		         Iterator var7 = resp.getIntentdata().iterator();
		        	 int siz = resp.getIntentdata().size();
		        	 System.out.println("siz: " + siz);
		        	 
		        	 if(siz>=2) {
		        		  intent = resp.getIntentdata().get(siz - lastIntentNumber);
		        	 }else {
		        		  intent = resp.getIntentdata().get(siz - 1);
		        	 }
		        	 lastIntent = resp.getIntentdata().get(siz - 1);
		        	 
//		        	 System.out.println("intent len : " + intent.getSessionId());

//		         while(var7.hasNext()) {
//		            Intent intent = (Intent)var7.next();
		            AgentReport agent = new AgentReport();
		            agent.setChatId(intent.getSessionId());
		            agent.setCreatedDate(ReportUtil.getDate(intent.getCreated_date()));
//		            agent.setTime(ReportUtil.getTime(intent.getCreated_date()));
		            agent.setIntentTriggered(intent.getIntentName());
//		            agent.setQuerByAgent(intent.getUserRequest());
//		            agent.setResponseProvided(intent.getResponsetext());
		            agent.setVaName(intent.getVaName());
		            agent.setLastIntent(lastIntent.getIntentName());
		            agent.setAgent_CSR(ReportUtil.getAgentID(resp.getSessiondata().getMetadata()));
		            agents.add(agent);
		            System.out.println("agents size : " + agents.size());
//		         }
		      }
		      }

		      return agents;
		   }
		public String currentTime() {
	   
	   		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
	   		System.out.println("timeStamp " + timeStamp);
	   				
	   		return timeStamp.toString();
	   	}
}
