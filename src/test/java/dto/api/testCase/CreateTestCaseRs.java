package dto.api.testCase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTestCaseRs {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("section_id")
    @Expose
    private Integer sectionId;
    @SerializedName("template_id")
    @Expose
    private Integer templateId;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("priority_id")
    @Expose
    private Integer priorityId;
    @SerializedName("milestone_id")
    @Expose
    private Object milestoneId;
    @SerializedName("refs")
    @Expose
    private String refs;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("created_on")
    @Expose
    private Integer createdOn;
    @SerializedName("updated_by")
    @Expose
    private Integer updatedBy;
    @SerializedName("updated_on")
    @Expose
    private Integer updatedOn;
    @SerializedName("estimate")
    @Expose
    private String estimate;
    @SerializedName("estimate_forecast")
    @Expose
    private Object estimateForecast;
    @SerializedName("suite_id")
    @Expose
    private Integer suiteId;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("is_deleted")
    @Expose
    private Integer isDeleted;
    @SerializedName("case_assignedto_id")
    @Expose
    private Object caseAssignedtoId;
    @SerializedName("custom_automation_type")
    @Expose
    private Object customAutomationType;
    @SerializedName("custom_preconds")
    @Expose
    private String customPreconds;
    @SerializedName("custom_steps")
    @Expose
    private String customSteps;
    @SerializedName("custom_testrail_bdd_scenario")
    @Expose
    private Object customTestrailBddScenario;
    @SerializedName("custom_expected")
    @Expose
    private String customExpected;
    @SerializedName("custom_steps_separated")
    @Expose
    private Object customStepsSeparated;
    @SerializedName("custom_mission")
    @Expose
    private Object customMission;
    @SerializedName("custom_goals")
    @Expose
    private Object customGoals;
    @SerializedName("comments")
    @Expose
    private List<Object> comments;
}
