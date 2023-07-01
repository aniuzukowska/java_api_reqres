package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResponseLombokModel {
    int id;
    @JsonProperty("first_name")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }
}

