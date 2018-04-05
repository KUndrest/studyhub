package ee.ttu.studyhub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Integer score;

    @PrePersist
    public void PreSave() {
        created = LocalDateTime.now();
    }

    @ManyToOne
    SubjectPerson subjectPerson;

    @JsonIgnore
    @NotNull
    @ManyToOne
    Header header;
}
