package iducs.springboot.boardpsh.entity;

import iducs.springboot.boardpsh.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "member_a201912024")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(sequenceName = "member_a201912024_seq", name = "member_a201912024_seq_gen",
    initialValue = 1, allocationSize = 1)
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_a201912024_seq_gen")
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
