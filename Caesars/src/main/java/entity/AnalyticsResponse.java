package entity;

import java.util.List;

public class AnalyticsResponse {

	   public SessionID sessiondata;
	   public List<Intent> intentdata;

	   public SessionID getSessiondata() {
	      return this.sessiondata;
	   }

	   public void setSessiondata(SessionID sessiondata) {
	      this.sessiondata = sessiondata;
	   }

	   public List<Intent> getIntentdata() {
	      return this.intentdata;
	   }

	   public void setIntentdata(List<Intent> intentdata) {
	      this.intentdata = intentdata;
	   }

}
