package irepdata.dto;

import irepdata.model.Idea;
import irepdata.model.Tag;
import irepdata.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Gvozd on 17.12.2016.
 */
public class IdeaDummy {

    public static IdeaDummy fillFromIdea(Idea sourceIdea){
        IdeaDummy dummy = new IdeaDummy();
        dummy.name = sourceIdea.getName();
        dummy.content = sourceIdea.getContent().getContentData();
        dummy.description = sourceIdea.getDescription();
        dummy.enabled = sourceIdea.isEnabled();
        dummy.image = sourceIdea.getImage();
        StringBuffer sb = new StringBuffer();
        for (Tag tag: sourceIdea.getTags()){
            sb.append(tag.getContent());
            sb.append(" ");
        }
        dummy.tags = sb.toString();
        return dummy;
    }

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
