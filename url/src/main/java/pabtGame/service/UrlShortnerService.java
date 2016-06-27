package pabtGame.service;

import com.google.inject.Inject;
import models.DAO.UrlMappingDAO;
import models.UrlMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Nidhi Mishra on 18/06/16.
 */
public class UrlShortnerService {
    private static final Logger logger = LoggerFactory.getLogger(UrlShortnerService.class);

    @Inject
    UrlMappingDAO urlMappingDAO;

    public String getLongUrl(String shortUrl){
        UrlMapping urlMapping = new UrlMapping();
        try {
            urlMapping = urlMappingDAO.getLongUrl(shortUrl);
            return String.valueOf(urlMapping.getLongUrl());
        } catch (Exception e) {
            logger.error("Exception while adding to mapping table : " + e.getMessage(), e);
        }
        return null;
    }


    public String getShortUrl(String longUrl) {
        String slightlyShortUrl = removeObviousSubStrings(longUrl);
        try {
            UrlMapping urlMapping = urlMappingDAO.add(slightlyShortUrl);
            return String.valueOf(urlMapping.getId());
        } catch (Exception e) {
            logger.error("Exception while adding to mapping table : " + e.getMessage(), e);
        }

        return null;
    }

    private String removeObviousSubStrings(String longUrl) {
        return longUrl.substring(0, longUrl.length()-4);
    }


}
