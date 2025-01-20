package dto.api.testCase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateTestCaseRq {
    @SerializedName("section_id")
    @Expose
    private Integer sectionId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("template_id")
    @Expose
    private Object templateId;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("priorityId")
    @Expose
    private Integer priorityId;
    @SerializedName("estimate")
    @Expose
    private String estimate;
    @SerializedName("milestone_id")
    @Expose
    private Object milestoneId;
    @SerializedName("refs")
    @Expose
    private String refs;
}
