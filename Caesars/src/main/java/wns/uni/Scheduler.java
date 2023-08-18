package wns.uni;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component 
public class Scheduler {
	
	@Value("${Scheduler_time}")
	private String Scheduler_time;
	
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
    private ReportToDbController reportToDbController;
	
	
	  @Scheduled(cron = "${Scheduler_time}")
	    public void doTheListThingy() {
		  
				  
	    	try {
				reportToDbController.dataToDBScheduler();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
}
