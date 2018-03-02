package ee.ttu.studyhub.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;
    private String studentCode;

    @OneToMany(mappedBy ="user", cascade= CascadeType.ALL)
    List<Subject> subjects;
}
