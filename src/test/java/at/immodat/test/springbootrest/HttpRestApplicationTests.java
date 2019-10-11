package at.immodat.test.springbootrest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRestApplicationTests {

    //------------------------------------------------------------------------------------------------------------------
    @Test
    @Ignore("Manueller Test. Rest-Service und Keycloak müssen gestartet sein.")
    public void testWithHttp() throws Exception {
        // Keycloak client - access type - public:
        //      curl -d "client_id=login-app" -d "username=user1" -d "password=user1" -d "grant_type=password" "http://sbspielwiese:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token"
        // Erhaltenen "acess_token" als Authorization-Header mitschicken
        //      curl -X GET http://localhost:8484/test?test=hoemi -H "Authorization: Bearer eyJhbGciOiJSUzI..."
        // HTTP CLient aufsetzen...

        HttpClient client = HttpClientBuilder.create().build();

        // Login Request...
        final String realm = "SpringBootKeycloak";
        final String clientId = "login-app";
        final String userName = "user1";
        final String password = "user1";

        HttpPost loginRequest = new HttpPost("http://sbspielwiese:8180/auth/realms/" + realm + "/protocol/openid-connect/token");

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("grant_type", "password"));
        parameters.add(new BasicNameValuePair("client_id", clientId));
        parameters.add(new BasicNameValuePair("username", userName));
        parameters.add(new BasicNameValuePair("password", password));
        loginRequest.setEntity(new UrlEncodedFormEntity(parameters));

        HttpResponse loginResponse = client.execute(loginRequest);

        String responseString = EntityUtils.toString(loginResponse.getEntity());
        System.out.println("Login response: " + responseString);

        // Parse Login Response...
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> responseMap = springParser.parseMap(responseString);

        final String access_token = (String) responseMap.get("access_token");
        System.out.println("Access token: " + access_token);

        // Aufruf des Webservices mit Access token...
        HttpGet restRequest = new HttpGet("http://localhost:8484/test?test=hoemi");
        restRequest.setHeader("Authorization", "Bearer " + access_token);
        HttpResponse restResponse = client.execute(restRequest);
        String restResponseString = EntityUtils.toString(restResponse.getEntity());
        System.out.println("Rest response: " + restResponseString);

        Assert.assertEquals("***hoemi***", restResponseString);
    }

    //------------------------------------------------------------------------------------------------------------------
}
