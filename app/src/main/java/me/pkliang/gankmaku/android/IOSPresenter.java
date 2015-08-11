package me.pkliang.gankmaku.android;

import javax.inject.Inject;

import me.pkliang.gankmaku.base.presenter.PaginationRxPresenter;
import me.pkliang.gankmaku.domain.entity.Response;
import me.pkliang.gankmaku.domain.interactor.GetIOS;

/**
 * Created by Administrator on 2015/8/11.
 */
public class IOSPresenter extends PaginationRxPresenter<AndroidView, Response> {

    private final GetIOS getIOS;

    @Inject
    public IOSPresenter(GetIOS getIOS) {
        this.getIOS = getIOS;
    }

    @Override
    protected void load(int page, int limit, boolean pullToRefresh) {
        subscribe(getIOS.execute(limit, page), pullToRefresh);
    }

    @Override
    protected boolean hasMore(Response data) {
        return data.getResults().size() >= mLimit;
    }
}
