package ee.ttu.idk0071.studyhub.subject;

@Entity
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue
    long id;
    String subject;
    String code;

    @OneToOne(mappedBy="subject",
            cascade=CascadeType.ALL)
    Lector lector;

    @OneToMany(mappedBy="subject", cascade=CascadeType.ALL)
    List<Student> students;
}