package com.test.demotest.entity.aos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_subrogasi")
public class T_Subrogasi extends BaseEnity<String>{

    @Id
    @Column(name = "id", unique = true,length = 60)
    private String id;
    @Column(name = "no_rekening",length = 50)
    private String noRekening;
    @Column(name = "nomor_peserta",length = 50)
    private String nomorPeserta;
    @Column(name = "nominal_claim",precision = 18,scale = 2)
    private Double nominalClaim;
    @Column(name = "akumulasi_subrogasi",precision = 18,scale = 2)
    private Double akumulasiSubrogasi;
    @Column(name = "sisa_kewajiban_subrogasi",precision = 18,scale = 2)
    private Double sisaKewajibanSubrogasi;
    @Column(name = "presentasi_coverage",precision = 18,scale = 2)
    private Double presentasiCoverage;
    @Column(name = "presentase_collecting_fee",precision = 18,scale = 2)
    private Double presentaseCollectingFee;
    @Column(name = "presentase_pajak",precision = 18,scale = 2)
    private Double presentasePajak;
    @Column(name = "id_klaim",length = 60)
    private String idKlaim;
    @Column(name = "status",length = 5)
    private String status;

    @OneToMany(mappedBy = "subrogasiId")
    private List<T_Subrogasi_Summary> subrogasiSummary= new ArrayList<T_Subrogasi_Summary>();
    

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNoRekening() {
        return noRekening;
    }
    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }
    public String getNomorPeserta() {
        return nomorPeserta;
    }
    public void setNomorPeserta(String nomorPeserta) {
        this.nomorPeserta = nomorPeserta;
    }
    public Double getNominalClaim() {
        return nominalClaim;
    }
    public void setNominalClaim(Double nominalClaim) {
        this.nominalClaim = nominalClaim;
    }
    public Double getAkumulasiSubrogasi() {
        return akumulasiSubrogasi;
    }
    public void setAkumulasiSubrogasi(Double akumulasiSubrogasi) {
        this.akumulasiSubrogasi = akumulasiSubrogasi;
    }
    public Double getSisaKewajibanSubrogasi() {
        return sisaKewajibanSubrogasi;
    }
    public void setSisaKewajibanSubrogasi(Double sisaKewajibanSubrogasi) {
        this.sisaKewajibanSubrogasi = sisaKewajibanSubrogasi;
    }
    public Double getPresentasiCoverage() {
        return presentasiCoverage;
    }
    public void setPresentasiCoverage(Double presentasiCoverage) {
        this.presentasiCoverage = presentasiCoverage;
    }
    public Double getPresentaseCollectingFee() {
        return presentaseCollectingFee;
    }
    public void setPresentaseCollectingFee(Double presentaseCollectingFee) {
        this.presentaseCollectingFee = presentaseCollectingFee;
    }
    public Double getPresentasePajak() {
        return presentasePajak;
    }
    public void setPresentasePajak(Double presentasePajak) {
        this.presentasePajak = presentasePajak;
    }
    public String getIdKlaim() {
        return idKlaim;
    }
    public void setIdKlaim(String idKlaim) {
        this.idKlaim = idKlaim;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<T_Subrogasi_Summary> getSubrogasiSummary() {
        return subrogasiSummary;
    }
    public void setSubrogasiSummary(List<T_Subrogasi_Summary> subrogasiSummary) {
        this.subrogasiSummary = subrogasiSummary;
    }

    
    

    
}
