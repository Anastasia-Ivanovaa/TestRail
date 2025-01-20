package dto.api.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetProjectsRs {
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("projects")
    @Expose
    private List<Project> projects;
}
