package com.test.demotest.entity.aos;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "t_subrogasi_summary")
public class T_Subrogasi_Summary extends BaseEnity<String>{

    @Id
    @Column(name = "id",unique = true,length = 60)
    private String id = UUID.randomUUID().toString();
    @Column(name = "line_no")
    private Integer lineNo;
    @Column(name = "nominal_subrogasi_pokok",precision = 18,scale = 2)
    private Double nominalSubrogasiPokok;
    @Column(name = "nominal_subrogasi_bunga",precision = 18,scale = 2)
    private Double nominalSubrogasiBunga;
    @Column(name = "nominal_subrogasi_denda",precision = 18,scale = 2)
    private Double nominalSubrogasiDenda; 
    @Column(name = "nominal_subrogasi_lebih",precision = 18,scale = 2)
    private Double nominalSubrogasLebih;
    @Column(name = "nominal_pajak",precision = 18,scale = 2)
    private Double nominalPajak;
    @Column(name="nominal_fee_gross",precision = 18,scale = 2)
    private Double nominalFeeGross;
    @Column(name = "nominal_fee_net",precision = 18,scale =2)
    private Double nominalFeeNet;
    @Column(name="nominal_subrogasi_total",precision = 18,scale = 2)
    private Double nominalSubrogasiTotal;
    @Column(name = "tanggal_jurnal")
    private Date tanggalJurnal;
    @Column(name = "no_jurnal")
    private String noJurnal;
    @Column(name = "tgl_nota_kredit" )
    private Date tglNotaKredit;
    @Column(name = "collecting_agent")
    private String collectingAgent;
    @Column(name = "biaya_rekonsiliasi",precision = 18, scale = 2)
    private Double biayaRekonsiliasi;
    @Column(name="no_nota_kredit",length = 50)
    private String noNotaKredit;
    @Column(name = "m_rekening_giro",length = 50)
    private String mRekeningGiro;
    @Column(name = "is_netting")
    private Byte isNetting;
    @Column(name = "status",length = 5)
    private String status;
    @Column(name="remark",length = 40)
    private String remark;
    @Column(name = "jenis_transaksi",length = 15)
    private String jenisTransaksi;
    @Column(name = "kode_bank",length = 10)
    private String kodeBank;
    @Column(name = "kode_cabang_askrindo",length = 5)
    private String kodeCabangAskrindo;

    @ManyToOne
    @JoinColumn(name = "id_subrogasi")
    @JsonManagedReference
    private T_Subrogasi subrogasiId;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getLineNo() {
        return lineNo;
    }
    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }
    public Double getNominalSubrogasiPokok() {
        return nominalSubrogasiPokok;
    }
    public void setNominalSubrogasiPokok(Double nominalSubrogasiPokok) {
        this.nominalSubrogasiPokok = nominalSubrogasiPokok;
    }
    public Double getNominalSubrogasiBunga() {
        return nominalSubrogasiBunga;
    }
    public void setNominalSubrogasiBunga(Double nominalSubrogasiBunga) {
        this.nominalSubrogasiBunga = nominalSubrogasiBunga;
    }
    public Double getNominalSubrogasiDenda() {
        return nominalSubrogasiDenda;
    }
    public void setNominalSubrogasiDenda(Double nominalSubrogasiDenda) {
        this.nominalSubrogasiDenda = nominalSubrogasiDenda;
    }
    public Double getNominalSubrogasLebih() {
        return nominalSubrogasLebih;
    }
    public void setNominalSubrogasLebih(Double nominalSubrogasLebih) {
        this.nominalSubrogasLebih = nominalSubrogasLebih;
    }
    public Double getNominalPajak() {
        return nominalPajak;
    }
    public void setNominalPajak(Double nominalPajak) {
        this.nominalPajak = nominalPajak;
    }
    public Double getNominalFeeGross() {
        return nominalFeeGross;
    }
    public void setNominalFeeGross(Double nominalFeeGross) {
        this.nominalFeeGross = nominalFeeGross;
    }
    public Double getNominalFeeNet() {
        return nominalFeeNet;
    }
    public void setNominalFeeNet(Double nominalFeeNet) {
        this.nominalFeeNet = nominalFeeNet;
    }
    public Double getNominalSubrogasiTotal() {
        return nominalSubrogasiTotal;
    }
    public void setNominalSubrogasiTotal(Double nominalSubrogasiTotal) {
        this.nominalSubrogasiTotal = nominalSubrogasiTotal;
    }
    public Date getTanggalJurnal() {
        return tanggalJurnal;
    }
    public void setTanggalJurnal(Date tanggalJurnal) {
        this.tanggalJurnal = tanggalJurnal;
    }
    public String getNoJurnal() {
        return noJurnal;
    }
    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }
    public Date getTglNotaKredit() {
        return tglNotaKredit;
    }
    public void setTglNotaKredit(Date tglNotaKredit) {
        this.tglNotaKredit = tglNotaKredit;
    }
    public String getCollectingAgent() {
        return collectingAgent;
    }
    public void setCollectingAgent(String collectingAgent) {
        this.collectingAgent = collectingAgent;
    }
    public Double getBiayaRekonsiliasi() {
        return biayaRekonsiliasi;
    }
    public void setBiayaRekonsiliasi(Double biayaRekonsiliasi) {
        this.biayaRekonsiliasi = biayaRekonsiliasi;
    }
    public String getNoNotaKredit() {
        return noNotaKredit;
    }
    public void setNoNotaKredit(String noNotaKredit) {
        this.noNotaKredit = noNotaKredit;
    }
    public String getmRekeningGiro() {
        return mRekeningGiro;
    }
    public void setmRekeningGiro(String mRekeningGiro) {
        this.mRekeningGiro = mRekeningGiro;
    }
    public Byte getIsNetting() {
        return isNetting;
    }
    public void setIsNetting(Byte isNetting) {
        this.isNetting = isNetting;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getJenisTransaksi() {
        return jenisTransaksi;
    }
    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
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
    public T_Subrogasi getSubrogasiId() {
        return subrogasiId;
    }
    public void setSubrogasiId(T_Subrogasi subrogasiId) {
        this.subrogasiId = subrogasiId;
    }

    

}
