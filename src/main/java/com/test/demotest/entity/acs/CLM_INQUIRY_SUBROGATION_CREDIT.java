package com.test.demotest.entity.acs;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
public class CLM_INQUIRY_SUBROGATION_CREDIT {
    @Id
    @Column(name = "REGISTRATION_ID", length = 16, nullable = false)
    private String registrationId ;
    @Column(name = "ANALYSIS_ID", length = 16,nullable = false)
    private String analysisId ;
    @Column(name="PRODUCT_ID",length=4, nullable = false)
    private String productId;
    @Column(name="DEBITUR_ID",length = 16)
    private String debiturId ;
    @Column(name="COLLECTION_STATUS",length = 3,nullable = false)
    private String collectionStatus;
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;
    @Column(name="DEBITUR_NAME",length = 255,nullable = false)
    private String debiturName;
    @Column(name="CREDIT_NO", length = 50)
    private String creditNo;
    @Column(name="SUB_PRODUCT_ID",length = 4)
    private String subProductId;
    @Column(name = "PRODUCT_NAME", length = 3,nullable = false)
    private String productName;
    @Column(name = "CLAIM_NO", length = 30,nullable = false)
    private String claimNo;
    @Column(name = "TXT_CUSTOMER_NAME",length = 2048)
    private String txtCustomerName;
    @Column(name="AMT_CASH_COLLATERAL", length = 17, scale = 2,precision = 38)
    private Double amtCashCollateral;
    @Column(name = "AMT_NONCASH_COLLATERAL", length = 17, scale = 2,precision = 38)
    private Double amtNonCashCollateral;
    @Column(name = "AMT_CLAIM_PAYMENT",length = 9, scale = 2,precision = 19)
    private Double amtClaimPayment;
    @Column(name = "AMT_RECOVERY", length = 9, scale = 2,precision = 18,nullable = false)
    private Double amtRecovery;
    @Column(name = "AMT_SUBROGRATION",length = 9,scale = 2,precision = 18,nullable = false)
    private Double amtSubrogation;
    @Column(name = "AMT_OS",length = 9,scale = 2,precision = 18,nullable = false)
    private Double amtOs;
    @Column(name = "POLICY_NO",length = 30)
    private String policyNo;
    @Column(name = "AGE_CLAIM",length = 4,precision = 10)
    private Integer ageClaim;

