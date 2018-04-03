package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Header;
import ee.ttu.studyhub.service.HeaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HeaderController {
    private HeaderService headerService;

    public HeaderController(HeaderService headerService) {
        this.headerService = headerService;
    }

    @RequestMapping(value="/headers/add", method= RequestMethod.POST,
            consumes = "application/json")
    public Header addHeader(@RequestBody Header header) {
        return headerService.addHeader(header);
    }

    @RequestMapping(value="/headers", method=RequestMethod.GET)
    public List<Header> getAllHeaders() {
        return headerService.getAllHeaders();
    }

    @RequestMapping(value = "/headers/{id}", method=RequestMethod.GET)
    public Header getHeader(@PathVariable("id") long headerId) {
        return headerService.getHeaderById(headerId);
    }
}

