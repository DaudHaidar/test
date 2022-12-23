package com.test.demotest.service.aos;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.dto.RequestSubrogasi;
import com.test.demotest.dto.ResponseData;
import com.test.demotest.entity.aos.Logs;
import com.test.demotest.repository.aos.LogsRepository;

@Service
@Transactional
public class LogsService {

    @Autowired
    private LogsRepository logsRepository;

    public Logs create(RequestSubrogasi request, ResponseData<Object> response ) {

        Logs createLogs = new Logs(request.getNoRekening(),"h2h-subro",request.toString(),response.toString(),response.getStatus(),response.getMessage());
        return logsRepository.save(createLogs);
    }

    public Iterable<Logs> getAll() {
        return logsRepository.findAll();
    }

    public void delete(Long id) {
        logsRepository.deleteById(id);
    }

}
