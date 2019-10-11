package at.immodat.test.springbootrest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootRestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void test() throws Exception {


        mockMvc.perform(get("/test?test=hoemi"))
                .andExpect(content().string("***hoemi***"))
                .andExpect(status().isOk());
    }

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void testWithHttp() {
        // Keycloak client - access type - public:
        //      curl -d "client_id=login-rest" -d "username=user1" -d "password=user1" -d "grant_type=password" "http://sbspielwiese:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token"
        // Erhaltenen "acess_token" als Authorization-Header mitschicken
        //      curl -X GET http://localhost:8484/test?test=hoemi -H "Authorization: Bearer eyJhbGciOiJSUzI..."
    }

    //------------------------------------------------------------------------------------------------------------------
}
