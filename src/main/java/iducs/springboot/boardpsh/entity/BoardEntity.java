package iducs.springboot.boardpsh.entity;

import iducs.springboot.boardpsh.domain.Board;
import jakarta.persistence.*;
import lombok.*;

//JPA Auditing을 활용하여서 생성한사람, 생성일자, 수정한사람, 수정일자 등을 선택하여서 감시
@Entity
@Table(name = "board_a201912024")

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_a201912024_seq_gen")
    @SequenceGenerator(sequenceName = "board_a201912024_seq", name = "board_a201912024_seq_gen", initialValue = 1, allocationSize = 1)
    private long bno;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @ManyToOne
    //@JoinColumn(name = "seq")
    private MemberEntity writer; // BoardEntity : MemberEntity = N : 1

    public BoardEntity(Board dto) {
        MemberEntity member = MemberEntity.builder()
                .seq(dto.getWriterSeq())
                .build();

        bno = dto.getBno();
        title = dto.getTitle();
        content = dto.getContent();
        writer = member;
    }
}
