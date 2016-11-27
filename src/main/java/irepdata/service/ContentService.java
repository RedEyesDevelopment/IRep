package irepdata.service;

import irepdata.model.Content;

/**
 * Created by Gvozd on 27.11.2016.
 */
public interface ContentService {
    public Content getContent(String parameterType, Long id);
    public void createContent(Content content);
    public boolean deleteContent(Long ideaId);
    public boolean updateContent(String content, Long ideaId);
}
