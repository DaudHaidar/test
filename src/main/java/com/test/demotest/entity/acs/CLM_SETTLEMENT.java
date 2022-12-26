package com.test.demotest.entity.acs;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.test.demotest.entity.aos.BaseEnity;

@Entity
@Table(name = "CLAIM.CLM_SETTLEMENT")
public class CLM_SETTLEMENT extends BaseEnity<String> {
    @Id
    @Column(name = "SETTLEMENT_ID",length = 16,nullable = false)
    private String settlementId;
    @Column(name = "REGISTRATION_ID",length = 16,nullable = false)
    private String registrationId ;
    @Column(name="DOC_STATUS",length=2,precision = 5,nullable = false)
    private Integer docStatus;
    @Column(name="DOC_TYPE_ID",length = 4,precision = 10)
    private Integer docTypeId;
    @Column(name = "TASK_ID",length = 16)
    private String taskId;
    @Column(name = "PRELIMINARY_ID",length = 16, nullable = false)
    private String preliminaryId;
    @Column(name = "SETTLEMENT_IDX",length = 4,precision = 10)
    private Integer settlementIdx;
    @Column(name = "CLM_SETTLE_TYPE",length = 30)
    private String clmSettleType;
    @Column(name = "CLM_COMPLETION_TYPE",length = 30)
    private String clmCompletionType;
    @Column(name = "PAYMENT_OPTION",length = 30)
    private String paymentOption;
    @Column(name="BILLED_TO",length = 16)
    private String billedTo;
    @Column(name = "POSTING_DATE",length = 3,precision = 10)
    private Date postingDate;
    @Column(name = "ISSUED_DATE",length = 3,precision = 10)
    private Date issuedDate;
    @Column(name = "SETTLEMENT_NO", length = 60)
    private String settlementNo;
    @Column(name="IS_NOTE_GENERATED",length = 1)
    private Character isNoteGenerated;
    @Column(name = "TXT_DESCRIPTION",length = 200)
    private String txtDescription;
    @Column(name = "VERSION",precision = 10)
    private Integer version;
    @Column(name ="IS_NONPROP_TREATY",length = 1 )
    private Character isNonpropTreaty;
    @Column(name="PRINT_DATE",length = 8,scale = 3,precision = 23)
    private Date printDate;
    @Column(name="PRINT_BY",length=50)
    private String printBy;
    @Column(name = "PRINT_COUNTER",length = 4,precision = 10)
    private Integer printCounter;


    public String getSettlementId() {
        return settlementId;
    }
    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }
    public String getRegistrationId() {
        return registrationId;
    }
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
    public Integer getDocStatus() {
        return docStatus;
    }
    public void setDocStatus(Integer docStatus) {
        this.docStatus = docStatus;
    }
    public Integer getDocTypeId() {
        return docTypeId;
    }
    public void setDocTypeId(Integer docTypeId) {
        this.docTypeId = docTypeId;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getPremliminaryId() {
        return preliminaryId;
    }
    public void setPremliminaryId(String preliminaryId) {
        this.preliminaryId = preliminaryId;
    }
    public Integer getSettlementIdx() {
        return settlementIdx;
    }
    public void setSettlementIdx(Integer settlementIdx) {
        this.settlementIdx = settlementIdx;
    }
    public String getClmSettleType() {
        return clmSettleType;
    }
    public void setClmSettleType(String clmSettleType) {
        this.clmSettleType = clmSettleType;
    }
    public String getClmCompletionType() {
        return clmCompletionType;
    }
    public void setClmCompletionType(String clmCompletionType) {
        this.clmCompletionType = clmCompletionType;
    }
    public String getPaymentOption() {
        return paymentOption;
    }
    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }
    public String getBilledTo() {
        return billedTo;
    }
    public void setBilledTo(String billedTo) {
        this.billedTo = billedTo;
    }
    public Date getPostingDate() {
        return postingDate;
    }
    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }
    public Date getIssuDate() {
        return issuedDate;
    }
    public void setIssuDate(Date issuDate) {
        this.issuedDate = issuDate;
    }
    public String getSettlementNo() {
        return settlementNo;
    }
    public void setSettlementNo(String settlementNo) {
        this.settlementNo = settlementNo;
    }
    public Character getIsNoteGenerated() {
        return isNoteGenerated;
    }
    public void setIsNoteGenerated(Character isNoteGenerated) {
        this.isNoteGenerated = isNoteGenerated;
    }
    public String getTxtDescription() {
        return txtDescription;
    }
    public void setTxtDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public Character getIsNonpropTreaty() {
        return isNonpropTreaty;
    }
    public void setIsNonpropTreaty(Character isNonpropTreaty) {
        this.isNonpropTreaty = isNonpropTreaty;
    }
    public Date getPrintDate() {
        return printDate;
    }
    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }
    public String getPrintBy() {
        return printBy;
    }
    public void setPrintBy(String printBy) {
        this.printBy = printBy;
    }
    public Integer getPrintCounter() {
        return printCounter;
    }
    public void setPrintCounter(Integer printCounter) {
        this.printCounter = printCounter;
    }

    @Override
    public String toString(){
        return "{" +
        "settlementId='" + settlementId + '\'' +
        ", version ='" + version + '\'' +
        ", registrationId='" + registrationId + '\'' +
        ", createdDate='" + createdDate + '\'' +
        ", createdBy='" + createdBy + '\'' +

        '}';
    }
    
    

}
