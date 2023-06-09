package iducs.springboot.boardpsh.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "a_memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
