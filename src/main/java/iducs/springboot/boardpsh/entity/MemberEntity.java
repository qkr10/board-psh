package iducs.springboot.boardpsh.entity;

import iducs.springboot.boardpsh.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "b_member")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long seq;

    @Column(length = 30, nullable = false)
    private String email;;

    @Column(length = 20, nullable = false)
    private String name;;

    @Column(length = 20, nullable = false)
    private String pw;

    public MemberEntity(Member member) {
        seq = member.getSeq();
        email = member.getEmail();
        name = member.getName();
        pw = member.getPw();
    }
}
