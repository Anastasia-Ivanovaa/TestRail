package dto.api.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {
    @SerializedName("next")
    @Expose
    private Object next;
    @SerializedName("prev")
    @Expose
    private Object prev;
}
