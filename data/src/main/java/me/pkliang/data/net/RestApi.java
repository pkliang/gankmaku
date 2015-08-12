package me.pkliang.data.net;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import me.pkliang.gankmaku.domain.entity.Response;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Omistaja on 8/10/2015.
 */
public interface RestApi {

     String BASE_URL = "http://gank.avosapps.com/api/data";
     String TYPE_FULI = "福利";
     String TYPE_ANDROID = "Android";
     String TYPE_IOS = "iOS";

     @GET("/{type}/{pageSize}/{page}")
     Observable<Response> getGank(@Path("type") String type,
                                  @Path("pageSize") int pageSize,
                                  @Path("page") int page);

     class Factory {
          public static RestApi create(RequestInterceptor requestInterceptor, RestAdapter.LogLevel logLevel) {

               OkHttpClient client = new OkHttpClient();
               client.setConnectTimeout(30, TimeUnit.SECONDS);
               client.setReadTimeout(30, TimeUnit.SECONDS);
               RestAdapter restAdapter = new RestAdapter.Builder()
                       .setEndpoint(RestApi.BASE_URL)
                       .setLogLevel(logLevel)
                       .setRequestInterceptor(requestInterceptor)
                       .setClient(new OkClient(client))
                       .build();

               return restAdapter.create(RestApi.class);
          }

         public static RestApi create(RestAdapter.LogLevel logLevel) {

             OkHttpClient client = new OkHttpClient();
             client.setConnectTimeout(30, TimeUnit.SECONDS);
             client.setReadTimeout(30, TimeUnit.SECONDS);
             RestAdapter restAdapter = new RestAdapter.Builder()
                     .setEndpoint(RestApi.BASE_URL)
                     .setLogLevel(logLevel)
                     .setClient(new OkClient(client))
                     .build();

             return restAdapter.create(RestApi.class);
         }
     }
}

