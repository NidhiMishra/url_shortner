package filters;

import com.sun.jersey.core.util.ReaderWriter;
import org.slf4j.LoggerFactory;
import utils.GuiceInjector;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class RequestFilter implements ContainerRequestFilter {

    public RequestFilter() {
        GuiceInjector.getInjector().injectMembers(this);
    }

    final private static org.slf4j.Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
//        setUidForRequest(containerRequestContext);
        logger.info("<<<<<<<<<< Got New Request >>>>>>>>>>>");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = containerRequestContext.getEntityStream();
        ReaderWriter.writeTo(in, out);

        byte[] requestEntity = out.toByteArray();
        logger.info("METHOD : " + containerRequestContext.getMethod());
        logger.info("URL : " + containerRequestContext.getUriInfo().getAbsolutePath());
        logger.info("BODY : " + out);

        MultivaluedMap<String, String> requestHeaders = containerRequestContext.getHeaders();
        logger.info("Request Headers: ");
        for(MultivaluedMap.Entry entry : requestHeaders.entrySet()) {
            logger.info("key: " + entry.getKey() + " , " + "value: " + entry.getValue());
        }
        containerRequestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
    }
}
