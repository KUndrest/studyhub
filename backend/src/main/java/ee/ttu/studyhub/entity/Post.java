package ee.ttu.studyhub.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String date;
    private LocalDateTime created;

    @PrePersist
    public void PreSave(){
        created = LocalDateTime.now();
    }

    @ManyToOne
    Subject subject;
}
