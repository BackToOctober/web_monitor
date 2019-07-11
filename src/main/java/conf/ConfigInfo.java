package conf;

public class ConfigInfo {

    private static final String MYSQL_HOST = "192.168.6.7";
    private static final int MYSQL_PORT = 3306;
    private static final String MYSQL_USER = "dynamicads";
    private static final String MYSQL_PASS = "SFWg0NjBoOiLHGa";
    private static final String MYSQL_DB = "adn";
    public static final String BANNER_URL = "jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT
            + "/" + MYSQL_DB + "?" + "user=" + MYSQL_USER + "&password="
            + MYSQL_PASS + "&noAccessToProcedureBodies=true&autoReconnect=true&useUnicode=true" +
            "&&characterEncoding=ISO8859_1&characterSetResults=ISO8859_1";
}
