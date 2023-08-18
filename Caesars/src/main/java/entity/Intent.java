package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(
		   ignoreUnknown = true
		)

public class Intent {

	   private String _id;
	   private String created_date;
	   private String vaName;
	   private String channelMaster;
	   private String isFirstRequest;
	   private String userRequest;
	   private String intentName;
	   private String responsetext;
	   private String langname;
	   private String catId;
	   private String rolename;
	   private String isTransfer;
	   private String intentNumber;
	   private String pingBack;
	   private String sessionId;
	   private String intentStage;
	   private String finalResponseGiven;
	   private String responseHtml;
	   private String htmlJsonList;
	   private String isCustomHtmlTemplate;
	   private String responseCode;

	   public String getId() {
	      return this._id;
	   }

	   public void setId(String id) {
	      this._id = id;
	   }

	   public String getCreated_date() {
	      return this.created_date;
	   }

	   public void setCreated_date(String created_date) {
	      this.created_date = created_date;
	   }

	   public String getVaName() {
	      return this.vaName;
	   }

	   public void setVaName(String vaName) {
	      this.vaName = vaName;
	   }

	   public String getChannelMaster() {
	      return this.channelMaster;
	   }

	   public void setChannelMaster(String channelMaster) {
	      this.channelMaster = channelMaster;
	   }

	   public String getIsFirstRequest() {
	      return this.isFirstRequest;
	   }

	   public void setIsFirstRequest(String isFirstRequest) {
	      this.isFirstRequest = isFirstRequest;
	   }

	   public String getUserRequest() {
	      return this.userRequest;
	   }

	   public void setUserRequest(String userRequest) {
	      this.userRequest = userRequest;
	   }

	   public String getIntentName() {
	      return this.intentName;
	   }

	   public void setIntentName(String intentName) {
	      this.intentName = intentName;
	   }

	   public String getResponsetext() {
	      return this.responsetext;
	   }

	   public void setResponsetext(String responsetext) {
	      this.responsetext = responsetext;
	   }

	   public String getLangname() {
	      return this.langname;
	   }

	   public void setLangname(String langname) {
	      this.langname = langname;
	   }

	   public String getCatId() {
	      return this.catId;
	   }

	   public void setCatId(String catId) {
	      this.catId = catId;
	   }

	   public String getRolename() {
	      return this.rolename;
	   }

	   public void setRolename(String rolename) {
	      this.rolename = rolename;
	   }

	   public String getIsTransfer() {
	      return this.isTransfer;
	   }

	   public void setIsTransfer(String isTransfer) {
	      this.isTransfer = isTransfer;
	   }

	   public String getIntentNumber() {
	      return this.intentNumber;
	   }

	   public void setIntentNumber(String intentNumber) {
	      this.intentNumber = intentNumber;
	   }

	   public String getPingBack() {
	      return this.pingBack;
	   }

	   public void setPingBack(String pingBack) {
	      this.pingBack = pingBack;
	   }

	   public String getSessionId() {
	      return this.sessionId;
	   }

	   public void setSessionId(String sessionId) {
	      this.sessionId = sessionId;
	   }

	   public String getIntentStage() {
	      return this.intentStage;
	   }

	   public void setIntentStage(String intentStage) {
	      this.intentStage = intentStage;
	   }

	   public String getFinalResponseGiven() {
	      return this.finalResponseGiven;
	   }

	   public void setFinalResponseGiven(String finalResponseGiven) {
	      this.finalResponseGiven = finalResponseGiven;
	   }

	   public String getResponseHtml() {
	      return this.responseHtml;
	   }

	   public void setResponseHtml(String responseHtml) {
	      this.responseHtml = responseHtml;
	   }

	   public String getHtmlJsonList() {
	      return this.htmlJsonList;
	   }

	   public void setHtmlJsonList(String htmlJsonList) {
	      this.htmlJsonList = htmlJsonList;
	   }

	   public String getIsCustomHtmlTemplate() {
	      return this.isCustomHtmlTemplate;
	   }

	   public void setIsCustomHtmlTemplate(String isCustomHtmlTemplate) {
	      this.isCustomHtmlTemplate = isCustomHtmlTemplate;
	   }

	   public String getResponseCode() {
	      return this.responseCode;
	   }

	   public void setResponseCode(String responseCode) {
	      this.responseCode = responseCode;
	   }

	   public String toString() {
	      return "Intent [_id=" + this._id + ", created_date=" + this.created_date + ", vaName=" + this.vaName + ", channelMaster=" + this.channelMaster + ", isFirstRequest=" + this.isFirstRequest + ", userRequest=" + this.userRequest + ", intentName=" + this.intentName + ", responsetext=" + this.responsetext + ", langname=" + this.langname + ", catId=" + this.catId + ", rolename=" + this.rolename + ", isTransfer=" + this.isTransfer + ", intentNumber=" + this.intentNumber + ", pingBack=" + this.pingBack + ", sessionId=" + this.sessionId + ", intentStage=" + this.intentStage + ", finalResponseGiven=" + this.finalResponseGiven + ", responseHtml=" + this.responseHtml + ", htmlJsonList=" + this.htmlJsonList + ", isCustomHtmlTemplate=" + this.isCustomHtmlTemplate + ", responseCode=" + this.responseCode + "]";
	   }

}
