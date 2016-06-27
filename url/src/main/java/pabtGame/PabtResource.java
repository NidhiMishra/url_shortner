package pabtGame;

import io.dropwizard.hibernate.UnitOfWork;
import models.UrlShortnerError;
import models.UrlShortnerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pabtGame.service.CreateBallService;
import pabtGame.service.UrlShortnerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by Nidhi Mishra on 07/11/15.
 */

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PabtResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBallService.class);
    @Inject
    private UrlShortnerService urlShortnerService;
    @Inject
    private UrlShortnerResponse urlShortnerResponse;



    @GET
    @Path("/{shortUrl : .+}")
    @UnitOfWork
    public Response redirectToLongUrl(@PathParam("shortUrl") String shortUrl){
        if(shortUrl.equals("favicon.ico")){
            return null;
        }
        String longUrl = urlShortnerService.getLongUrl(shortUrl);
        return Response.temporaryRedirect(UriBuilder.fromUri(longUrl + ".com").build()).build();
    }

    @GET
    @Path("/new/{longUrl : .+}")
    @UnitOfWork
    public Response getShortUrl(@PathParam("longUrl") String longUrl)
    {
        if(longUrl == null || longUrl.isEmpty() || !isUrlValid(longUrl)){
            return  Response.status(Response.Status.BAD_REQUEST).entity(new UrlShortnerError("Invalide Url", "please 'follow http://www.example.com' format")).build();
        }
        String shortUrl = urlShortnerService.getShortUrl(longUrl);
        UrlShortnerResponse urlShortnerResponse = new UrlShortnerResponse(longUrl, shortUrl);
        return Response.ok().entity(urlShortnerResponse).build();
    }

    private boolean isUrlValid(String longUrl) {

        if(!isHttpPresent(longUrl)){
            return false;
        }else{
            longUrl = longUrl.substring(4);
        }

        if(isSpresent(longUrl)){
            longUrl = longUrl.substring(1);
        }

        if(!isForwardSlashPresent(longUrl)){
            return false;
        }
        longUrl = longUrl.substring(3);
        return !(!isWWWpresent(longUrl) || !isCOMpresent(longUrl));

    }

    private boolean isCOMpresent(String longUrl) {
        String last4char = longUrl.substring(longUrl.length()-4);
        return last4char.equals(".com");
    }

    private boolean isWWWpresent(String longUrl) {
        String first3Char = longUrl.substring(0,4);
        return first3Char.equals("www.");
    }

    private boolean isForwardSlashPresent(String longUrl) {
        String first3Char = longUrl.substring(0,3);
        return first3Char.equals("://");
    }

    private boolean isSpresent(String longUrl) {
        String firstChar = String.valueOf(longUrl.charAt(0));
        return firstChar.equals("s");
    }

    private boolean isHttpPresent(String longUrl) {
        String first4Char = longUrl.substring(0,4);
        return first4Char.equals("http");
    }
}














