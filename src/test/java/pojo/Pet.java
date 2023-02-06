package pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    public String id;
    public String name;
    public Category category;
    public ArrayList<String> photoUrls;
    public ArrayList<Tag> tags;
    public String status;

    public static Pet petBuild(Map<String, String> params) {
        String id = params.get("id");
        String name = params.get("name");
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add(params.get("photoUrls"));
        String status = params.get("status");
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(Tag.tagBuild(params));
        return Pet.builder()
                .id(id)
                .category(Category.categoryBuild(params))
                .name(name)
                .photoUrls(photoUrls)
                .tags(tags)
                .status(status)
                .build();
    }

    public static String petResponseParam(String key) {
        if (key.contains("category")){
            key = key.replace("category", "").toLowerCase();
        }
        if (key.contains("tag")){
            key = key.replace("tag", "").toLowerCase();
        }
        return key;
    }
}
