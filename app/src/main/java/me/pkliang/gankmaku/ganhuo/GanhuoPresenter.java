package me.pkliang.gankmaku.ganhuo;

import javax.inject.Inject;
import me.pkliang.gankmaku.base.presenter.PaginationRxPresenter;
import me.pkliang.gankmaku.domain.entity.Response;
import me.pkliang.gankmaku.domain.interactor.UseCase;

/**
 * Created by liangsong on 31/10/15.
 */
public class GanhuoPresenter extends PaginationRxPresenter<GanhuoView, Response> {

  private final UseCase useCase;

  @Inject public GanhuoPresenter(UseCase useCase) {
    this.useCase = useCase;
  }

  @Override protected void load(int page, int limit, boolean pullToRefresh) {
    subscribe(useCase.execute(limit, page), pullToRefresh);
  }

  @Override protected boolean hasMore(Response data) {
    return data != null && data.getResults().size() >= mLimit;
  }
}
