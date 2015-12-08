package me.binf.im.server.router;

import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangbin on 2015/12/8.
 */
public class Routers {

    protected final org.slf4j.Logger log = LoggerFactory.getLogger(MessageRouter.class);

    private List<Route> routes = new ArrayList<Route>();

    public Routers() {
    }

    public void addRoute(List<Route> routes){
        routes.addAll(routes);
    }

    public void addRoute(Route route){
        routes.add(route);
    }

    public void removeRoute(Route route){
        routes.remove(route);
    }

    public void addRoute(Integer code, Method action, Object controller){
        Route route = new Route();
        route.setCode(code);
        route.setAction(action);
        route.setController(controller);

        routes.add(route);
        log.info("Add Route：[" + code + "]");
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

}
