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
public class Tag {

    public String id;
    public String name;

    public static Tag tagBuild(Map<String, String> params) {
        String id = params.get("tagId");
        String name = params.get("tagName");
        return Tag.builder().id(id).name(name).build();
    }
}
