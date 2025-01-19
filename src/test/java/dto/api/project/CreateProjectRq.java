package dto.api.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjectRq {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("announcement")
    @Expose
    private String announcement;
    @SerializedName("show_announcement")
    @Expose
    private Boolean showAnnouncement;
}
