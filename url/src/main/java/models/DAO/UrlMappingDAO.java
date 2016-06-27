package models.DAO;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.HibernateBundle;
import models.UrlMapping;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Nidhi Mishra on 18/06/16.
 */
public class UrlMappingDAO extends AbstractDAO<UrlMapping> {

    private Logger logger = LoggerFactory.getLogger(UrlMappingDAO.class);

    @Inject
    public UrlMappingDAO(HibernateBundle hibernateBundle)
    {
        super(hibernateBundle.getSessionFactory());
    }

    public UrlMapping add(String longUrl) throws Exception {
        try {
            List<UrlMapping> urlMappingList = currentSession().createCriteria(UrlMapping.class).add(Restrictions.eq("longUrl", longUrl)).list();
            logger.info("========Added to UrlMapping=======");
            if(urlMappingList.size() == 0){
                UrlMapping urlMapping = new UrlMapping(longUrl);
                return persist(urlMapping);
            }
            return urlMappingList.get(0);
        } catch (HibernateException e) {
            logger.error("HibernateException : " + e.getMessage(), e);
        }
        return null;
    }


    public UrlMapping getLongUrl(String shortUrl) throws Exception {
        List<UrlMapping> urlMappingList = currentSession().createCriteria(UrlMapping.class).add(Restrictions.eq("id", Integer.valueOf(shortUrl))).list();
        return urlMappingList.get(0);
    }
}
