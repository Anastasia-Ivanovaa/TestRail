package dto.ui;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditTestCase {

    private final String title;
    private final String section;
    private final String template;
    private final String type;
    private final String priority;
    private final String assignedTo;
    private final String estimate;
    private final String references;
    private final String automationType;
    private final String preconditions;
    private final String steps;
    private final String expectedResult;
}
