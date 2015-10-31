package me.pkliang.gankmaku.domain;

import me.pkliang.gankmaku.domain.entity.Response;
import rx.Observable;

/**
 * Created by Omistaja on 8/10/2015.
 */
public interface Repository {

  Observable<Response> getFuli(int pageSize, int page);

  Observable<Response> getAndroid(int pageSize, int page);

  Observable<Response> getIOS(int pageSize, int page);
}
