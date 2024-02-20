package POJO;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"name",
"email",
"gender",
"status"
})
@Data
@AllArgsConstructor
@Builder
@ToString
public class DemoUser {

@JsonProperty("id")
public Integer id;
@JsonProperty("name")
public String name;
@JsonProperty("email")
public String email;
@JsonProperty("gender")
public String gender;
@JsonProperty("status")
public String status;

}
