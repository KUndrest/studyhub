package ee.ttu.studyhub.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
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

    @JsonIgnoreProperties({"headers", "subjectPersons", "person", "posts"})
    @NotNull
    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Person person;

    @JsonIgnoreProperties("header")
    @OneToMany(mappedBy="header", fetch = EAGER, cascade = ALL, orphanRemoval = true)
    private List<Score> scores;
}