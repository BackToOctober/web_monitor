package module;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public abstract class AbstractHandler {

    protected Router router;
    protected Vertx vertx;

    public AbstractHandler(Vertx vertx) {
        this.router = Router.router(vertx);
        this.vertx = vertx;
        initRouter();
    }

    public void installRouter(Router rootRouter, String path) {
        rootRouter.mountSubRouter(path, router);
    }

    public abstract void initRouter();
}
