package me.pkliang.data;

import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import me.pkliang.data.net.RestApi;
import me.pkliang.gankmaku.domain.Repository;
import me.pkliang.gankmaku.domain.entity.Response;
import rx.Observable;

/**
 * Created by Omistaja on 8/10/2015.
 */
public class RepositoryImpl implements Repository {

    private final RestApi restApi;

    @Inject
    public RepositoryImpl(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<Response> getFuli(int pageSize, int page) {
        return getResponse(RestApi.TYPE_FULI, pageSize, page);
    }

    @Override
    public Observable<Response> getAndroid(int pageSize, int page) {
        return getResponse(RestApi.TYPE_ANDROID, pageSize, page);
    }

    @Override
    public Observable<Response> getIOS(int pageSize, int page) {
        return getResponse(RestApi.TYPE_IOS, pageSize, page);
    }

    private Observable<Response> getResponse(String type, int pageSize, int page) {
        return restApi
                .getGank(type, pageSize, page)
                .flatMap(response -> Hawk.putObservable(type + page, response))
                .flatMap(success -> Hawk.<Response>getObservable(type + page))
                .onErrorResumeNext(error -> Hawk.<Response>getObservable(type + page))
                .filter(response1 -> response1 != null);
    }
}
