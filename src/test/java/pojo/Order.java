package pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    public String id;
    public String petId;
    public String quantity;
    public String shipDate;
    public String status;
    public String complete;

    public static Order orderBuild(Map<String, String> params) {
        String id = params.get("id");
        String petId = params.get("petId");
        String quantity = params.get("quantity");
        String shipDate = params.get("shipDate");
        String status = params.get("status");
        String complete = params.get("complete");
        return Order.builder()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .shipDate(shipDate)
                .status(status)
                .complete(complete)
                .build();
    }
}
