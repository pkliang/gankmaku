package me.pkliang.gankmaku.ganhuo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.pkliang.gankmaku.R;
import me.pkliang.gankmaku.base.BaseAdapter;
import me.pkliang.gankmaku.domain.entity.Response;

/**
 * Created by Omistaja on 8/11/2015.
 */
public class GanhuoAdapter extends BaseAdapter<GanhuoAdapter.ViewHolder> {

  private final WebChromeClient chromeClient;

  public GanhuoAdapter(Response mResponse, WebChromeClient chromeClient) {
    super(mResponse);
    this.chromeClient = chromeClient;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ganhuo, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.webView.loadUrl(mResponse.getResults().get(position).getUrl());
    holder.webView.setWebChromeClient(chromeClient);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.webView) WebView webView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
