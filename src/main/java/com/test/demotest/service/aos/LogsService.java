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

    public Logs create(String noRekening, String process, String response, String responseCode, String request,String responseMessage) {

        Logs createLogs = new Logs(noRekening,process,request,response,responseCode,responseMessage);
        return logsRepository.save(createLogs);
    }

    public Iterable<Logs> getAll() {
        return logsRepository.findAll();
    }

    public void delete(Long id) {
        logsRepository.deleteById(id);
    }

}
