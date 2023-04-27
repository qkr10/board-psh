package iducs.springboot.boardpsh.domain;

import iducs.springboot.boardpsh.entity.MemberEntity;
import lombok.*;

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

    public Member(MemberEntity entity) {
        email = entity.getEmail();
        name = entity.getName();
        seq = entity.getSeq();
        pw = entity.getPw();
    }
}
