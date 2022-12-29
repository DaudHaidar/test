package com.askrindo.aossubrogasi.service.aos;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.askrindo.aossubrogasi.dto.RequestSubrogasi;
import com.askrindo.aossubrogasi.dto.ResponseData;
import com.askrindo.aossubrogasi.entity.aos.Logs;
import com.askrindo.aossubrogasi.repository.aos.LogsRepository;

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