    public Double getAmtSubrogation() {
        return amtSubrogation;
    }
    public void setAmtSubrogation(Double amtSubrogation) {
        this.amtSubrogation = amtSubrogation;
    }
    @Column(name = "IS_COLLATERAL",length = 30)
    private Character isCollateral;
    @Column(name = "IS_CASH_COLLATERAL",length = 30)
    private Character isCashCollateral;
    @Column(name = "IS_NONCASH_COLLATERAL",length = 30)
    private Character isNonCashCollateral;
    @Column(name = "IS_POTENTIAL_SHS",length = 1)
    private Character isPotentialShs;
    @Column(name = "CREDIT_ACCOUNT_NO",length = 50)
    private String creditAccountNo;
    @Column(name="MAIN_PRODUCT_ID",length=4)
    private String mainProductId;
    @Column(name = "PARTNER_TYPE_ID",length = 50)
    private String partnerTypeId;
    @Column(name="BP_UNIT_CODE",length = 10)
    private String bpUnitCode;
    @Column(name = "CREDIT_AGREEMENT_NO",length = 50)
    private String creditAgreementNo;
    @Column(name = "CUSTOMER_ID",length = 16)
    private String customerId ;
    @Column(name = "STGR_DATE")
    private Date stgrDate;
    @Column(name = "BRANCH_ID",length = 6,nullable = false)
    private String branchId;
    @Column(name = "TRANSFER_BRANCH_ID",length = 6)
    private String transferBranchId;
    @Column(name = "JOURNAL_PAID_DATE")
    private Date journalPaidDate;
    @Column(name = "PAYMENT_STATUS",length = 30)
    private String paymentStatus;
    @Column(name="IS_ENDORSED")
    private String isEndorsed;

    
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
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getDebiturId() {
        return debiturId;
    }
    public void setDebiturId(String debiturId) {
        this.debiturId = debiturId;
    }
    public String getCollectionStatus() {
        return collectionStatus;
    }
    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
    public Date getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getDebiturName() {
        return debiturName;
    }
    public void setDebiturName(String debiturName) {
        this.debiturName = debiturName;
    }
    public String getCreditNo() {
        return creditNo;
    }
    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }
    public String getSubProductId() {
        return subProductId;
    }
    public void setSubProductId(String subProductId) {
        this.subProductId = subProductId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getClaimNo() {
        return claimNo;
    }
    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }
    public String getTxtCustomerName() {
        return txtCustomerName;
    }
    public void setTxtCustomerName(String txtCustomerName) {
        this.txtCustomerName = txtCustomerName;
    }
    public Double getAmtCashCollateral() {
        return amtCashCollateral;
    }
    public void setAmtCashCollateral(Double amtCashCollateral) {
        this.amtCashCollateral = amtCashCollateral;
    }
    public Double getAmtNonCashCollateral() {
        return amtNonCashCollateral;
    }
    public void setAmtNonCashCollateral(Double amtNonCashCollateral) {
        this.amtNonCashCollateral = amtNonCashCollateral;
    }
    public Double getAmtClaimPayment() {
        return amtClaimPayment;
    }
    public void setAmtClaimPayment(Double amtClaimPayment) {
        this.amtClaimPayment = amtClaimPayment;
    }
    public Double getAmtRecovery() {
        return amtRecovery;
    }
    public void setAmtRecovery(Double amtRecovery) {
        this.amtRecovery = amtRecovery;
    }
    public Double getAmtOs() {
        return amtOs;
    }
    public void setAmtOs(Double amtOs) {
        this.amtOs = amtOs;
    }
    public String getPolicyNo() {
        return policyNo;
    }
    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }
    public Integer getAgeClaim() {
        return ageClaim;
    }
    public void setAgeClaim(Integer ageClaim) {
        this.ageClaim = ageClaim;
    }
    public Character getIsCollateral() {
        return isCollateral;
    }
    public void setIsCollateral(Character isCollateral) {
        this.isCollateral = isCollateral;
    }
    public Character getIsCashCollateral() {
        return isCashCollateral;
    }
    public void setIsCashCollateral(Character isCashCollateral) {
        this.isCashCollateral = isCashCollateral;
    }
    public Character getIsNonCashCollateral() {
        return isNonCashCollateral;
    }
    public void setIsNonCashCollateral(Character isNonCashCollateral) {
        this.isNonCashCollateral = isNonCashCollateral;
    }
    public Character getIsPotentialShs() {
        return isPotentialShs;
    }
    public void setIsPotentialShs(Character isPotentialShs) {
        this.isPotentialShs = isPotentialShs;
    }
    public String getCreditAccountNo() {
        return creditAccountNo;
    }
    public void setCreditAccountNo(String creditAccountNo) {
        this.creditAccountNo = creditAccountNo;
    }
    public String getMainProductId() {
        return mainProductId;
    }
    public void setMainProductId(String mainProductId) {
        this.mainProductId = mainProductId;
    }
    public String getPartnerTypeId() {
        return partnerTypeId;
    }
    public void setPartnerTypeId(String partnerTypeId) {
        this.partnerTypeId = partnerTypeId;
    }
    public String getBpUnitCode() {
        return bpUnitCode;
    }
    public void setBpUnitCode(String bpUnitCode) {
        this.bpUnitCode = bpUnitCode;
    }
    public String getCreditAgreementNo() {
        return creditAgreementNo;
    }
    public void setCreditAgreementNo(String creditAgreementNo) {
        this.creditAgreementNo = creditAgreementNo;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public Date getStgrDate() {
        return stgrDate;
    }
    public void setStgrDate(Date stgrDate) {
        this.stgrDate = stgrDate;
    }
    public String getBranchId() {
        return branchId;
    }
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    public String getTransferBranchId() {
        return transferBranchId;
    }
    public void setTransferBranchId(String transferBranchId) {
        this.transferBranchId = transferBranchId;
    }
    public Date getJournalPaidDate() {
        return journalPaidDate;
    }
    public void setJournalPaidDate(Date journalPaidDate) {
        this.journalPaidDate = journalPaidDate;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public String getIsEndorsed() {
        return isEndorsed;
    }
    public void setIsEndorsed(String isEndorsed) {
        this.isEndorsed = isEndorsed;
    }

    
    
    

    
}
