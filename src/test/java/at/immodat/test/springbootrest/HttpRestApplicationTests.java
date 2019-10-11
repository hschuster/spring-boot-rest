package at.immodat.test.springbootrest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HttpRestApplicationTests {

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void testWithHttp() throws Exception {
        // Keycloak client - access type - public:
        //      curl -d "client_id=login-rest" -d "username=user1" -d "password=user1" -d "grant_type=password" "http://sbspielwiese:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token"
        // Erhaltenen "acess_token" als Authorization-Header mitschicken
        //      curl -X GET http://localhost:8484/test?test=hoemi -H "Authorization: Bearer eyJhbGciOiJSUzI..."
        // HTTP CLient aufsetzen...

        HttpClient client = HttpClientBuilder.create().build();

        // Login Request mit Basic Authentication...
        HttpPost loginRequest = new HttpPost("http://sbspielwiese:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token");

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("grant_type", "password"));
        parameters.add(new BasicNameValuePair("client_id", "login-rest"));
        parameters.add(new BasicNameValuePair("username", "user1"));
        parameters.add(new BasicNameValuePair("password", "user1"));

        loginRequest.setEntity(new UrlEncodedFormEntity(parameters));
        HttpResponse loginResponse = client.execute(loginRequest);


        final String responseString = EntityUtils.toString(loginResponse.getEntity());
        System.out.println("Login response: " + responseString);


    }

    //------------------------------------------------------------------------------------------------------------------
}
