package ee.ttu.studyhub.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Score {
    @Id
    @GeneratedValue
    private Long id;
    private String score;
    private String header;
    private LocalDateTime created;
    private Boolean isMark;

    @PrePersist
    public void PreSave(){
        created = LocalDateTime.now();
    }
    @ManyToOne
    Subject subject;

    @ManyToOne
    Person person;
}
