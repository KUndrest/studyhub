package ee.ttu.studyhub.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Date date;

    @ManyToOne
    Subject subject;

    @ManyToOne
    Person person;
}
