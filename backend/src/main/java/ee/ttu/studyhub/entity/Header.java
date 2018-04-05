package ee.ttu.studyhub.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

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

    @JsonIgnore
    @NotNull
    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Person person;

    @OneToMany(mappedBy="header", fetch = EAGER)
    private List<Score> scores;
}