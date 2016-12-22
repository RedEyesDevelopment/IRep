package irepdata.support;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Lenovo on 22.12.2016.
 */
public class CookiesHandler {

    public static String searchCookie(String cookieName, HttpServletRequest request) {
        return getCookie(cookieName, request);
    }

    public static boolean aquireCookie(String cookieName, HttpServletRequest request) {
        if (null!=getCookie(cookieName, request)) {
            return true;
        } else return false;
    }

    private static String getCookie(String cookieName, HttpServletRequest request){
        Cookie[] cook = request.getCookies();
        System.out.println("Cookie array has "+cook.length+" items");
        for (Cookie cookie : cook) {
            System.out.println("Cookie "+cookie.getName()+" has value of "+cookie.getValue());
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void setCookie(String cookieName, String value, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
