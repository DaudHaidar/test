package com.askrindo.aossubrogasi.entity.acs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.askrindo.aossubrogasi.entity.aos.BaseEnity;

@Entity
@Table(name = "CLAIM.CLM_RECOV_PAYMENT")
public class CLM_RECOV_PAYMENT extends BaseEnity<String>{

    @Id
    @Column(name = "PAYMENT_ID",length = 16, nullable = false)
    private String paymentId;
    @Column(name = "RECOVERY_DTL_ID",length = 16)
    private String recoveryDtlId;
    @Column(name = "IS_INCLUDE_PPH21",length = 1)
    private Character isIncludePph21;
    @Column(name = "IS_INCLUDE_PPH23",length = 1)
    private Character isIncludepph23;
    @Column(name = "PCT_PPH23",length = 5,scale = 6,precision = 9)
    private Double pctPph23;
    @Column(name = "PCT_FEE",length = 5,scale = 6,precision = 9)
    private Double pctFee;
    @Column(name = "PCT_RECONCILIATION", length = 5,scale = 6,precision = 9)
    private Double pctReconciliation;
    @Column(name = "AMT_FEE",length = 9,scale = 2,precision = 18)
    private Double amtFee;
    @Column(name = "AMT_RECOVERY",length = 9,scale = 2,precision = 18)
    private Double amtRecovery;
    @Column(name = "AMT_RECONCILIATION",length = 9,scale = 2,precision = 18)
    private Double amtReconciliation;
    @Column(name ="AMT_RECOVERY_SHARE",length = 9,scale = 2,precision = 18)
    private Double amtRecoveryShare;
    @Column(name="BANK_NOTE_DATE",length = 3,precision = 10)
    private Date bankNoteDate;
    @Column(name = "CREDIT_NOTE_DATE",length = 3,precision = 10)
    private Date creditNoDate;
    @Column(name = "BANK_NAME",length = 200)
    private String bankName;
    @Column(name = "BANK_NOTE_NO",length = 50)
    private String bankNoteNo;
    @Column(name = "CREDIT_NOTE_NO",length = 50)
    private String creditNoteNo;
    @Column(name = "VERSION",length = 4)
    private Integer version;
    @Column(name="IS_PAIDOFF_FEE",length = 1)
    private Character isPaidOffFee;
    @Column(name = "PAIDOFFDATE",length = 3,precision = 10)
    private Date paidOffDate;
    @Column(name = "TRX_FEE_ID",length = 16)
    private String trxFeeId;
    @Column(name="REGISTRATION_ID",length = 16)
    private String registrationId;
    @Column(name = "ANALYSIS_ID",length = 16)
    private String analysisId;
    @Column(name="SETTLEMENT_ID",length = 16)
    private String settlementId;
    @Column(name = "COLLECT_AGENT_ID",length = 16)
    private String collectAgentId;
    @Column(name="COLLECT_AGENT_NAME",length = 200)
    private String collectAgentName;
    @Column(name = "REVERSE_MULTIPLIER",length = 2,precision = 5)
    private Integer reverseMultiplier;
    @Column(name="IS_NETTING",length = 1)
    private Character isNetting;
    @Column(name="LEGACY_NO",length = 200)
    private String legacyNo;
    @Column(name = "AMT_SHS_PREV",length = 9,scale = 2,precision = 18)
    private Double amtShsPrev;
    @Column(name = "AMT_SHS_AFTER",length = 9,scale = 2,precision = 18)
    private Double amtShsAfter;
    @Column(name="BRANCH_ID",length=6)
    private String branchId;
    @Column(name = "IS_PAIDOFF_FEE_RECONCILIATION",length = 1)
    private Character isPaidOffReconciliation;
    @Column(name = "PAIDOFFDATE_RECONCILIATION", length = 3,precision = 10)
    private Date paidOffDateReconciliation;
    @Column(name = "TRX_FEE_RECONCILIATION_ID",length = 16)
    private String trxFeeReconciliationId;
    @Column(name = "RECOV_PLAN_NO",length = 50)
    private String recovPlanNo;
    @Column(name = "RECOVERY_NO",length = 50)
    private String recoveryNo;
    @Column(name = "RECOVERY_NO_ROOT",length = 50)
    private String recoveryNoRoot;
    @Column(name = "RECOVERY_TYPE",length = 30)
    private String recoveryType;
    @Column(name="PAYMENT_ID_ROOT",length = 16)
    private String paymentIdRoot;
    @Column(name = "SETTLEMENT_ID_NETTING",length = 16)
    private String settlementIdNetting;
    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    public String getRecoveryDtlId() {
        return recoveryDtlId;
    }
    public void setRecoveryDtlId(String recoveryDtlId) {
        this.recoveryDtlId = recoveryDtlId;
    }
    public Character getIsIncludePph21() {
        return isIncludePph21;
    }
    public void setIsIncludePph21(Character isIncludePph21) {
        this.isIncludePph21 = isIncludePph21;
    }
    public Character getIsIncludepph23() {
        return isIncludepph23;
    }
    public void setIsIncludepph23(Character isIncludepph23) {
        this.isIncludepph23 = isIncludepph23;
    }
    public Double getPctPph23() {
        return pctPph23;
    }
    public void setPctPph23(Double pctPph23) {
        this.pctPph23 = pctPph23;
    }
    public Double getPctFee() {
        return pctFee;
    }
    public void setPctFee(Double pctFee) {
        this.pctFee = pctFee;
    }
    public Double getPctReconciliation() {
        return pctReconciliation;
    }
    public void setPctReconciliation(Double pctReconciliation) {
        this.pctReconciliation = pctReconciliation;
    }
    public Double getAmtFee() {
        return amtFee;
    }
    public void setAmtFee(Double amtFee) {
        this.amtFee = amtFee;
    }
    public Double getAmtRecovery() {
        return amtRecovery;
    }
    public void setAmtRecovery(Double amtRecovery) {
        this.amtRecovery = amtRecovery;
    }
    public Double getAmtReconciliation() {
        return amtReconciliation;
    }
    public void setAmtReconciliation(Double amtReconciliation) {
        this.amtReconciliation = amtReconciliation;
    }
    public Double getAmtRecoveryShare() {
        return amtRecoveryShare;
    }
    public void setAmtRecoveryShare(Double amtRecoveryShare) {
        this.amtRecoveryShare = amtRecoveryShare;
    }
    public Date getBankNoteDate() {
        return bankNoteDate;
    }
    public void setBankNoteDate(Date bankNoteDate) {
        this.bankNoteDate = bankNoteDate;
    }
    public Date getCreditNoDate() {
        return creditNoDate;
    }
    public void setCreditNoDate(Date creditNoDate) {
        this.creditNoDate = creditNoDate;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBankNoteNo() {
        return bankNoteNo;
    }
    public void setBankNoteNo(String bankNoteNo) {
        this.bankNoteNo = bankNoteNo;
    }
    public String getCreditNoteNo() {
        return creditNoteNo;
    }
    public void setCreditNoteNo(String creditNoteNo) {
        this.creditNoteNo = creditNoteNo;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public Character getIsPaidOffFee() {
        return isPaidOffFee;
    }
    public void setIsPaidOffFee(Character isPaidOffFee) {
        this.isPaidOffFee = isPaidOffFee;
    }
    public Date getPaidOffDate() {
        return paidOffDate;
    }
    public void setPaidOffDate(Date paidOffDate) {
        this.paidOffDate = paidOffDate;
    }
    public String getTrxFeeId() {
        return trxFeeId;
    }
    public void setTrxFeeId(String trxFeeId) {
        this.trxFeeId = trxFeeId;
    }
    public String getRegistrationId() {
        return registrationId;
    }
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
    public String getAnalysisId() {
        return analysisId;
    }
    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }
    public String getSettlementId() {
        return settlementId;
    }
    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }
    public String getCollectAgentId() {
        return collectAgentId;
    }
    public void setCollectAgentId(String collectAgentId) {
        this.collectAgentId = collectAgentId;
    }
    public String getCollectAgentName() {
        return collectAgentName;
    }
    public void setCollectAgentName(String collectAgentName) {
        this.collectAgentName = collectAgentName;
    }
    public Integer getReverseMultiplier() {
        return reverseMultiplier;
    }
    public void setReverseMultiplier(Integer reverseMultiplier) {
        this.reverseMultiplier = reverseMultiplier;
    }
    public Character getIsNetting() {
        return isNetting;
    }
    public void setIsNetting(Character isNetting) {
        this.isNetting = isNetting;
    }
    public String getLegacyNo() {
        return legacyNo;
    }
    public void setLegacyNo(String legacyNo) {
        this.legacyNo = legacyNo;
    }
    public Double getAmtShsPrev() {
        return amtShsPrev;
    }
    public void setAmtShsPrev(Double amtShsPrev) {
        this.amtShsPrev = amtShsPrev;
    }
    public Double getAmtShsAfter() {
        return amtShsAfter;
    }
    public void setAmtShsAfter(Double amtShsAfter) {
        this.amtShsAfter = amtShsAfter;
    }
    public String getBranchId() {
        return branchId;
    }
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    public Character getIsPaidOffReconciliation() {
        return isPaidOffReconciliation;
    }
    public void setIsPaidOffReconciliation(Character isPaidOffReconciliation) {
        this.isPaidOffReconciliation = isPaidOffReconciliation;
    }
    public Date getPaidOffDateReconciliation() {
        return paidOffDateReconciliation;
    }
    public void setPaidOffDateReconciliation(Date paidOffDateReconciliation) {
        this.paidOffDateReconciliation = paidOffDateReconciliation;
    }
    public String getTrxFeeReconciliationId() {
        return trxFeeReconciliationId;
    }
    public void setTrxFeeReconciliationId(String trxFeeReconciliationId) {
        this.trxFeeReconciliationId = trxFeeReconciliationId;
    }
    public String getRecovPlanNo() {
        return recovPlanNo;
    }
    public void setRecovPlanNo(String recovPlanNo) {
        this.recovPlanNo = recovPlanNo;
    }
    public String getRecoveryNo() {
        return recoveryNo;
    }
    public void setRecoveryNo(String recoveryNo) {
        this.recoveryNo = recoveryNo;
    }
    public String getRecoveryNoRoot() {
        return recoveryNoRoot;
    }
    public void setRecoveryNoRoot(String recoveryNoRoot) {
        this.recoveryNoRoot = recoveryNoRoot;
    }
    public String getRecoveryType() {
        return recoveryType;
    }
    public void setRecoveryType(String recoveryType) {
        this.recoveryType = recoveryType;
    }
    public String getPaymentIdRoot() {
        return paymentIdRoot;
    }
    public void setPaymentIdRoot(String paymentIdRoot) {
        this.paymentIdRoot = paymentIdRoot;
    }
    public String getSettlementIdNetting() {
        return settlementIdNetting;
    }
    public void setSettlementIdNetting(String settlementIdNetting) {
        this.settlementIdNetting = settlementIdNetting;
    }


    


}

