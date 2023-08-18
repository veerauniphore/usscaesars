package entity;

public class AgentReport {

	   private String Agent_CSR;
	   private String chatId;
	   private String QuerByAgent;
	   private String createdDate;
	   private String time;
	   private String intentTriggered;
	   private String responseProvided;
	   private String vaName;
	   private String lastIntent;

	   public String getAgent_CSR() {
	      return this.Agent_CSR;
	   }

	   public void setAgent_CSR(String agent_CSR) {
	      this.Agent_CSR = agent_CSR;
	   }

	   public String getChatId() {
	      return this.chatId;
	   }

	   public void setChatId(String chatId) {
	      this.chatId = chatId;
	   }

	   public String getQuerByAgent() {
	      return this.QuerByAgent;
	   }

	   public void setQuerByAgent(String querByAgent) {
	      this.QuerByAgent = querByAgent;
	   }

	   public String getCreatedDate() {
	      return this.createdDate;
	   }

	   public void setCreatedDate(String createdDate) {
	      this.createdDate = createdDate;
	   }

	   public String getTime() {
	      return this.time;
	   }

	   public void setTime(String time) {
	      this.time = time;
	   }

	   public String getIntentTriggered() {
	      return this.intentTriggered;
	   }

	   public void setIntentTriggered(String intentTriggered) {
	      this.intentTriggered = intentTriggered;
	   }

	   public String getResponseProvided() {
	      return this.responseProvided;
	   }

	   public void setResponseProvided(String responseProvided) {
	      this.responseProvided = responseProvided;
	   }

	   public String getVaName() {
	      return this.vaName;
	   }

	   public void setVaName(String vaName) {
	      this.vaName = vaName;
	   }

	public String getLastIntent() {
		return lastIntent;
	}

	public void setLastIntent(String lastIntent) {
		this.lastIntent = lastIntent;
	}

}
