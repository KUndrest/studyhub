package ee.ttu.studyhub.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Score {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime created;
    @Column(length =2)
    private Integer score;

    @PrePersist
    public void PreSave() {
        created = LocalDateTime.now();
    }

    @ManyToOne
    SubjectPerson subjectPerson;

    @JsonIgnoreProperties("scores")
    @NotNull
    @ManyToOne
    Header header;
}
