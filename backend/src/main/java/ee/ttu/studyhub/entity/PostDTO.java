package ee.ttu.studyhub.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String date;
    private LocalDateTime created;
    private Subject subject;
}
