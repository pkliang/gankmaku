package me.pkliang.gankmaku.widget;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Omistaja on 5/4/2015.
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
  public static String TAG = "EndlessScrollListener";

  private int previousTotal = 0; // The total number of items in the dataset after the last load
  private boolean loading = true; // True if we are still waiting for the last set of data to load.
  private int visibleThreshold = 5;
      // The minimum amount of items to have below your current scroll position before loading more.
  int firstVisibleItem, visibleItemCount, totalItemCount;

  private int currentPage = 0;

  RecyclerViewPositionHelper mRecyclerViewHelper;

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
    visibleItemCount = recyclerView.getChildCount();
    totalItemCount = mRecyclerViewHelper.getItemCount();
    firstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition();
    //Timber.d("loading " + loading);
    if (loading) {
      if (totalItemCount > previousTotal) {
        loading = false;
        previousTotal = totalItemCount;
      }
    }
    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
      // End has been reached
      // Do something
      currentPage++;

      onLoadMore(currentPage);

      loading = true;
    }
  }

  public int getVisibleThreshold() {
    return visibleThreshold;
  }

  public void setVisibleThreshold(int visibleThreshold) {
    this.visibleThreshold = visibleThreshold;
  }

  public void reset() {
    currentPage = 0;
    previousTotal = 0;
    loading = true;
  }

  //Start loading
  public abstract void onLoadMore(int currentPage);
}
