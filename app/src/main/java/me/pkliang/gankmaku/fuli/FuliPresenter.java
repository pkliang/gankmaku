package me.pkliang.gankmaku.fuli;

import javax.inject.Inject;
import me.pkliang.gankmaku.base.presenter.PaginationRxPresenter;
import me.pkliang.gankmaku.domain.entity.Response;
import me.pkliang.gankmaku.domain.interactor.UseCase;

/**
 * Created by Omistaja on 8/10/2015.
 */
public class FuliPresenter extends PaginationRxPresenter<FuliView, Response> {

  private final UseCase getFuli;

  @Inject public FuliPresenter(UseCase getFuliUseCase) {
    this.getFuli = getFuliUseCase;
  }

  @Override protected boolean hasMore(Response data) {
    return data != null && data.getResults().size() >= mLimit;
  }

  @Override protected void load(int page, int limit, boolean pullToRefresh) {
    subscribe(getFuli.execute(limit, page), pullToRefresh);
  }
}
