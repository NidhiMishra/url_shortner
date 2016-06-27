package filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* Created by Nidhi Mishra on 17/02/15.
*/

public class ResponseFilter implements ContainerResponseFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        containerResponseContext.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS");
        List<String> requestHeader = containerRequestContext.getHeaders().get("Access-Control-Request-Headers");
        if (requestHeader != null && !requestHeader.isEmpty()) {
            logger.info("setting access control headers.");
            containerResponseContext.getHeaders().put("Access-Control-Allow-Headers", new ArrayList<Object>(requestHeader));
        }
        logger.info(">>>>>>>>>> Bid Adieu <<<<<<<<<<<");
    }
}


