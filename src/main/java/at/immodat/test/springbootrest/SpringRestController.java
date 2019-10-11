package at.immodat.test.springbootrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.RestController
public class SpringRestController {


    // todo: https://medium.com/@bcarunmail/securing-rest-api-using-keycloak-and-spring-oauth2-6ddf3a1efcc2

    // todo: https://sandor-nemeth.github.io/java/spring/2017/06/15/spring-boot-with-keycloak.html


    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/test")
    public @ResponseBody ResponseEntity<String> test(@RequestParam(value="test", required = false) String test) {
        System.out.println("Received parameter: " + test);
        return new ResponseEntity<>("***" + test + "***", HttpStatus.OK);
    }

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/testUnsecured")
    public @ResponseBody ResponseEntity<String> testUnsecured(@RequestParam(value="test", required = false) String test) {
        System.out.println("Received parameter: " + test);
        return new ResponseEntity<>("***" + test + "***", HttpStatus.OK);
    }

    //------------------------------------------------------------------------------------------------------------------
}
