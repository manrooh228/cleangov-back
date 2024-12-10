package kz.cleangov.cleangov.domain.constructorClass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {
    private Long id;
    private String textRu;
    private boolean correct;
}
