package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    public String id;
    public String petId;
    public String quantity;
    public String shipDate;
    public String status;
    public String complete;

    public static Order orderBuild(Map<String, String> params) {
        return Order.builder()
                .id(params.get("id"))
                .petId(params.get("petId"))
                .quantity(params.get("quantity"))
                .shipDate(params.get("shipDate"))
                .status(params.get("status"))
                .complete(params.get("complete"))
                .build();
    }
}
