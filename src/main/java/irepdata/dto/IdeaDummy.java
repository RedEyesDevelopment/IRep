package irepdata.dto;

import irepdata.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Gvozd on 17.12.2016.
 */
public class IdeaDummy {

    @Autowired
    private TagService tagService;

    private String name;
    private String description;
    private String image;
    private String tags;
    private String content;
    private boolean enabled;

    public IdeaDummy(TagService tagService) {
        this.tagService = tagService;
        System.out.println(tagService.toString());
    }

    public IdeaDummy() {
    }

    public TagService getTagService() {
        return tagService;
    }

    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
