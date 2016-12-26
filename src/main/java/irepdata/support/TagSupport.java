package irepdata.support;

import irepdata.model.Tag;

import java.util.Set;

/**
 * Created by Gvozd on 21.12.2016.
 */
public interface TagSupport {
    public Set<Tag> parseTagsFromStringToSet(String sourceString);
    public void parseTagsFromStringToSet(Set<Tag> targetSet, String sourceString, boolean isUpdating);
    public String parseTagsFromSetToString(Set<Tag> targetset);
}
