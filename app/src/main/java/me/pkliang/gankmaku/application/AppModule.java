package me.pkliang.gankmaku.application;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.pkliang.data.RepositoryImpl;
import me.pkliang.data.net.RestApi;
import me.pkliang.gankmaku.domain.Repository;
import retrofit.RestAdapter;

/**
 * Created by Omistaja on 8/10/2015.
 */
@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application application() {
        return application;
    }

    @Provides
    @Singleton
    RestApi provideRestApi() {
        return RestApi.Factory.create(RestAdapter.LogLevel.FULL);
    }

    @Provides
    @Singleton
    Repository provideRepository(RepositoryImpl repository) {
        return repository;
    }
 }
