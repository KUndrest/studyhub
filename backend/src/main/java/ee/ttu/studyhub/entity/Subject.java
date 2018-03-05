package ee.ttu.studyhub.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue
    private Long id;
    private String subject;
    private String code;

    @ManyToOne
    Person person;

    @OneToMany(mappedBy ="subject", cascade= CascadeType.ALL)
    List<Post> posts;

    @OneToMany(mappedBy ="subject", cascade= CascadeType.ALL)
    List<Score> scores;
}