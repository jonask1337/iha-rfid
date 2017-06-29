import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Jonas on 09.06.2017.
 */
public class Server {
    private String url = "";
    private int port = 8080;

    public Server(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public void updateProducts(ProductList products){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(products);
            System.out.println(json);
            URL reqUrl = new URL(String.format("%s:%d/api/updateProducts/", url, port));

            HttpURLConnection conn= (HttpURLConnection) reqUrl.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/json");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( json.length() ));
            conn.setUseCaches( false );

            DataOutputStream wr = new DataOutputStream( conn.getOutputStream());
                wr.write( json.getBytes() );

            System.out.println(conn.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
