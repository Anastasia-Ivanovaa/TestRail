package dto.api.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProjectRs {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("announcement")
    @Expose
    private String announcement;
    @SerializedName("completed_on")
    @Expose
    private Integer completedOn;
    @SerializedName("default_role_id")
    @Expose
    private Integer defaultRoleId;
    @SerializedName("default_role")
    @Expose
    private String defaultRole;
    @SerializedName("is_completed")
    @Expose
    private Boolean isCompleted;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("show_announcement")
    @Expose
    private Boolean showAnnouncement;
    @SerializedName("suite_mode")
    @Expose
    private Integer suiteMode;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("users")
    @Expose
    private List<User> users;
    @SerializedName("groups")
    @Expose
    private List<Object> groups;
}
