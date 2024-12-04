package kz.cleangov.cleangov.resource;

import kz.cleangov.cleangov.domain.Investigations;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvestigationWithProgress {
    private Investigations investigation;
    private int progress;
}
