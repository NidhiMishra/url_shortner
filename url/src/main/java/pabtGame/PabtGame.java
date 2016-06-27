package pabtGame;

import com.google.inject.Guice;
import com.google.inject.Injector;
import configuration.PabtConfiguration;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import models.DummyModel;
import models.UrlMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GuiceInjector;


public class PabtGame extends Application<PabtConfiguration>
{
    private static final Logger logger = LoggerFactory.getLogger(PabtGame.class);
    public static void main ( String[] args ) throws Exception {
        new PabtGame().run(args);
    }

    private final HibernateBundle<PabtConfiguration> hibernateBundle = new HibernateBundle<PabtConfiguration>(DummyModel.class, UrlMapping.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(PabtConfiguration pabtConfiguration) {
            return pabtConfiguration.getDatabase();
        }
    };


    @Override
    public void initialize(Bootstrap<PabtConfiguration> bootstrap) {

        bootstrap.addBundle(hibernateBundle);
//        bootstrap.addBundle(new AssetsBundle("/PabtUI/", "/ui", "index.html"));
//        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }

    @Override
    public void run(PabtConfiguration pabtConfiguration, Environment environment) throws Exception {

        PabtModule pabtModule = new PabtModule(pabtConfiguration, hibernateBundle);

        //------- registering app resources -----------
        Injector injector = Guice.createInjector(pabtModule);
        GuiceInjector.assignInjector(injector);
        environment.jersey().register(injector.getInstance(PabtResource.class));
//        environment.jersey().setUrlPattern("/api/*");
    }
}
