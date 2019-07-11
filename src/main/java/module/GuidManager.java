package module;

import conf.ConfigInfo;
import io.vertx.ext.web.RoutingContext;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GuidManager {

    private static final String GET_URL = "SELECT url FROM adn_raw_banner_ecomx WHERE bannerid = ?";

    public JSONObject searchGuid(long guid) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("guid", guid);
        try {
            Connection connection = DriverManager.getConnection(ConfigInfo.BANNER_URL);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

//    private JSONObject getAllInfo(long guid, String set, Connection connection, String sql,String product){
//        JSONObject jsonObject = getProduct(guid, set, connection, sql,product);
//        return jsonObject;
//    }
//
//    resultJson.put("ecom_product", getAllInfo(guid, ConfigInfo.SET_ECOM_ADS_OBJECT,
//                                              connection, "SELECT url FROM adn_raw_banner_ecomx WHERE bannerid = ","ecom"));
}
