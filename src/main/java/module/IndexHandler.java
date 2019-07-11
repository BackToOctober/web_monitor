package module;

import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;

public class IndexHandler extends AbstractHandler{

    private FreeMarkerTemplateEngine templateEngine;

    public IndexHandler(Vertx vertx, FreeMarkerTemplateEngine templateEngine) {
        super(vertx);
        this.templateEngine = templateEngine;
    }

    @Override
    public void initRouter() {
        router.route("/").blockingHandler(this::renderIndex);
    }

    public void renderIndex(RoutingContext routingContext) {
        templateEngine.render(routingContext.data(), "templates/index.ftl", ar -> {
            if (ar.succeeded()) {
                routingContext.response().putHeader("Content-Type", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                routingContext.response().end(ar.result());
            } else {
                routingContext.fail(ar.cause());
            }
        });
    }
}
