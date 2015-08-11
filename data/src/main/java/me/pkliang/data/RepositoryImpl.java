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
        return restApi
                .getGank(RestApi.TYPE_FULI, pageSize, page)
                .flatMap(response -> Hawk.putObservable(RestApi.TYPE_FULI + page, response))
                .flatMap(success -> Hawk.<Response>getObservable(RestApi.TYPE_FULI + page))
                .onErrorResumeNext(error -> Hawk.<Response>getObservable(RestApi.TYPE_FULI + page));
    }

    @Override
    public Observable<Response> getAndroid(int pageSize, int page) {
        return restApi
                .getGank(RestApi.TYPE_ANDROID, pageSize, page)
                .flatMap(response -> Hawk.putObservable(RestApi.TYPE_ANDROID + page, response))
                .flatMap(success -> Hawk.<Response>getObservable(RestApi.TYPE_ANDROID + page))
                .onErrorResumeNext(error -> Hawk.<Response>getObservable(RestApi.TYPE_ANDROID + page));
    }

    @Override
    public Observable<Response> getIOS(int pageSize, int page) {
        return restApi
                .getGank(RestApi.TYPE_IOS, pageSize, page)
                .flatMap(response -> Hawk.putObservable(RestApi.TYPE_IOS + page, response))
                .flatMap(success -> Hawk.<Response>getObservable(RestApi.TYPE_IOS + page))
                .onErrorResumeNext(error -> Hawk.<Response>getObservable(RestApi.TYPE_IOS + page));
    }
}
