package irepdata.support;

import irepdata.dao.TagDao;
import irepdata.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Gvozd on 21.12.2016.
 */
@Service
public class TagSupportTool implements TagSupport{

    @Autowired
    private TagDao tagDao;

    public TagDao getTagDao() {
        return tagDao;
    }

    public void setTagDao(TagDao TagDAO) {
        tagDao = TagDAO;
    }

    @Transactional
    public Set<Tag> parseTagsFromStringToSet(String sourceString) {
        return parsing(new HashSet<Tag>(), sourceString, false);
    }

    @Transactional
    public void parseTagsFromStringToSet(Set<Tag> targetSet, String sourceString, boolean isUpdating) {
        parsing(targetSet, sourceString, isUpdating);
    }

    private Set<Tag> parsing(Set<Tag> targetSet, String sourceString, boolean isUpdating) {
        String delims = "[ ]+";
        String[] tagStringList = sourceString.split(delims);
        List<Tag> tagList = tagDao.getSortedTagList("id", true, false);
        List<Tag> resultList = new ArrayList<>();
        List<String> creatingList = new ArrayList<>();
        boolean newTagFlag = false;
        for (String searchableTag : tagStringList) {
            newTagFlag = false;
            System.out.println("searcheableTag(String) is " + searchableTag);
            for (Tag tag : tagList) {
                if (tag.isEnabled()) {
                    if (tag.getContent().equals(searchableTag)) {
                        System.out.println("Tag is equal " + tag);
                        resultList.add(tag);
                        newTagFlag = false;
                        break;
                    } else newTagFlag = true;
                    System.out.println("newTagFlag is" + newTagFlag);
                }
            }
            System.out.println("newTagFlag is" + newTagFlag);
            if (newTagFlag == true) {
                System.out.println("CreatingList add " + searchableTag);
                creatingList.add(searchableTag);
            }
        }
        Set<Tag> tagSet;
        if (!creatingList.isEmpty()) {
            tagDao.createTags(creatingList);
            List<Tag> persistTagList = tagDao.getTagList(creatingList);
            tagSet = new HashSet<>(persistTagList);
        } else {
            tagSet = new HashSet<>();
        }
        tagSet.addAll(resultList);

        if (isUpdating) {
            targetSet.addAll(tagSet);
            targetSet.retainAll(tagSet);
        } else targetSet = tagSet;
        return targetSet;
    }

    @Transactional
    public String parseTagsFromSetToString(Set<Tag> targetset){
        StringBuilder result = new StringBuilder();
        for (Tag currenttag:targetset){
            result.append(currenttag.getContent());
            result.append(" ");
        }
        return result.toString();
    }
}
