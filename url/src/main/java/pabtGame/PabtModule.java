package pabtGame;

import com.google.inject.AbstractModule;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import configuration.PabtConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provides;
import com.google.inject.Singleton;
/**
* Created by Nidhi Mishra on 06/02/16.
*/
public class PabtModule extends AbstractModule {

    private static final Logger logger = LoggerFactory.getLogger(PabtGame.class);
    private PabtConfiguration pabtConfiguration;
    private HibernateBundle<PabtConfiguration> hibernateBundle;

    public PabtModule(PabtConfiguration pabtConfiguration, HibernateBundle<PabtConfiguration> hibernateBundle) {
        this.pabtConfiguration = pabtConfiguration;
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    protected void configure() {
        install(ThrowingProviderBinder.forModule(this));
        bind(HibernateBundle.class).toInstance(hibernateBundle);
        bind(PabtConfiguration.class).toInstance(pabtConfiguration);
    }

    @Provides
    @Singleton
    public SessionFactory providesSessionFactory() {
        return hibernateBundle.getSessionFactory();
    }


}
