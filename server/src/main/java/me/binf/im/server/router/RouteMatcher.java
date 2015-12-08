package me.binf.im.server.router;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbin on 2015/12/8.
 */
public class RouteMatcher {

    private List<Route> routes;

    public RouteMatcher(List<Route> routes) {
        this.routes = routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }


    public Route findRoute(Integer code) {

        for (Route route : this.routes) {
            if(route.getCode().equals(code)){
                return route;
            }
        }
        return null;
    }




}
