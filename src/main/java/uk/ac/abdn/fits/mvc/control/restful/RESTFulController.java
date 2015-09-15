package uk.ac.abdn.fits.mvc.control.restful;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.ac.abdn.fits.business.restful.RESTFulRequest;


@Controller
public class RESTFulController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/restful", method = RequestMethod.GET)
    public @ResponseBody RESTFulRequest greeting(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new RESTFulRequest(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
