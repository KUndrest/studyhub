package ee.ttu.studyhub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;
    private String studentCode;

    @JsonIgnore
    @OneToMany(mappedBy ="person", cascade= CascadeType.ALL)
    List<SubjectPerson> subjectPersons;
}
