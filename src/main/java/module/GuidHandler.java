package module;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;

public class GuidHandler extends AbstractHandler {

    private GuidManager guidManager;
    private FreeMarkerTemplateEngine templateEngine;

    public GuidHandler(Vertx vertx, GuidManager guidManager, FreeMarkerTemplateEngine templateEngine) {
        super(vertx);
        this.guidManager = guidManager;
        this.templateEngine = templateEngine;
    }

    @Override
    public void initRouter() {
        router.route("/search-guid").blockingHandler(this::renderSearchGuidPage);
        router.route("/api/guid/search-guid").blockingHandler(this::searchGuid);
        router.route("/api/guid/demo").blockingHandler(this::demoGuid);
        router.route().handler(StaticHandler.create()
                .setCachingEnabled(false)
                .setDirectoryListing(true)
                .setWebRoot("templates"));
    }

    public void renderPage(RoutingContext routingContext, String file) {
        templateEngine.render(routingContext.data(), file, ar -> {
            if (ar.succeeded()) {
                routingContext.response().putHeader("Content-Type", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                routingContext.response().end(ar.result());
            } else {
                routingContext.fail(ar.cause());
            }
        });
    }

    public void renderSearchGuidPage(RoutingContext routingContext) {
        renderPage(routingContext, "templates/search-guid.ftl");
    }

    public void searchGuid(RoutingContext routingContext) {
        long guid = Long.parseLong(routingContext.request().getParam("guid"));
        guidManager.searchGuid(guid);
    }

    public void demoGuid(RoutingContext routingContext) {
        routingContext.response().end("demo");
    }

}
