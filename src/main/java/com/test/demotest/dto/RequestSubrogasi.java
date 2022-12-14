package com.test.demotest.dto;

import java.util.Date;

public class RequestSubrogasi {
    private String noRekening;
    private String noPk;
    private String noKlaim;
    private String noGiro;
    private String noTransaksi;
    private Double nilaiRecoveries;
    private Integer covRatio;
    private Date tglBayar;
    private String remark;
    private Integer counterAngsuran;
    private String kodeBank;
    private String kodeCabangAskrindo;
    private String jenisTransaksi;
    
    public String getNoRekening() {
        return noRekening;
    }
    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }
    public String getNoPk() {
        return noPk;
    }
    public void setNoPk(String noPk) {
        this.noPk = noPk;
    }
    public String getNoKlaim() {
        return noKlaim;
    }
    public void setNoKlaim(String noKlaim) {
        this.noKlaim = noKlaim;
    }
    public String getNoGiro() {
        return noGiro;
    }
    public void setNoGiro(String noGiro) {
        this.noGiro = noGiro;
    }
    public String getNoTransaksi() {
        return noTransaksi;
    }
    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }
    public Double getNilaiRecoveries() {
        return nilaiRecoveries;
    }
    public void setNilaiRecoveries(Double nilaiRecoveries) {
        this.nilaiRecoveries = nilaiRecoveries;
    }
    public Integer getCovRatio() {
        return covRatio;
    }
    public void setCovRatio(Integer covRatio) {
        this.covRatio = covRatio;
    }
    public Date getTglBayar() {
        return tglBayar;
    }
    public void setTglBayar(Date tglBayar) {
        this.tglBayar = tglBayar;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getCounterAngsuran() {
        return counterAngsuran;
    }
    public void setCounterAngsuran(Integer counterAngsuran) {
        this.counterAngsuran = counterAngsuran;
    }
    public String getKodeBank() {
        return kodeBank;
    }
    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }
    public String getKodeCabangAskrindo() {
        return kodeCabangAskrindo;
    }
    public void setKodeCabangAskrindo(String kodeCabangAskrindo) {
        this.kodeCabangAskrindo = kodeCabangAskrindo;
    }
    public String getJenisTransaksi() {
        return jenisTransaksi;
    }
    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }
}
