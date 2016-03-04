package kanemars;

import org.junit.Assert;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by P10158774 on 26/02/2016.
 */
public class DragonDropFXControllerTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testInitialize() throws Exception {

    }

    @org.junit.Test
    public void PostHttpsGumRoadLicensingExpectResponse() throws Exception {
        String api = "https://api.gumroad.com/v2/licenses/verify?license_key=CC2BB8E1-C7154F4F-A1D8397D-45B90133&product_permalink=hJEZD";
        URL url = new URL(api);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        String json = getOutputOfConnection(connection);
        Assert.assertNotNull(json);
    }

    public static String getOutputOfConnection (URLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}