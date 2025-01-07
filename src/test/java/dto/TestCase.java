package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCase {

    private final String sectionOption;
    private final String templateOption;
    private final String typeOption;
    private final String priorityOption;
    private final String automationTypeOption;
    private final String preconditions;
    private final String steps;
    private final String expectedResult;


}
