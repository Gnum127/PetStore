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
public class Category {

    public String id;
    public String name;

    public static Category categoryBuild(Map<String, String> params) {
        String id = params.get("categoryId");
        String name = params.get("categoryName");
        return Category.builder().id(id).name(name).build();
    }
}