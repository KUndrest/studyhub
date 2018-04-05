package ee.ttu.studyhub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class SubjectPerson {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    Person person;

    @JsonIgnore
    @ManyToOne
    Subject subject;

    @JsonIgnore
    @OneToMany(mappedBy ="subjectPerson", cascade= CascadeType.ALL)
    List<Score> scores;
}
