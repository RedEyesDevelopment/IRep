package irepdata.support;

import irepdata.dto.IdeaDummy;
import irepdata.model.Content;
import irepdata.model.Idea;
import irepdata.model.Tag;

/**
 * Created by Gvozd on 21.12.2016.
 */
public class IdeaDummyConversionTool {
    public static void fillDummyFromIdea(Idea sourceIdea, IdeaDummy targetDummy){
        targetDummy.setName(sourceIdea.getName());
        targetDummy.setContent(sourceIdea.getContent().getContentData());
        targetDummy.setDescription(sourceIdea.getDescription());
        targetDummy.setEnabled(sourceIdea.isEnabled());
        targetDummy.setImage(sourceIdea.getImage());
        StringBuffer sb = new StringBuffer();
        for (Tag tag: sourceIdea.getTags()){
            sb.append(tag.getContent());
            sb.append(" ");
        }
        targetDummy.setTags(sb.toString());
    }

    public static void fillIdeaFromDummy(IdeaDummy sourceDummy, Idea targetIdea){
        targetIdea.setName(sourceDummy.getName());
        targetIdea.setDescription(sourceDummy.getDescription());
        targetIdea.setImage(sourceDummy.getImage());
        targetIdea.setEnabled(sourceDummy.isEnabled());

        String content = sourceDummy.getContent();
        Content contentData = new Content();
        contentData.setContentData(content);
        targetIdea.setContent(contentData);
    }
}
