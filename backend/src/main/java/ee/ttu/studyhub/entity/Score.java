package ee.ttu.studyhub.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Score {
    @Id
    @GeneratedValue
    private Long id;
    private String score;
    private String content;
    private Date date;

    @ManyToOne
    Subject subject;

    @ManyToOne
    Person person;
}
