package com.test.demotest.entity.acs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.test.demotest.entity.aos.BaseEnity;

@Entity
@Table(name = "CLAIM.CLM_REGISTRATION_OS")
public class CLM_REGISTRATION_OS extends BaseEnity<String> {
    
    @Id
    @Column(name = "REG_OS_ID",length = 16,nullable = false)
    private String regOsId;
    @Column(name = "CURRENCY_CODE",length = 3,nullable = false)
    private String currencyCode;
    @Column(name = "REGISTRATION_ID",length = 16,nullable = false)
    private String registrationId;
    @Column(name = "CLM_OS_TYPE",length = 30)
    private String clmOstype;
    @Column(name = "CLM_STATUS",length = 30)
    private String clmStatus;
    @Column( name = "AMT_OS",length = 9,scale = 2,precision = 18,nullable = false)
    private Double amtOs;
    @Column(name = "AMT_LOSS",length = 9,scale = 2,precision = 18,nullable = false)
    private Double amtLoss;
    @Column(name="AMT_CLOSED",length = 9,scale = 2,precision = 18,nullable = false)
    private Double amtClosed;
    @Column(name="AMT_SETTLED",length = 9,scale = 2,precision = 18)
    private Double amtSettled;
    @Column(name = "IDR_RATE",length = 9,scale = 2,precision = 18)
    private double idrRate;

    

    public String getRegOsId() {
        return regOsId;
    }
    public void setRegOsId(String regOsId) {
        this.regOsId = regOsId;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getRegistrationId() {
        return registrationId;
    }
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
    public String getClmOstype() {
        return clmOstype;
    }
    public void setClmOstype(String clmOstype) {
        this.clmOstype = clmOstype;
    }
    public String getClmStatus() {
        return clmStatus;
    }
    public void setClmStatus(String clmStatus) {
        this.clmStatus = clmStatus;
    }
    public Double getAmtOs() {
        return amtOs;
    }
    public void setAmtOs(Double amtOs) {
        this.amtOs = amtOs;
    }
    public Double getAmtLoss() {
        return amtLoss;
    }
    public void setAmtLoss(Double amtLoss) {
        this.amtLoss = amtLoss;
    }
    public Double getAmtClosed() {
        return amtClosed;
    }
    public void setAmtClosed(Double amtClosed) {
        this.amtClosed = amtClosed;
    }
    public double getIdrRate() {
        return idrRate;
    }
    public void setIdrRate(double idrRate) {
        this.idrRate = idrRate;
    }
    public Double getAmtSettled() {
        return amtSettled;
    }
    public void setAmtSettled(Double amtSettled) {
        this.amtSettled = amtSettled;
    }
    
    @Override
    public String toString(){

        return "ProductInfo [REG_OS_ID=" + regOsId + ", CURRENCY_CODE=" + currencyCode + ", REGISTRATION_ID=" + registrationId + ", CLM_OS_TYPE=" + clmOstype +", CLM_STATUS=" + clmStatus + ", AMT_OS=" + amtOs + ", AMT_LOSS="+ amtLoss + ", AMT_CLOSED=" + amtClosed + ", AMT_SETTLED="+ amtSettled + ", IDR_RATE ="+ idrRate + "]";
    }

    
}
