package me.pkliang.gankmaku.ganhuo;

import javax.inject.Inject;

import me.pkliang.gankmaku.base.presenter.PaginationRxPresenter;
import me.pkliang.gankmaku.domain.entity.Response;
import me.pkliang.gankmaku.domain.interactor.GetIOSUseCase;

/**
 * Created by Administrator on 2015/8/11.
 */
public class IOSPresenter extends PaginationRxPresenter<GanhuoView, Response> {

    private final GetIOSUseCase getIOSUseCase;

    @Inject
    public IOSPresenter(GetIOSUseCase getIOSUseCase) {
        this.getIOSUseCase = getIOSUseCase;
    }

    @Override
    protected void load(int page, int limit, boolean pullToRefresh) {
        subscribe(getIOSUseCase.execute(limit, page), pullToRefresh);
    }

    @Override
    protected boolean hasMore(Response data) {
        return data.getResults().size() >= mLimit;
    }
}
