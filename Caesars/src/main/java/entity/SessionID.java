package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(
		   ignoreUnknown = true
		)

public class SessionID {
	

	   private String _id;
	   private String beginTime;
	   private String endTime;
	   private String sessionId;
	   private String vaName;
	   private String vcmId;
	   private String isStillActive;
	   private String channelMaster;
	   private String vaid;
	   private String catId;
	   private String vrmId;
	   private String isTransfer;
	   private String isAuthorized;
	   private String reasonforlivetransfer;
	   private String listofIntents;
	   private String systemSlots;
	   private String metadata;
	   private String VaAvatarName;
	   private String langid;
	   private Intent[] intentdata;

	   public String getId() {
	      return this._id;
	   }

	   public void setId(String id) {
	      this._id = id;
	   }

	   public String getBeginTime() {
	      return this.beginTime;
	   }

	   public void setBeginTime(String beginTime) {
	      this.beginTime = beginTime;
	   }

	   public String getEndTime() {
	      return this.endTime;
	   }

	   public void setEndTime(String endTime) {
	      this.endTime = endTime;
	   }

	   public String getSessionId() {
	      return this.sessionId;
	   }

	   public void setSessionId(String sessionId) {
	      this.sessionId = sessionId;
	   }

	   public String getVaName() {
	      return this.vaName;
	   }

	   public void setVaName(String vaName) {
	      this.vaName = vaName;
	   }

	   public String getVcmId() {
	      return this.vcmId;
	   }

	   public void setVcmId(String vcmId) {
	      this.vcmId = vcmId;
	   }

	   public String getIsStillActive() {
	      return this.isStillActive;
	   }

	   public void setIsStillActive(String isStillActive) {
	      this.isStillActive = isStillActive;
	   }

	   public String getChannelMaster() {
	      return this.channelMaster;
	   }

	   public void setChannelMaster(String channelMaster) {
	      this.channelMaster = channelMaster;
	   }

	   public String getVaid() {
	      return this.vaid;
	   }

	   public void setVaid(String vaid) {
	      this.vaid = vaid;
	   }

	   public String getCatId() {
	      return this.catId;
	   }

	   public void setCatId(String catId) {
	      this.catId = catId;
	   }

	   public String getVrmId() {
	      return this.vrmId;
	   }

	   public void setVrmId(String vrmId) {
	      this.vrmId = vrmId;
	   }

	   public String getIsTransfer() {
	      return this.isTransfer;
	   }

	   public void setIsTransfer(String isTransfer) {
	      this.isTransfer = isTransfer;
	   }

	   public String getIsAuthorized() {
	      return this.isAuthorized;
	   }

	   public void setIsAuthorized(String isAuthorized) {
	      this.isAuthorized = isAuthorized;
	   }

	   public String getReasonforlivetransfer() {
	      return this.reasonforlivetransfer;
	   }

	   public void setReasonforlivetransfer(String reasonforlivetransfer) {
	      this.reasonforlivetransfer = reasonforlivetransfer;
	   }

	   public String getListofIntents() {
	      return this.listofIntents;
	   }

	   public void setListofIntents(String listofIntents) {
	      this.listofIntents = listofIntents;
	   }

	   public String getSystemSlots() {
	      return this.systemSlots;
	   }

	   public void setSystemSlots(String systemSlots) {
	      this.systemSlots = systemSlots;
	   }

	   public String getMetadata() {
	      return this.metadata;
	   }

	   public void setMetadata(String metadata) {
	      this.metadata = metadata;
	   }

	   public String getVaAvatarName() {
	      return this.VaAvatarName;
	   }

	   public void setVaAvatarName(String vaAvatarName) {
	      this.VaAvatarName = vaAvatarName;
	   }

	   public String getLangid() {
	      return this.langid;
	   }

	   public void setLangid(String langid) {
	      this.langid = langid;
	   }

	   public Intent[] getIntentdata() {
	      return this.intentdata;
	   }

	   public void setIntentdata(Intent[] intentdata) {
	      this.intentdata = intentdata;
	   }

	
}
