package app;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Server {

    public static void start() {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions();
        vertx.deployVerticle(MainVerticle.class, options);
    }

    public static void main(String[] args) {
        Server.start();
    }
}
