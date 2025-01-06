package dto;

import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.By;

@Data
@Builder
public class Project {

    private final String name;
    private final String projectType;
    private final String testCaseApprovals;
    private final String defaultAccess;
    private final String defectPlugin;
}
