package ee.ttu.studyhub.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class SubjectPerson {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    Person person;

    @ManyToOne
    Subject subject;

    @OneToMany(mappedBy ="subjectPerson", cascade= CascadeType.ALL)
    List<Score> scores;

}
