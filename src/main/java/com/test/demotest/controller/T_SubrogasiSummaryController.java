package com.test.demotest.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.demotest.dto.RequestSubrogasi;
import com.test.demotest.dto.ResponseData;
import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.entity.acs.CLM_PRELIMINARY;
import com.test.demotest.entity.acs.CLM_RECOV_PAYMENT;
import com.test.demotest.entity.acs.CLM_REGISTRATION_OS;
import com.test.demotest.entity.acs.CLM_SETTLEMENT;
import com.test.demotest.entity.acs.CLM_SETTLEMENT_SUMMARY;
import com.test.demotest.entity.aos.T_Subrogasi;
import com.test.demotest.entity.aos.T_Subrogasi_Summary;
import com.test.demotest.service.acs.CLM_INQUIRY_SUBROGRATIONService;
import com.test.demotest.service.acs.CLM_PRELIMINARYService;
import com.test.demotest.service.acs.CLM_RECOV_PAYMENTService;
import com.test.demotest.service.acs.CLM_REGISTRATION_OSService;
import com.test.demotest.service.acs.CLM_SETTLEMENTService;
import com.test.demotest.service.acs.CLM_SETTLEMENT_SUMMARYService;
import com.test.demotest.service.aos.LogsService;
import com.test.demotest.service.aos.T_SubrogasiService;
import com.test.demotest.service.aos.T_SubrogasiSummaryService;

@RestController
public class T_SubrogasiSummaryController {
    
    @Autowired
    private T_SubrogasiSummaryService subrogasiSummaryService;
    @Autowired
    private T_SubrogasiService subrogasiService;
    @Autowired
    private CLM_INQUIRY_SUBROGRATIONService cInquiryService;
    @Autowired
    private CLM_PRELIMINARYService cPreliminaryService;
    @Autowired
    private CLM_SETTLEMENTService cSettlementService;
    @Autowired
    private CLM_SETTLEMENT_SUMMARYService cSettlementSummaryService;
    @Autowired
    private CLM_RECOV_PAYMENTService cRecovePaymentService;
    @Autowired
    private CLM_REGISTRATION_OSService cRegistrationOsService;
    @Autowired
    private LogsService logsService;

