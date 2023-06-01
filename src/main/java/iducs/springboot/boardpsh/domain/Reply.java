package iducs.springboot.boardpsh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Reply {
    private Long rno;
    private String text;
    private String replier;
    private Long bno;
}
