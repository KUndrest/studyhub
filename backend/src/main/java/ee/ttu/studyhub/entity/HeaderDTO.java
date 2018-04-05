package ee.ttu.studyhub.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HeaderDTO {
    private Long id;
    private String header;
    private LocalDateTime created;
    private Boolean isMark;
    private Subject subject;
    private Person person;
}