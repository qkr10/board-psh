package iducs.springboot.boardpsh.domain;

import iducs.springboot.boardpsh.entity.BoardEntity;
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

public class Board {
    // board
    private Long bno;
    private String title;
    private String content;

    // join
    private Long writerSeq;
    private String writerEmail;
    private String writerName;

    private Long replyCount;

    // auditing
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public Board(BoardEntity board, MemberEntity member) {
        bno = board.getBno();
        title = board.getTitle();
        content = board.getContent();

        writerSeq = member.getSeq();
        writerEmail = member.getEmail();
        writerName = member.getName();

        regDate = board.getRegDate();
        modDate = board.getModDate();
    }

    public Board(BoardEntity board) {
        this(board, board.getWriter());
    }

    public Board(BoardEntity board, MemberEntity member, Long replyCount) {
        this(board, member);
        this.replyCount = replyCount;
    }
}
