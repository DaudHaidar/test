package com.test.demotest.entity.acs;

import java.util.Date;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.test.demotest.entity.aos.BaseEnity;

@Entity
@Table(name = "CLM_SETTLEMENT_SUMMARY")
public class CLM_SETTLEMENT_SUMMARY extends BaseEnity<String>{

    @Id
    @Column(name = "SETTLE_SUMMARY_ID",length = 16,nullable = false)
    private String settleSummaryId;
    @ManyToOne
    @JoinColumn(name = "SETTLEMENT_ID")
    private CLM_SETTLEMENT settlementId;
    @Column(name = "CURRENCY_CODE", length = 3,nullable = false)
    private String currencyCode;
    @Column(name = "AMT_CLAIM",length = 9,scale = 2,precision = 18)
    private Double amtClaim;
    @Column(name = "AMT_JP", length = 9,scale = 2,precision = 18)
    private Double amtJp;
    @Column(name="SETTLE_NOTE_NO",length=30)
    private String settleNoteNo;
    @Column(name = "JP_NOTE_NO",length = 30)
    private String jpNoteNo;
    @Column(name = "RATE_IDR",length = 9,scale = 4,precision = 18)
    private Double rateIdr;
    @Column(name = "VERSION",length = 4,precision = 10)
    private Integer version;
    @Column(name = "IS_PAIDOFF",length = 1)
    private Character isPaidOff;
    @Column(name = "AMT_PAYMENT",length = 9,scale = 2,precision = 18)
    private Double amtPayment;
    @Column(name="PAYMENT_STATUS",length = 30)
    private String paymentStatus;
    @Column(name = "BANK_NOTE_NUMBER",length = 50)
    private String bankNoteNumber;
    @Column(name = "PAYMENT_DATE",length = 8,scale = 3,precision = 23)
    private Date paymentDate;



    
    public String getSettleSummaryId() {
        return settleSummaryId;
    }
    public void setSettleSummaryId(String settleSummaryId) {
        this.settleSummaryId = settleSummaryId;
    }
    public CLM_SETTLEMENT getSettlementId() {
        return settlementId;
    }
    public void setSettlementId(CLM_SETTLEMENT settlementId) {
        this.settlementId = settlementId;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public Double getAmtClaim() {
        return amtClaim;
    }
    public void setAmtClaim(Double amtClaim) {
        this.amtClaim = amtClaim;
    }
    public Double getAmtJp() {
        return amtJp;
    }
    public void setAmtJp(Double amtJp) {
        this.amtJp = amtJp;
    }
    public String getSettleNoteNo() {
        return settleNoteNo;
    }
    public void setSettleNoteNo(String settleNoteNo) {
        this.settleNoteNo = settleNoteNo;
    }
    public String getJpNoteNo() {
        return jpNoteNo;
    }
    public void setJpNoteNo(String jpNoteNo) {
        this.jpNoteNo = jpNoteNo;
    }
    public Double getRateIdr() {
        return rateIdr;
    }
    public void setRateIdr(Double rateIdr) {
        this.rateIdr = rateIdr;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public Character getIsPaidOff() {
        return isPaidOff;
    }
    public void setIsPaidOff(Character isPaidOff) {
        this.isPaidOff = isPaidOff;
    }
    public Double getAmtPayment() {
        return amtPayment;
    }
    public void setAmtPayment(Double amtPayment) {
        this.amtPayment = amtPayment;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public String getBankNoteNumber() {
        return bankNoteNumber;
    }
    public void setBankNoteNumber(String bankNoteNumber) {
        this.bankNoteNumber = bankNoteNumber;
    }
    public Date getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public Stream stream() {
        return null;
    }

    

    
}
