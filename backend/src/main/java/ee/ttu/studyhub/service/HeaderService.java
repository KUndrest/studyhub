package ee.ttu.studyhub.service;

import ee.ttu.studyhub.entity.Header;
import ee.ttu.studyhub.entity.Subject;
import ee.ttu.studyhub.repository.HeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeaderService {

    @Autowired
    private HeaderRepository headerRepository;

    public HeaderService(HeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    public Header addHeader(Header header) {
        return headerRepository.save(header);
    }

    public List<Header> getAllHeaders() {
        return headerRepository.findAll();
    }

    public Header getHeaderById(long headerId) {
        return headerRepository.findOne(headerId);
    }

    public List<Header> findBySubject(Subject subject) {
        return headerRepository.findAllBySubject(subject);
    }
}
