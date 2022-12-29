package com.askrindo.aossubrogasi.entity.acs;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CLM_PRELIMINARY  {

    @Id
    @Column(name = "PRELIMINARY_ID", length = 16,nullable = false)
    private String preliminaryId;
    @Column(name = "REGISTRATION_ID",length = 16,nullable = false)
    private String registrationId;
    @Column(name="DOC_TYPE_ID",length = 4,precision = 10)
    private Integer docTypeId;
    @Column(name = "DOC_STATUS",length = 2,precision = 5)
    private Integer docStatus;
    @Column(name="TASK_ID",length = 16)
    private String taskId;
    @Column(name = "POLICY_ID",length = 16,nullable = false)
    private String policyId;
    @Column(name="LOST_ADJUSTER_ID",length=16)
    private String lostAdjusterId;
    @Column(name="PREV_PRELIMINARY_ID",length=16)
    private String prevPreliminaryId;
    @Column(name = "ADENDUM_COUNTER",length = 4,precision = 10)
    private Integer adendumCounter;
    @Column(name = "IS_HAS_ADENDUM",length = 1)
    private Character isHasAdendum;
    @Column(name = "IS_TBA_ESTIMATION",length = 1)
    private Character isTbaEstimation;
    @Column(name = "PRELIMINARY_NO",length = 60)
    private String preliminaryNo;
    @Column(name="VERSION",length=4,precision=10)
    private Integer Version;
    @Column(name = "IS_NONPROP_TREATY",length = 1)
    private Character isNonpropTreaty;
    
    public String getPreliminaryId() {
        return preliminaryId;
    }
    public void setPreliminaryId(String preliminaryId) {
        this.preliminaryId = preliminaryId;
    }
    public String getRegistrationId() {
        return registrationId;
    }
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
    public Integer getDocTypeId() {
        return docTypeId;
    }
    public void setDocTypeId(Integer docTypeId) {
        this.docTypeId = docTypeId;
    }
    public Integer getDocStatus() {
        return docStatus;
    }
    public void setDocStatus(Integer docStatus) {
        this.docStatus = docStatus;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getPolicyId() {
        return policyId;
    }
    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
    public String getLostAdjusterId() {
        return lostAdjusterId;
    }
    public void setLostAdjusterId(String lostAdjusterId) {
        this.lostAdjusterId = lostAdjusterId;
    }
    public String getPrevPreliminaryId() {
        return prevPreliminaryId;
    }
    public void setPrevPreliminaryId(String prevPreliminaryId) {
        this.prevPreliminaryId = prevPreliminaryId;
    }
    public Integer getAdendumCounter() {
        return adendumCounter;
    }
    public void setAdendumCounter(Integer adendumCounter) {
        this.adendumCounter = adendumCounter;
    }
    public Character getIsHasAdendum() {
        return isHasAdendum;
    }
    public void setIsHasAdendum(Character isHasAdendum) {
        this.isHasAdendum = isHasAdendum;
    }
    public Character getIsTbaEstimation() {
        return isTbaEstimation;
    }
    public void setIsTbaEstimation(Character isTbaEstimation) {
        this.isTbaEstimation = isTbaEstimation;
    }
    public String getPreliminaryNo() {
        return preliminaryNo;
    }
    public void setPreliminaryNo(String preliminaryNo) {
        this.preliminaryNo = preliminaryNo;
    }
    public Integer getVersion() {
        return Version;
    }
    public void setVersion(Integer version) {
        Version = version;
    }
    public Character getIsNonpropTreaty() {
        return isNonpropTreaty;
    }
    public void setIsNonpropTreaty(Character isNonpropTreaty) {
        this.isNonpropTreaty = isNonpropTreaty;
    }

   



}
