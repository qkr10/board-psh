package iducs.springboot.boardpsh.domain;

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
}
