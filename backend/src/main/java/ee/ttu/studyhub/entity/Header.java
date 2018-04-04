package ee.ttu.studyhub.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Header {
    @Id
    @GeneratedValue
    private Long id;
    private String header;
    private LocalDateTime created;
    private Boolean isMark;

    @PrePersist
    public void PreSave() {
        created = LocalDateTime.now();
    }

    @NotNull
    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Person person;

    @OneToMany(mappedBy="header")
    private List<Score> scores;
}