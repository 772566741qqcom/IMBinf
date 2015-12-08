package me.binf.im.server.router;

import java.lang.reflect.Method;

/**
 * Created by wangbin on 2015/12/8.
 */
public class Route {

    private Integer code;

    private Method action;

    private Object controller;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Method getAction() {
        return action;
    }

    public void setAction(Method action) {
        this.action = action;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
