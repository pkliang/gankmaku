package me.pkliang.gankmaku.ganhuo;

import javax.inject.Inject;

import me.pkliang.gankmaku.base.presenter.PaginationRxPresenter;
import me.pkliang.gankmaku.domain.entity.Response;
import me.pkliang.gankmaku.domain.interactor.GetAndroidUseCase;

/**
 * Created by Omistaja on 8/11/2015.
 */
public class AndroidPresenter extends PaginationRxPresenter<GanhuoView, Response> {
    private final GetAndroidUseCase getAndroidUseCase;

    @Inject
    public AndroidPresenter(GetAndroidUseCase getAndroidUseCase) {
        this.getAndroidUseCase = getAndroidUseCase;
    }

    @Override
    protected void load(int page, int limit, boolean pullToRefresh) {
        subscribe(getAndroidUseCase.execute(limit, page), pullToRefresh);
    }

    @Override
    protected boolean hasMore(Response data) {
        return data.getResults().size() >= mLimit;
    }
}
