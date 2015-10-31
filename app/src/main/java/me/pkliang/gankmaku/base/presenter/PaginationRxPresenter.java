package me.pkliang.gankmaku.base.presenter;

import com.hannesdorfmann.mosby.mvp.rx.lce.MvpLceRxPresenter;
import me.pkliang.gankmaku.base.view.PaginationView;

/**
 * Created by Omistaja on 7/6/2015.
 */
public abstract class PaginationRxPresenter<V extends PaginationView<M>, M>
    extends MvpLceRxPresenter<V, M> {

  protected int mPage;
  protected int mLimit;
  protected int mFirstPage;
  private boolean isLoadingMore;
  private boolean hasMoreData;

  public PaginationRxPresenter() {
  }

  @Override protected void onError(Throwable e, boolean pullToRefresh) {
    super.onError(e, pullToRefresh);
    isLoadingMore = false;
  }

  @Override protected void onCompleted() {
    super.onCompleted();
    mPage++;
    isLoadingMore = false;
  }

  @Override protected void onNext(M data) {
    hasMoreData = hasMore(data);
    if (mPage == mFirstPage) {
      super.onNext(data);
    } else {
      if (isViewAttached()) {
        getView().setMoreData(data, hasMoreData);
        isLoadingMore = false;
      }
    }
    //mPage++;
  }

  protected abstract void load(int page, int limit, boolean pullToRefresh);

  protected abstract boolean hasMore(M data);

  public void refresh(int firstPage, int limit, boolean pullToRefresh) {
    mFirstPage = firstPage;
    mLimit = limit;
    mPage = firstPage;
    load(mPage, mLimit, pullToRefresh);
  }

  public void loadMore(boolean pullToRefresh) {
    if (hasMoreData && !isLoadingMore) {
      load(mPage, mLimit, pullToRefresh);
      isLoadingMore = true;
    }
  }
}
