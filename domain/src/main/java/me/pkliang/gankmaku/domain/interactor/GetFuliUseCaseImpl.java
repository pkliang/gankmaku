package me.pkliang.gankmaku.domain.interactor;

import javax.inject.Inject;

import me.pkliang.gankmaku.domain.Repository;
import me.pkliang.gankmaku.domain.entity.Response;
import rx.Observable;

/**
 * Created by Omistaja on 8/10/2015.
 */
public class GetFuliUseCaseImpl extends BaseUseCaseImpl implements GetFuliUseCase {

    @Inject
    public GetFuliUseCaseImpl(Repository repository) {
        super(repository);
    }

    @Override
    public Observable<Response> execute(int pageSize, int page) {
        return repository.getFuli(pageSize, page);
    }
}
