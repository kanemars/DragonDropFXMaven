package kanemars;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class DragonDropFXLicensing {
    public static boolean IsLicensed () {
        String api = "https://api.gumroad.com/v2/licenses/verify?license_key=CC2BB8E1-C7154F4F-A1D8397D-45B90133&product_permalink=hJEZD";
        try {
            URL url = new URL(api);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            String json = getOutputOfConnection(connection);
            System.out.println(json);
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage() + e.getStackTrace());
            e.printStackTrace();
            return false;
        }
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
