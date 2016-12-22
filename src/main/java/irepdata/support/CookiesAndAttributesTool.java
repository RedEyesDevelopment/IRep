package irepdata.support;

import javax.servlet.http.HttpSession;

/**
 * Created by Lenovo on 22.12.2016.
 */
public class CookiesAndAttributesTool {
    public static AttributeNode searchAttributeInSession(String attribName, HttpSession session, boolean deleteAfterSearch){
        Object obj = session.getAttribute(attribName);
        if (null==obj) return new AttributeNode();
        if (deleteAfterSearch) session.removeAttribute(attribName);
        return new AttributeNode(obj);
    }
}
