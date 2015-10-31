package me.pkliang.gankmaku.ganhuo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter;
import me.pkliang.gankmaku.base.view.IDataAdapter;
import me.pkliang.gankmaku.domain.entity.Response;

/**
 * Created by Omistaja on 8/11/2015.
 */
public class FragmentsAdapter extends FragmentStatePagerAdapter implements IDataAdapter<Response> {

  private final Response mResponse;

  public FragmentsAdapter(FragmentManager fm, Response response) {
    super(fm);
    mResponse = response;
  }

  @Override public Fragment getItem(int i, Fragment.SavedState savedState) {
    Fragment f = new PagerItemFragment();
    if (savedState == null) {
      Bundle bundle = new Bundle();
      bundle.putString("url", mResponse.getResults().get(i).getUrl());
      f.setArguments(bundle);
    }
    f.setInitialSavedState(savedState);
    return f;
  }

  @Override public void onDestroyItem(int i, Fragment fragment) {

  }

  @Override public int getItemCount() {
    return mResponse.getResults().size();
  }

  @Override public void notifyDataChanged(Response response, boolean isRefresh) {
    if (isRefresh) mResponse.getResults().clear();
    mResponse.getResults().addAll(response.getResults());
    mResponse.setError(response.isError());
    notifyDataSetChanged();
  }

  @Override public Response getData() {
    return mResponse;
  }

  @Override public boolean isEmpty() {
    return mResponse.getResults().isEmpty();
  }
}
