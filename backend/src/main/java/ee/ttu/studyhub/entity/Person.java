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
    // @Pattern(regexp="^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"\n" +
     //       "\t\t+ \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$\"")
    private String email;
    @Column(length =100)
    private String password;
    @Column(length =200)
    private String name;
    @Column(length =10, unique = true)
    private String studentCode;

    @JsonIgnore
    @OneToMany(mappedBy ="person", cascade= CascadeType.ALL)
    List<SubjectPerson> subjectPersons;
}
