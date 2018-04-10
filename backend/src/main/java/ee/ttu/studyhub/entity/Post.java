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
    @Column(length =200)
    private String title;
    @Column(length =500)
    private String content;
    @Column(length =10)
    private String date;
    private LocalDateTime created;

    @PrePersist
    public void PreSave(){
        created = LocalDateTime.now();
    }

    @ManyToOne
    Subject subject;
}
