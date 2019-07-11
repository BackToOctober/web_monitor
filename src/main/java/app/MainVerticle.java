package app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;
import module.GuidHandler;
import module.GuidManager;
import module.IndexHandler;

public class MainVerticle extends AbstractVerticle{

    public GuidHandler guidHandler;
    public GuidManager guidManager;
    public IndexHandler indexHandler;
    public FreeMarkerTemplateEngine templateEngine;

    public MainVerticle() {}

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        guidManager = new GuidManager();
        templateEngine = FreeMarkerTemplateEngine.create(vertx);
        guidHandler = new GuidHandler(vertx, guidManager, templateEngine);
        indexHandler = new IndexHandler(vertx, templateEngine);
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/*").handler(this::writePath);
        router.post().handler(BodyHandler.create());
        indexHandler.installRouter(router, "/");
        guidHandler.installRouter(router, "/guid");
        router.route().handler(StaticHandler.create()
                .setCachingEnabled(false)
                .setDirectoryListing(true)
                .setWebRoot("templates"));
        router.route("/*").handler(this::failPathHandler);

        server.requestHandler(router).listen(8080,"0.0.0.0", res->{
            if (res.succeeded()) {
                System.out.println("server start at 8080");
                startFuture.complete();
            } else {
                System.out.println("server start failed at 8080");
                startFuture.fail(res.cause());
            }
        });
    }

    public void writePath(RoutingContext routingContext) {
        System.out.println(routingContext.request().uri());
        routingContext.next();
    }

    public void failPathHandler(RoutingContext routingContext) {
        routingContext.response().setStatusCode(404).end("fail 404");
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        super.stop(stopFuture);
    }
}
