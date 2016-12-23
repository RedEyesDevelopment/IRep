package irepdata.service;

import irepdata.model.Content;

/**
 * Created by Gvozd on 27.11.2016.
 */
public interface ContentService {
    public Content getContent(Long id);
    public void createContent(Content content);
    public void updateContent(Long ideaId, String content);
}
