package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Reportreq;

public class UpdateParams {

	   private static final Logger logger = LoggerFactory.getLogger(UpdateParams.class);

	   public static Reportreq setParams(String[] params) {
	      Reportreq parameters = new Reportreq();
	      String[] var2 = params;
	      int var3 = params.length;

	      for(int var4 = 0; var4 < var3; ++var4) {
	         String param = var2[var4];
	         String[] map = param.split("=");
	         if (map[0].equalsIgnoreCase("username")) {
	            parameters.setUsername(map[1]);
	         }

	         if (map[0].equalsIgnoreCase("password")) {
	            parameters.setPassword(map[1]);
	         }

	         if (map[0].equalsIgnoreCase("startDate")) {
	            parameters.setStartDate(map[1]);
	         }

	         if (map[0].equalsIgnoreCase("endDate")) {
	            parameters.setEndDate(map[1]);
	         }

	         if (map[0].equalsIgnoreCase("channel")) {
	            parameters.setChannel(map[1]);
	         }

	         if (map[0].equalsIgnoreCase("langName")) {
	            parameters.setLangname(map[1]);
	         }

	         if (map[0].equalsIgnoreCase("active")) {
	            parameters.setActive(map[1]);
	         }
	      }

	      return parameters;
	   }

}
