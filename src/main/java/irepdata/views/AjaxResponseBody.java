package irepdata.views;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * Created by Admin on 08.12.2016.
 */
public class AjaxResponseBody<T> {

    @JsonView
    private String msg;

    @JsonView
    private String code;

    @JsonView
    private List<T> result;

    public String getMsg() {
        return msg;
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
