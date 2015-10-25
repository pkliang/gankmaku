package me.pkliang.gankmaku.application;

import android.app.Application;
import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.pkliang.gankmaku.data.RepositoryImpl;
import me.pkliang.gankmaku.data.net.RestApi;
import me.pkliang.gankmaku.domain.Repository;

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
        Interceptor interceptor = chain -> {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.i("Retrofit", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.i("Retrofit", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        };
        return RestApi.Factory.create(interceptor);
    }

    @Provides
    @Singleton
    Repository provideRepository(RepositoryImpl repository) {
        return repository;
    }
 }
