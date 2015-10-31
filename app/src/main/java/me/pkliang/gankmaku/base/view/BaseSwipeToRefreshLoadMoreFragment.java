package me.pkliang.gankmaku.base.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingFragmentLceViewState;
import me.pkliang.gankmaku.R;
import me.pkliang.gankmaku.base.presenter.PaginationRxPresenter;
import me.pkliang.gankmaku.widget.EmptyRecyclerView;
import me.pkliang.gankmaku.widget.EndlessRecyclerOnScrollListener;

/**
 * Created by Omistaja on 7/16/2015.
 */
public abstract class BaseSwipeToRefreshLoadMoreFragment<CV extends SwipeRefreshLayout, M, V extends PaginationView<M>, P extends PaginationRxPresenter<V, M>>
    extends MvpLceViewStateFragment<CV, M, V, P>
    implements PaginationView<M>, SwipeRefreshLayout.OnRefreshListener {

  @Bind(R.id.recyclerView) public EmptyRecyclerView emptyRecyclerView;

  @Bind((R.id.emptyView)) public TextView emptyView;

  private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener() {
      @Override public void onLoadMore(int currentPage) {
        presenter.loadMore(true);
      }
    };
    emptyRecyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
    contentView.setOnRefreshListener(this);
  }

  @Override public LceViewState<M, V> createViewState() {
    return new RetainingFragmentLceViewState<>(this);
  }

  @Override protected String getErrorMessage(Throwable throwable, boolean pullToRefresh) {
    throwable.printStackTrace();
    return throwable.getMessage();
  }

  @Override public void showContent() {
    super.showContent();
    if (emptyRecyclerView.getEmptyView() == null) emptyRecyclerView.setEmptyView(emptyView);
    contentView.setRefreshing(false);
  }

  @Override public void showError(Throwable e, boolean pullToRefresh) {
    if (getData() == null || getIDataAdapter().isEmpty()) {
      super.showError(e, pullToRefresh);
    } else {
      super.showError(e, true);
      if (!pullToRefresh) showContent();
    }
    contentView.setRefreshing(false);
  }

  @Override public void showLoading(boolean pullToRefresh) {
    super.showLoading(pullToRefresh);
    if (pullToRefresh && !contentView.isRefreshing()) {
      // Workaround for measure bug: https://code.google.com/p/android/issues/detail?id=77712
      contentView.post(() -> contentView.setRefreshing(true));
    }
  }

  @Override public void onRefresh() {
    loadData(true);
  }

  @Override public M getData() {
    return getIDataAdapter() == null ? null : getIDataAdapter().getData();
  }

  @Override public void setData(M data) {
    getIDataAdapter().notifyDataChanged(data, true);
    endlessRecyclerOnScrollListener.reset();
  }

  @Override public void setMoreData(M data, boolean hasMore) {
    getIDataAdapter().notifyDataChanged(data, false);
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.refresh(getFirstPage(), getPageSize(), pullToRefresh);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    //TODO when current view state is not show content, change the view state to show content
    presenter.detachView(false);
    viewState.setStateShowContent(getData());
  }

  protected abstract IDataAdapter<M> getIDataAdapter();

  protected abstract int getFirstPage();

  protected abstract int getPageSize();
}
