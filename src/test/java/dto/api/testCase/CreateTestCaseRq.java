package dto.api.testCase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTestCaseRq {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("template_id")
    @Expose
    private Integer templateId;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("priority_id")
    @Expose
    private Integer priorityId;
    @SerializedName("estimate")
    @Expose
    private String estimate;
    @SerializedName("refs")
    @Expose
    private String refs;
    @SerializedName("custom_preconds")
    @Expose
    private String customPreconds;
    @SerializedName("custom_steps")
    @Expose
    private String customSteps;
    @SerializedName("custom_expected")
    @Expose
    private String customExpected;
}
