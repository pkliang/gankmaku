package me.pkliang.gankmaku.application;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.pkliang.gankmaku.data.RepositoryImpl;
import me.pkliang.gankmaku.data.net.HttpLoggingInterceptor;
import me.pkliang.gankmaku.data.net.RestApi;
import me.pkliang.gankmaku.domain.Repository;

/**
 * Created by Omistaja on 8/10/2015.
 */
@Module public class AppModule {

  private final Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton Application application() {
    return application;
  }

  @Provides @Singleton RestApi provideRestApi() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    return RestApi.Factory.create(logging);
  }

  @Provides @Singleton Repository provideRepository(RepositoryImpl repository) {
    return repository;
  }
}
