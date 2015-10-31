package me.pkliang.gankmaku.base;

import android.support.v7.widget.RecyclerView;
import me.pkliang.gankmaku.base.view.IDataAdapter;
import me.pkliang.gankmaku.domain.entity.Response;

/**
 * Created by Omistaja on 8/11/2015.
 */
public abstract class BaseAdapter<V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V>
    implements IDataAdapter<Response> {

  protected Response mResponse;

  protected BaseAdapter(Response mResponse) {
    this.mResponse = mResponse;
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
