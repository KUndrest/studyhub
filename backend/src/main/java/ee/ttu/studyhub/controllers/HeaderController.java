package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Header;
import ee.ttu.studyhub.entity.HeaderDTO;
import ee.ttu.studyhub.entity.Subject;
import ee.ttu.studyhub.service.HeaderService;
import ee.ttu.studyhub.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class HeaderController {

    @PersistenceContext
    private EntityManager em;
    private HeaderService headerService;

    @Autowired
    private SubjectService subjectService;

    public HeaderController(HeaderService headerService) {
        this.headerService = headerService;
    }

    @RequestMapping(value="/headers/add", method= RequestMethod.POST,
            consumes = "application/json")
    public Header addHeader(@RequestBody HeaderDTO headerDTO) {
        Header header = new Header();
        header.setHeader(headerDTO.getHeader());
        header.setPerson(headerDTO.getPerson());
        header.setIsMark(headerDTO.getIsMark());
        header.setSubject(headerDTO.getSubject());
        return headerService.addHeader(header);
    }

    @RequestMapping(value="/headers", method=RequestMethod.GET)
    public List<Header> getAllHeaders() {
        return headerService.getAllHeaders();
    }

    @RequestMapping(value = "/headers/{id}", method=RequestMethod.GET)
    public List<Header> getHeaders(@PathVariable("id") long subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        return headerService.findBySubject(subject);
    }

    @DeleteMapping(value = "/headers/{id}")
    public List<Header> removeHeader(@PathVariable("id") long headerId) {
        headerService.removeHeader(headerId);
        return getAllHeaders();
    }

    @Transactional
    @PostMapping(value = "/header")
    public Header updateHeader(@RequestBody HeaderDTO headerDTO) {
        Header header = headerService.getHeaderById(headerDTO.getId());

        header.setHeader(headerDTO.getHeader());
        header.setCreated(headerDTO.getCreated());
        header.setIsMark(headerDTO.getIsMark());
        header.setSubject(headerDTO.getSubject());
        header.setPerson(headerDTO.getPerson());
        header.setId(headerDTO.getId());
        return em.merge(header);
    }
}

