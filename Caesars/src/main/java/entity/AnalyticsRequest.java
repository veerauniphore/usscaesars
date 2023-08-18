package entity;

public class AnalyticsRequest {

	   private String startDate;
	   private String endDate;
	   private String channel;
	   private String username;
	   private String langname;
	   private String active;

	   public AnalyticsRequest(String startDate, String endDate, String channel, String username, String langname, String active) {
	      this.startDate = startDate;
	      this.endDate = endDate;
	      this.channel = channel;
	      this.username = username;
	      this.langname = langname;
	      this.active = active;
	   }

	   public String getStartDate() {
	      return this.startDate;
	   }

	   public void setStartDate(String startDate) {
	      this.startDate = startDate;
	   }

	   public String getEndDate() {
	      return this.endDate;
	   }

	   public void setEndDate(String endDate) {
	      this.endDate = endDate;
	   }

	   public String getChannel() {
	      return this.channel;
	   }

	   public void setChannel(String channel) {
	      this.channel = channel;
	   }

	   public String getUsername() {
	      return this.username;
	   }

	   public void setUsername(String username) {
	      this.username = username;
	   }

	   public String getLangname() {
	      return this.langname;
	   }

	   public void setLangname(String langname) {
	      this.langname = langname;
	   }

	   public String getActive() {
	      return this.active;
	   }

	   public void setActive(String active) {
	      this.active = active;
	   }

	   public String toString() {
	      return "AnalyticsRequest [startDate=" + this.startDate + ", endDate=" + this.endDate + ", channel=" + this.channel + ", username=" + this.username + ", langname=" + this.langname + ", active=" + this.active + "]";
	   }

}
