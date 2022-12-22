package com.test.demotest.service.aos;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.aos.Logs;
import com.test.demotest.repository.aos.LogsRepository;

@Service
@Transactional
public class LogsService {

    @Autowired
    private LogsRepository logsRepository;

    public Logs create(String noRekening, String process, String response, String responseCode, String request,
            String responseMessage) {
        System.out.println("No rekening:" + noRekening.length());
        System.out.println("response:" + response.length());
        System.out.println("request:" + request.length());
        System.out.println("responseMessage:" + responseMessage.length());

        Logs createLogs = new Logs();
        createLogs.setId(1L);
        createLogs.setNoRekenig(noRekening);
        createLogs.setProcess(process);
        createLogs.setResponse(response);
        createLogs.setResponseCode(responseCode);
        createLogs.setRequest(request);
        createLogs.setResponseMessage(responseMessage);
        return logsRepository.save(createLogs);
    }

    public Logs update(String noRekening, String process, String response, String responseCode, String request,
            String responseMessage, Long id) {
        if (!(logsRepository.findById(id).isPresent())) {
            throw new RuntimeException("Id logs tidak ditemukan");
        }
        Logs updateLogs = logsRepository.findById(id).get();
        updateLogs.setId(id);
        updateLogs.setNoRekenig(noRekening);
        updateLogs.setProcess(process);
        updateLogs.setResponse(response);
        updateLogs.setResponseCode(responseCode);
        updateLogs.setRequest(request);
        updateLogs.setResponseMessage(responseMessage);
        return logsRepository.save(updateLogs);
    }

    public Iterable<Logs> getAll() {
        return logsRepository.findAll();
    }

    public void delete(Long id) {
        logsRepository.deleteById(id);
    }

}
