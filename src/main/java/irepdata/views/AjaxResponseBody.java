package irepdata.views;

import com.fasterxml.jackson.annotation.JsonView;
import irepdata.service.UserServiceImpl;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 08.12.2016.
 */
public class AjaxResponseBody<T> {
    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @JsonView(JSONViews.List.class)
    private String msg;

    @JsonView(JSONViews.List.class)
    private String code;

    @JsonView(JSONViews.List.class)
    private List<T> result;

    public String getMsg() {
        return msg;
    }

    public AjaxResponseBody() {
    logger.info("Creating AjaxResponseBody");
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
