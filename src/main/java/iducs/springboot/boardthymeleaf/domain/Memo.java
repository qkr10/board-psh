package iducs.springboot.boardthymeleaf.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    private Long mno;
    private String memoText;
}
