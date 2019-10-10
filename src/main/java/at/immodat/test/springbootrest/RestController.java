package at.immodat.test.springbootrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/test")
    public @ResponseBody
    ResponseEntity<String> test(@RequestParam(value="test", required = false) String test) {
        System.out.println("Received parameter: " + test);
        return new ResponseEntity<>("***" + test + "***", HttpStatus.OK);
    }

    //------------------------------------------------------------------------------------------------------------------
}
