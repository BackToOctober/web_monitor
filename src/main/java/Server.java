//package com.vcc.bigdata.kinghub.search.app.service;
//
//import com.vcc.bigdata.kinghub.search.app.config.ConfigInfo;
//import com.vcc.bigdata.kinghub.search.app.preparedataset.GreedyExtraction;
//import com.vcc.bigdata.kinghub.search.app.query.channel.DataChannelES;
//import com.vcc.bigdata.kinghub.search.app.query.post.DataPostES;
//import com.vcc.bigdata.kinghub.search.app.query.TermExtraction;
//import com.vcc.bigdata.kinghub.search.app.query.user.DataUserES;
//import com.vcc.bigdata.util.MonitoringUtil;
//import io.vertx.core.AbstractVerticle;
//import io.vertx.core.DeploymentOptions;
//import io.vertx.core.Vertx;
//import io.vertx.core.http.HttpMethod;
//import io.vertx.core.http.HttpServer;
//import io.vertx.ext.web.Router;
//import io.vertx.ext.web.handler.BodyHandler;
//
//import java.io.FileNotFoundException;
//import java.net.UnknownHostException;
//import java.util.Properties;
//
//public class Server extends AbstractVerticle {
//
//    HttpServer server;
//    public static Properties properties;
//    private static DataPostES dataPostES;
//    private static DataUserES dataUserES;
//    private static DataChannelES dataChannelES;
//    private static TermExtraction extraction;
//
//    @Override
//    public void start() throws Exception {
//
//        server = vertx.createHttpServer();
//        Router router = Router.router(vertx);
//        router.post().handler(BodyHandler.create());
//        server.requestHandler(router::accept).listen(Integer.parseInt(properties.getProperty("PORT")), handler -> {
//            if (handler.failed())
//                System.out.println(handler.cause().getMessage() + "\t" + handler.cause());
//            else
//                System.out.println("success");
//        });
//
//        router.route("/*").handler(x -> {
//            x.response().putHeader("access-control-allow-origin", "*")
//                    .putHeader("access-control-allow-headers", "user-id, Content-Type, Accept, X-Requested-With")
//                    .putHeader("access-control-allow-methods", "GET, POST, DELETE, PUT, OPTIONS")
//                    .putHeader("access-control-allow-credentials","true");
//            x.next();
//        });
//
//        router.route(HttpMethod.POST, "/kinghub/search-post").handler(new BasicPostHandler(dataPostES, extraction, properties.getProperty("INDEX_KINGHUB_POST")));
//        router.route(HttpMethod.GET, "/kinghub/search-user").handler(new BasicUserHandler(dataUserES, extraction, properties.getProperty("INDEX_KINGHUB_USER")));
//        router.route(HttpMethod.GET, "/kinghub/suggest-user").handler(new BasicUserCompletion(properties.getProperty("INDEX_KINGHUB_USER")));
//        router.route(HttpMethod.GET, "/kinghub/search-channel").handler(new BasicChannelHandler(dataChannelES, extraction, properties.getProperty("INDEX_KINGHUB_CHANNEL")));
//        router.route(HttpMethod.POST, "/kinghub/update-user").handler(new UpdateInfoUserHandler());
//        router.route(HttpMethod.POST, "/kinghub/update-status-user").handler(new UpdateStatusUserHandler());
//        router.route(HttpMethod.POST, "/kinghub/suggest-hashtag").handler(new BasicHashtagCompletion(properties.getProperty("INDEX_KINGHUB_HASHTAG")));
//        router.route(HttpMethod.GET, "/kinghub/history-profile").handler(new BasicGotoUser(properties.getProperty("INDEX_KINGHUB_USER")));
//    }
//
//    public static void init() throws FileNotFoundException, UnknownHostException {
//        properties = ConfigInfo.getProperties();
////        VCTokenizer tokenizer=new VCTokenizer("resource/VCTokenizer/model");
////        extraction=new TermExtraction(tokenizer, new GreedyExtraction("resource/data/dictionary_cafebiz.txt,resource/data/stopword.txt",false));
//        dataPostES = new DataPostES();
//        dataUserES = new DataUserES();
//        dataChannelES = new DataChannelES();
//    }
//
//    public static void main(String[] args) throws FileNotFoundException, UnknownHostException {
//        //TODO new thread for monitor
//        Runnable runnable = new Monitor();
//        Thread thread = new Thread(runnable);
//        thread.start();
//        //TODO Auto-generated method stub
//        Server.init();
//        DeploymentOptions options = new DeploymentOptions();
//        options.setWorker(true);
//        options.setWorkerPoolSize(10);
//        options.setInstances(10);
//        Vertx vertx = Vertx.vertx();
//        vertx.deployVerticle(Server.class.getName(), options);
//    }
//
//    public static class Monitor implements Runnable {
//        @Override
//        public void run() {
//            System.out.println("Init Monitor for kinghub-search services ! ..");
//            while (true) {
//                MonitoringUtil.pushMessages("kinghub-search-services", "tungdd",
//                        "kinghub-search", 2, MonitoringUtil.STATUS_SUCCESS, System.currentTimeMillis());
//                try {
//                    Thread.sleep(59000 * 2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    MonitoringUtil.pushMessages("kinghub-search-services", "tungdd",
//                            "kinghub-search", 2, MonitoringUtil.STATUS_ERROR, System.currentTimeMillis());
//
//                }
//            }
//        }
//    }
//
//}
