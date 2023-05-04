package iducs.springboot.boardpsh.domain;

import iducs.springboot.boardpsh.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Member {
    //DTO, Domain(실제 업무 영역) object
    private Long seq;
    private String email;
    private String name;
    private String pw;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public Member(MemberEntity entity) {
        email = entity.getEmail();
        name = entity.getName();
        seq = entity.getSeq();
        pw = entity.getPw();

        regDate = entity.getRegDate();
        modDate = entity.getModDate();
    }
}
