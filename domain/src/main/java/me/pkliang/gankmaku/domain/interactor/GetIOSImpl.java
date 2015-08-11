package me.pkliang.gankmaku.domain.interactor;

import javax.inject.Inject;

import me.pkliang.gankmaku.domain.Repository;
import me.pkliang.gankmaku.domain.entity.Response;
import rx.Observable;

/**
 * Created by Omistaja on 8/11/2015.
 */
public class GetIOSImpl extends BaseUseCaseImpl implements GetIOS {

    @Inject
    public GetIOSImpl(Repository repository) {
        super(repository);
    }

    @Override
    public Observable<Response> execute(int pageSize, int page) {
        return repository.getIOS(pageSize, page);
    }
}
