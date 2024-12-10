package kz.cleangov.cleangov.domain.constructorClass;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
    private Long id;
    private String textRu;
    private List<AnswerDTO> answers;
}
