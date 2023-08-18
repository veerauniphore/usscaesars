package util;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportUtil {

	   private static final Logger logger = LoggerFactory.getLogger(ReportUtil.class);
	   static final String DATE_FORMAT = "yyyy-M-dd hh:mm:ss";

	   public static String getAgentID(String agent) {
	      String agent_ = "";
	      String[] newAgent = agent.replace("{", "").replace("}", "").split(",");
	      if (newAgent.length > 1) {
	         agent_ = newAgent[1].split("=")[1];
	      }

	      return agent_;
	   }

	   public static String getDate(String dtNtime) {
	      ZonedDateTime zdt = ZonedDateTime.parse(dtNtime);
	      ZoneId america = ZoneId.of("America/New_York");
	      ZonedDateTime estZonedDateTime = zdt.withZoneSameInstant(america);
	      String dt = "";
	      String[] date = estZonedDateTime.toString().split("T");
	      dt = date[0];
	      return dt;
	   }

	   public static String getTime(String dtNtime) {
	      ZonedDateTime zdt = ZonedDateTime.parse(dtNtime);
	      ZoneId america = ZoneId.of("America/New_York");
	      ZonedDateTime estZonedDateTime = zdt.withZoneSameInstant(america);
	      String dt = "";
	      String[] date = estZonedDateTime.toString().split("T");
	      String[] time = date[1].split("-");
	      dt = time[0];
	      return dt;
	   }

	   public static String getESTtimeZone(String dtNtime) {
	      return "";
	   }

}
