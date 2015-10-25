package me.pkliang.gankmaku.data.net;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import me.pkliang.gankmaku.domain.entity.Response;
import retrofit.MoshiConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Omistaja on 8/10/2015.
 */
public interface RestApi {

    String BASE_URL = "http://gank.avosapps.com/api/data/";
    String TYPE_FULI = "福利";
    String TYPE_ANDROID = "Android";
    String TYPE_IOS = "iOS";

    @GET("{type}/{pageSize}/{page}")
    Observable<Response> getGank(@Path("type") String type,
                                 @Path("pageSize") int pageSize,
                                 @Path("page") int page);

    class Factory {
        public static RestApi create(Interceptor requestInterceptor) {
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(30, TimeUnit.SECONDS);
            client.setReadTimeout(30, TimeUnit.SECONDS);
            client.interceptors().add(requestInterceptor);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RestApi.class);
        }
    }
}

