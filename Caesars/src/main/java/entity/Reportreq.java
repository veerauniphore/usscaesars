package entity;

public class Reportreq {

	   private String username;
	   private String password;
	   private String startDate;
	   private String endDate;
	   private String channel;
	   private String langname;
	   private String active;

	   public String getUsername() {
	      return this.username;
	   }

	   public void setUsername(String username) {
	      this.username = username;
	   }

	   public String getPassword() {
	      return this.password;
	   }

	   public void setPassword(String password) {
	      this.password = password;
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


	   public String getLangname() {
		return langname;
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
	      return "Parameters :: username=" + this.username + ", password=" + this.password + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", channel=" + this.channel + ", langName=" + this.langname + ", active=" + this.active;
	   }

}
