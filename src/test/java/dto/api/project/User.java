package dto.api.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("global_role_id")
    @Expose
    private Object globalRoleId;
    @SerializedName("global_role")
    @Expose
    private Object globalRole;
    @SerializedName("project_role_id")
    @Expose
    private Object projectRoleId;
    @SerializedName("project_role")
    @Expose
    private Object projectRole;
}