    @PostMapping("/api/v1/shs/subro")
    public ResponseEntity<ResponseData<Object>> create(@RequestBody RequestSubrogasi request){

    try {
        CLM_INQUIRY_SUBROGATION_CREDIT cInquiry = cInquiryService.getByNoRekening(request.getNoRekening());
        CLM_PRELIMINARY cPreliminary = cPreliminaryService.findRegistrationId(cInquiry.getRegistrationId());

        System.out.println("CEK SISA SUBROGARATION "+cInquiry.getAmtSubrogation());
        if(cInquiry != null){

            // T_Subrogasi_Summary subrogasiSummary = new T_Subrogasi_Summary();
            // T_Subrogasi subrogasi= new T_Subrogasi();
            T_Subrogasi_Summary lineNo = subrogasiSummaryService.findByLineNo(request.getCounterAngsuran());

            if(lineNo != null){
                System.out.println("Subrogation2 line no :"+lineNo.getLineNo());
                throw new Exception("Counter angsuran sudah terdaftar");
            } 
    
            if(lineNo == null&& request.getCounterAngsuran()==1){
                if(cInquiry.getAmtSubrogation() <= 0){     

                    T_Subrogasi subrogasi = subrogasiService.save(request,cInquiry);

                    T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi);

                    ResponseData<Object> response = new ResponseData<Object>();
                    response.setStatus("00");
                    response.setMessage("00");
                    response.getData().add(subrogasiSummary);

                    logsService.create(request.getNoRekening(), "h2h-subro", response.toString(), response.getStatus(), request.toString(), response.getMessage());

                    return ResponseEntity.status(HttpStatus.CREATED).body(response);

                    // subrogasi.setNoRekening(request.getNoRekening());
                    // subrogasi.setNomorPeserta(request.getNoRekening());
                    // subrogasi.setNominalClaim(request.getNilaiRecoveries());
                    // subrogasi.setAkumulasiSubrogasi(cInquiry.getAmtRecovery()+request.getNilaiRecoveries());
                    // subrogasi.setSisaKewajibanSubrogasi(cInquiry.getAmtSubrogation());
                    // subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
                    // subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
                    // subrogasi.setPresentasePajak(Double.valueOf(0));
                    // subrogasi.setIdKlaim(UUID.randomUUID().toString());
                    // subrogasi.setStatus("0");
   
                    // subrogasiSummary.setLineNo(request.getCounterAngsuran());
                    // subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                    // subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-cInquiry.getAmtSubrogation());
                    // subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                    // subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
                    // subrogasiSummary.setKodeBank(request.getKodeBank());
                    // subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
                    // subrogasiSummary.setTglNotaKredit(new Date());
                    // subrogasiSummary.setSubrogasiId(subrogasiService.save(subrogasi));
                    // subrogasiSummary.setNominalSubrogasiBunga(Double.valueOf(0));
                    // subrogasiSummary.setNominalSubrogasiDenda(Double.valueOf(0));
                    // subrogasiSummary.setNominalPajak(Double.valueOf(0));
                    // subrogasiSummary.setNominalFeeGross(Double.valueOf(0));
                    // subrogasiSummary.setNominalFeeNet(Double.valueOf(0));
                    // subrogasiSummary.setNominalSubrogasiTotal(request.getNilaiRecoveries());
                    // subrogasiSummary.setTanggalJurnal(null);
                    // subrogasiSummary.setNoJurnal(null);
                    // subrogasiSummary.setBiayaRekonsiliasi(Double.valueOf(0));
                    // subrogasiSummary.setCollectingAgent(null);
                    // subrogasiSummary.setNoNotaKredit(null);
                    // subrogasiSummary.setmRekeningGiro(null);
                    // subrogasiSummary.setIsNetting((byte) 0);
                    // subrogasiSummary.setStatus("0");
                    // subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+cInquiry.getBpUnitCode()+"_"+request.getNoRekening()+"_"+request.getCounterAngsuran());

                }
                else if(cInquiry.getAmtSubrogation() >0){
                    
                    System.out.println("CEK REGISTRATION ID CLM_PRELIMINARY (cPreliminary) :"+cPreliminary);
                    System.out.println("cInquiry REGISTRATION ID (cInquiry.getId()): "+ cInquiry.getRegistrationId());
                    System.out.println("PRELIMINARY_ID (cPreliminary.getPreliminaryId()) : "+cPreliminary.getPreliminaryId());

                    String noRekening = request.getNoRekening();
                    String nomorPeserta = request.getNoRekening();
                    Double nominalClaim = request.getNilaiRecoveries();
                    Double akumulasiSubrogasi = cInquiry.getAmtRecovery()+request.getNilaiRecoveries();
                    Double sisaKewajibanSubrogasi = cInquiry.getAmtSubrogation();
                    Double presentasiCoverage = Double.valueOf(request.getCovRatio());
                    Double presentaseCollectingFee = Double.valueOf(0);
                    Double presentasePajak = Double.valueOf(0);
                    String idKlaim = UUID.randomUUID().toString();

                    T_Subrogasi subrogasi = subrogasiService.save(noRekening, nomorPeserta, nominalClaim, akumulasiSubrogasi, sisaKewajibanSubrogasi, presentasiCoverage, presentaseCollectingFee, presentasePajak, idKlaim, idKlaim);

                    Integer lineNomor = request.getCounterAngsuran();
                    Double nominalSubrogasiLebih = request.getNilaiRecoveries()-cInquiry.getAmtSubrogation();
                    Double nominalSUbrogasiPokok = request.getNilaiRecoveries();
                    String jenisTransaksi = request.getJenisTransaksi();
                    String kodeBank = request.getKodeBank();
                    String kodeCabangAskrindo = request.getKodeCabangAskrindo();
                    Date tglNotaKredit = new Date();
                    T_Subrogasi subrogasiId =  subrogasi;
                    Double nominalSubrogasiBunga = Double.valueOf(0);
                    Double nominalSubrogasiDenda = Double.valueOf(0);
                    Double nominalPajak = Double.valueOf(0);
                    Double nominalFeeGross = Double.valueOf(0);
                    Double nominalFeeNet = Double.valueOf(0);
                    Double nominalSubrogasiTotal = request.getNilaiRecoveries();
                    Date tangalJurnal = null;
                    String noJurnal = null;
                    Double biayaRekonsiliasi = Double.valueOf(0);
                    String collectingAgent = null;
                    String noNotaKredit = null;
                    String mRekeningGiro = null;
                    Byte isNetting = 0;
                    String status = "0";
                    String bpUnitCode = cInquiry.getBpUnitCode();

                    T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(lineNomor, nominalSUbrogasiPokok, nominalSubrogasiLebih<0.0? 0.0 :nominalSubrogasiLebih, jenisTransaksi, kodeBank, kodeCabangAskrindo, tglNotaKredit, subrogasiId, nominalSubrogasiBunga, nominalSubrogasiDenda, nominalPajak, nominalFeeGross, nominalFeeNet, nominalSubrogasiTotal, tangalJurnal, noJurnal, biayaRekonsiliasi, collectingAgent, noNotaKredit, mRekeningGiro, isNetting, status, bpUnitCode, noRekening);

                    // subrogasi.setNoRekening(request.getNoRekening());
                    // subrogasi.setNomorPeserta(request.getNoRekening());
                    // subrogasi.setNominalClaim(request.getNilaiRecoveries());
                    // subrogasi.setAkumulasiSubrogasi(cInquiry.getAmtRecovery()+request.getNilaiRecoveries());
                    // subrogasi.setSisaKewajibanSubrogasi(cInquiry.getAmtSubrogation());
                    // subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
                    // subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
                    // subrogasi.setPresentasePajak(Double.valueOf(0));
                    // subrogasi.setIdKlaim(UUID.randomUUID().toString());
                    // subrogasi.setStatus("0");

                    // subrogasiSummary.setLineNo(request.getCounterAngsuran());
                    // subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                    // subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-cInquiry.getAmtSubrogation()<0.0? 0.0 : request.getNilaiRecoveries()-cInquiry.getAmtSubrogation() );
                    // subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                    // subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
                    // subrogasiSummary.setKodeBank(request.getKodeBank());
                    // subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
                    // subrogasiSummary.setTglNotaKredit(new Date());
                    // subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+cInquiry.getBpUnitCode()+"_"+request.getNoRekening()+"_"+request.getCounterAngsuran());
                    // subrogasiSummary.setSubrogasiId(subrogasiService.save(subrogasi));
                    // subrogasiSummary.setNominalSubrogasiBunga(Double.valueOf(0));
                    // subrogasiSummary.setNominalSubrogasiDenda(Double.valueOf(0));
                    // subrogasiSummary.setNominalPajak(Double.valueOf(0));
                    // subrogasiSummary.setNominalFeeGross(Double.valueOf(0));
                    // subrogasiSummary.setNominalFeeNet(Double.valueOf(0));
                    // subrogasiSummary.setNominalSubrogasiTotal(request.getNilaiRecoveries());
                    // subrogasiSummary.setTanggalJurnal(null);
                    // subrogasiSummary.setNoJurnal(null);
                    // subrogasiSummary.setBiayaRekonsiliasi(Double.valueOf(0));
                    // subrogasiSummary.setCollectingAgent(null);
                    // subrogasiSummary.setNoNotaKredit(null);
                    // subrogasiSummary.setmRekeningGiro(null);
                    // subrogasiSummary.setIsNetting((byte) 0);
                    // subrogasiSummary.setStatus("0");
                    

                    // T_Subrogasi_Summary cSubrogasiSummary = subrogasiSummaryService.create(subrogasiSummary);

                    CLM_SETTLEMENT cSettlement = cSettlementService.create(cInquiry.getRegistrationId(), "CLM_SETTLE_TYPE.SUBROGATION_13", cPreliminary.getPreliminaryId(), "CLM_COMPLETION_TYPE.TEKNIS", "CLM_OPSI_PEMBAYARAN.PENUH", null, null, 5, 12, 1, '0', 0);

                    CLM_SETTLEMENT_SUMMARY cSettlementSummary = cSettlementSummaryService.create( cSettlement,"IDR", Double.valueOf(1000000), Double.valueOf(1));

                    CLM_RECOV_PAYMENT cRecovPayment = cRecovePaymentService.create(cInquiry.getBranchId(), '0', cInquiry.getRegistrationId(), cInquiry.getAnalysisId(), cSettlement.getSettlementId(), 1, '0', cInquiry.getAmtSubrogation(), cInquiry.getAmtSubrogation()-request.getNilaiRecoveries(), '0', "RECOVERY_TYPE_KUR.REC");

                    
                    ResponseData<Object> response = new ResponseData<Object>();
                    response.setStatus("00");
                    response.setMessage("00");
                    response.getData().add(cSettlementSummary);
                    response.getData().add(subrogasiSummary);
                    response.getData().add(cRecovPayment);

                    CLM_REGISTRATION_OS cRegistrationOs =cRegistrationOsService.update(cInquiry.getRegistrationId(), cRecovPayment.getAmtShsAfter(), cInquiry.getAmtClaimPayment()-cRecovPayment.getAmtShsAfter());
                    response.getData().add(cRegistrationOs);

                    logsService.create(request.getNoRekening(), "h2h-subro", response.toString(), response.getStatus(), request.toString(), response.getMessage());

                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                }            
            }  
            
                if(request.getCounterAngsuran()!=1 && subrogasiSummaryService.findByLineNo(request.getCounterAngsuran()-1) != null){

                    if(cInquiry.getAmtSubrogation() <= 0){

                        String noRekening = request.getNoRekening();
                        String nomorPeserta = request.getNoRekening();
                        Double nominalClaim = request.getNilaiRecoveries();
                        Double akumulasiSubrogasi = cInquiry.getAmtRecovery()+request.getNilaiRecoveries();
                        Double sisaKewajibanSubrogasi = cInquiry.getAmtSubrogation();
                        Double presentasiCoverage = Double.valueOf(request.getCovRatio());
                        Double presentaseCollectingFee = Double.valueOf(0);
                        Double presentasePajak = Double.valueOf(0);
                        String idKlaim = UUID.randomUUID().toString();

                        T_Subrogasi subrogasi = subrogasiService.save(noRekening, nomorPeserta, nominalClaim, akumulasiSubrogasi, sisaKewajibanSubrogasi, presentasiCoverage, presentaseCollectingFee, presentasePajak, idKlaim, idKlaim);

                        Integer lineNomor = request.getCounterAngsuran();
                        Double nominalSubrogasiLebih = request.getNilaiRecoveries()-cInquiry.getAmtSubrogation();
                        Double nominalSUbrogasiPokok = request.getNilaiRecoveries();
                        String jenisTransaksi = request.getJenisTransaksi();
                        String kodeBank = request.getKodeBank();
                        String kodeCabangAskrindo = request.getKodeCabangAskrindo();
                        Date tglNotaKredit = new Date();
                        T_Subrogasi subrogasiId =  subrogasi;
                        Double nominalSubrogasiBunga = Double.valueOf(0);
                        Double nominalSubrogasiDenda = Double.valueOf(0);
                        Double nominalPajak = Double.valueOf(0);
                        Double nominalFeeGross = Double.valueOf(0);
                        Double nominalFeeNet = Double.valueOf(0);
                        Double nominalSubrogasiTotal = request.getNilaiRecoveries();
                        Date tangalJurnal = null;
                        String noJurnal = null;
                        Double biayaRekonsiliasi = Double.valueOf(0);
                        String collectingAgent = null;
                        String noNotaKredit = null;
                        String mRekeningGiro = null;
                        Byte isNetting = 0;
                        String status = "0";
                        String bpUnitCode = cInquiry.getBpUnitCode();

                        T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(lineNomor, nominalSUbrogasiPokok, nominalSubrogasiLebih, jenisTransaksi, kodeBank, kodeCabangAskrindo, tglNotaKredit, subrogasiId, nominalSubrogasiBunga, nominalSubrogasiDenda, nominalPajak, nominalFeeGross, nominalFeeNet, nominalSubrogasiTotal, tangalJurnal, noJurnal, biayaRekonsiliasi, collectingAgent, noNotaKredit, mRekeningGiro, isNetting, status, bpUnitCode, noRekening);

                        ResponseData<Object> response = new ResponseData<Object>();
                        response.setStatus("00");
                        response.setMessage("00");
                        response.getData().add(subrogasiSummary);

                        logsService.create(request.getNoRekening(), "h2h-subro", response.toString(), response.getStatus(), request.toString(), response.getMessage());

                        return ResponseEntity.status(HttpStatus.CREATED).body(response);

                        // subrogasi.setNoRekening(request.getNoRekening());
                        // subrogasi.setNomorPeserta(request.getNoRekening());
                        // subrogasi.setNominalClaim(request.getNilaiRecoveries());
                        // subrogasi.setAkumulasiSubrogasi(cInquiry.getAmtRecovery()+request.getNilaiRecoveries());
                        // subrogasi.setSisaKewajibanSubrogasi(cInquiry.getAmtSubrogation());
                        // subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
                        // subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
                        // subrogasi.setPresentasePajak(Double.valueOf(0));
                        // subrogasi.setIdKlaim(UUID.randomUUID().toString());
                        // subrogasi.setStatus("0");

                        // subrogasiSummary.setLineNo(request.getCounterAngsuran());
                        // subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                        // subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-cInquiry.getAmtSubrogation());
                        // subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                        // subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
                        // subrogasiSummary.setKodeBank(request.getKodeBank());
                        // subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
                        // subrogasiSummary.setTglNotaKredit(new Date());
                        // subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+cInquiry.getBpUnitCode()+"_"+request.getNoRekening()+"_"+request.getCounterAngsuran());
                        // subrogasiSummary.setSubrogasiId(subrogasiService.save(subrogasi));
                        // subrogasiSummary.setNominalSubrogasiBunga(Double.valueOf(0));
                        // subrogasiSummary.setNominalSubrogasiDenda(Double.valueOf(0));
                        // subrogasiSummary.setNominalPajak(Double.valueOf(0));
                        // subrogasiSummary.setNominalFeeGross(Double.valueOf(0));
                        // subrogasiSummary.setNominalFeeNet(Double.valueOf(0));
                        // subrogasiSummary.setNominalSubrogasiTotal(request.getNilaiRecoveries());
                        // subrogasiSummary.setTanggalJurnal(null);
                        // subrogasiSummary.setNoJurnal(null);
                        // subrogasiSummary.setBiayaRekonsiliasi(Double.valueOf(0));
                        // subrogasiSummary.setCollectingAgent(null);
                        // subrogasiSummary.setNoNotaKredit(null);
                        // subrogasiSummary.setmRekeningGiro(null);
                        // subrogasiSummary.setIsNetting((byte) 0);
                        // subrogasiSummary.setStatus("0");

                    } 
                    
                    else if(cInquiry.getAmtSubrogation() > 0){

                        System.out.println("CEK REGISTRATION ID CLM_PRELIMINARY (cPreliminary) :"+cPreliminary);
                        System.out.println("cInquiry REGISTRATION ID (cInquiry.getId()): "+ cInquiry.getRegistrationId());
                        System.out.println("PRELIMINARY_ID (cPreliminary.getPreliminaryId()) : "+cPreliminary.getPreliminaryId());

                        // subrogasi.setNoRekening(request.getNoRekening());
                        // subrogasi.setNomorPeserta(request.getNoRekening());
                        // subrogasi.setNominalClaim(request.getNilaiRecoveries());
                        // subrogasi.setAkumulasiSubrogasi(cInquiry.getAmtRecovery()+request.getNilaiRecoveries());
                        // subrogasi.setSisaKewajibanSubrogasi(cInquiry.getAmtSubrogation());
                        // subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
                        // subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
                        // subrogasi.setPresentasePajak(Double.valueOf(0));
                        // subrogasi.setIdKlaim(UUID.randomUUID().toString());
                        // subrogasi.setStatus("0");

                        // subrogasiSummary.setLineNo(request.getCounterAngsuran());
                        // subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                        // subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-cInquiry.getAmtSubrogation());
                        // subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                        // subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
                        // subrogasiSummary.setKodeBank(request.getKodeBank());
                        // subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
                        // subrogasiSummary.setTglNotaKredit(new Date());
                        // subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+cInquiry.getBpUnitCode()+"_"+request.getNoRekening()+"_"+request.getCounterAngsuran());
                        // subrogasiSummary.setSubrogasiId(subrogasiService.save(subrogasi));
                        // subrogasiSummary.setNominalSubrogasiBunga(Double.valueOf(0));
                        // subrogasiSummary.setNominalSubrogasiDenda(Double.valueOf(0));
                        // subrogasiSummary.setNominalPajak(Double.valueOf(0));
                        // subrogasiSummary.setNominalFeeGross(Double.valueOf(0));
                        // subrogasiSummary.setNominalFeeNet(Double.valueOf(0));
                        // subrogasiSummary.setNominalSubrogasiTotal(request.getNilaiRecoveries());
                        // subrogasiSummary.setTanggalJurnal(null);
                        // subrogasiSummary.setNoJurnal(null);
                        // subrogasiSummary.setBiayaRekonsiliasi(Double.valueOf(0));
                        // subrogasiSummary.setCollectingAgent(null);
                        // subrogasiSummary.setNoNotaKredit(null);
                        // subrogasiSummary.setmRekeningGiro(null);
                        // subrogasiSummary.setIsNetting((byte) 0);
                        // subrogasiSummary.setStatus("0");
                        

                        // T_Subrogasi_Summary cSubrogasiSummary = subrogasiSummaryService.create(subrogasiSummary);

                        String noRekening = request.getNoRekening();
                        String nomorPeserta = request.getNoRekening();
                        Double nominalClaim = request.getNilaiRecoveries();
                        Double akumulasiSubrogasi = cInquiry.getAmtRecovery()+request.getNilaiRecoveries();
                        Double sisaKewajibanSubrogasi = cInquiry.getAmtSubrogation();
                        Double presentasiCoverage = Double.valueOf(request.getCovRatio());
                        Double presentaseCollectingFee = Double.valueOf(0);
                        Double presentasePajak = Double.valueOf(0);
                        String idKlaim = UUID.randomUUID().toString();

                        T_Subrogasi subrogasi = subrogasiService.save(noRekening, nomorPeserta, nominalClaim, akumulasiSubrogasi, sisaKewajibanSubrogasi, presentasiCoverage, presentaseCollectingFee, presentasePajak, idKlaim, idKlaim);

                        Integer lineNomor = request.getCounterAngsuran();
                        Double nominalSubrogasiLebih = request.getNilaiRecoveries()-cInquiry.getAmtSubrogation();
                        Double nominalSUbrogasiPokok = request.getNilaiRecoveries();
                        String jenisTransaksi = request.getJenisTransaksi();
                        String kodeBank = request.getKodeBank();
                        String kodeCabangAskrindo = request.getKodeCabangAskrindo();
                        Date tglNotaKredit = new Date();
                        T_Subrogasi subrogasiId =  subrogasi;
                        Double nominalSubrogasiBunga = Double.valueOf(0);
                        Double nominalSubrogasiDenda = Double.valueOf(0);
                        Double nominalPajak = Double.valueOf(0);
                        Double nominalFeeGross = Double.valueOf(0);
                        Double nominalFeeNet = Double.valueOf(0);
                        Double nominalSubrogasiTotal = request.getNilaiRecoveries();
                        Date tangalJurnal = null;
                        String noJurnal = null;
                        Double biayaRekonsiliasi = Double.valueOf(0);
                        String collectingAgent = null;
                        String noNotaKredit = null;
                        String mRekeningGiro = null;
                        Byte isNetting = 0;
                        String status = "0";
                        String bpUnitCode = cInquiry.getBpUnitCode();

                        T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(lineNomor, nominalSUbrogasiPokok, nominalSubrogasiLebih<0.0? 0.0 :nominalSubrogasiLebih, jenisTransaksi, kodeBank, kodeCabangAskrindo, tglNotaKredit, subrogasiId, nominalSubrogasiBunga, nominalSubrogasiDenda, nominalPajak, nominalFeeGross, nominalFeeNet, nominalSubrogasiTotal, tangalJurnal, noJurnal, biayaRekonsiliasi, collectingAgent, noNotaKredit, mRekeningGiro, isNetting, status, bpUnitCode, noRekening);

                        CLM_SETTLEMENT cSettlement = cSettlementService.create(cInquiry.getRegistrationId(), "CLM_SETTLE_TYPE.SUBROGATION_13", cPreliminary.getPreliminaryId(), "CLM_COMPLETION_TYPE.TEKNIS", "CLM_OPSI_PEMBAYARAN.PENUH", null, null, 5, 12, 1, '0', 0);

                        CLM_SETTLEMENT_SUMMARY cSettlementSummary = cSettlementSummaryService.create( cSettlement,"IDR", Double.valueOf(1000000), Double.valueOf(1));

                        CLM_RECOV_PAYMENT cRecovPayment = cRecovePaymentService.create(cInquiry.getBranchId(), '0', cInquiry.getRegistrationId(), cInquiry.getAnalysisId(), cSettlement.getSettlementId(), 1, '0', cInquiry.getAmtSubrogation(), cInquiry.getAmtSubrogation()-request.getNilaiRecoveries(), '0', "RECOVERY_TYPE_KUR.REC");
                        

                        ResponseData<Object> response = new ResponseData<Object>();
                        response.setStatus("00");
                        response.setMessage("00");
                        response.getData().add(cSettlementSummary);
                        response.getData().add(subrogasiSummary);
                        response.getData().add(cRecovPayment);

                        System.out.println("CEK Shs After recov :"+cRecovPayment.getAmtShsAfter());
                        System.out.println("CEK getAmtClaimPayment "+(cInquiry.getAmtClaimPayment()));
                        System.out.println("CEK recovPayment" + cRecovPayment.getAmtShsAfter());

                        CLM_REGISTRATION_OS cRegistrationOs =cRegistrationOsService.update(cInquiry.getRegistrationId(), cRecovPayment.getAmtShsAfter(), cInquiry.getAmtClaimPayment()-cRecovPayment.getAmtShsAfter());

                        response.getData().add(cRegistrationOs);
                        System.out.println("cekRegistrationOs"+cRegistrationOs);
                        System.out.println("AMT_SETTLED :" +cRegistrationOs.getAmtSettled());
                        System.out.println("AMT_OS :"+cRegistrationOs.getAmtOs());

                        logsService.create(request.getNoRekening(), "h2h-subro", response.toString(), response.getStatus(), request.toString(), response.getMessage());

                        return ResponseEntity.status(HttpStatus.CREATED).body(response);
                    }
            }
        }
        
        throw new Exception("Data cInquiry tidak ditemukan atau value counter angsuran longkap");
        
    } catch (Exception e) {

            ResponseData<Object> response = new ResponseData<Object>();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            System.out.println(e);
            logsService.create(request.getNoRekening(), "h2h-subro", response.toString(), response.getStatus(), request.toString(), response.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  
        }        
    }

}

