package ee.ttu.studyhub.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScoreDTO {
    private Long id;
    private LocalDateTime created;
    private Integer score;
    private SubjectPerson subjectPerson;
    private Header header;

}
