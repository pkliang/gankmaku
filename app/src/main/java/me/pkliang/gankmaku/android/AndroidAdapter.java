package me.pkliang.gankmaku.android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.pkliang.gankmaku.R;
import me.pkliang.gankmaku.base.BaseAdapter;
import me.pkliang.gankmaku.domain.entity.Response;

/**
 * Created by Omistaja on 8/11/2015.
 */
public class AndroidAdapter extends BaseAdapter<AndroidAdapter.ViewHolder> {

    public AndroidAdapter(Response mResponse) {
        super(mResponse);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.item_gank, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.webView.loadUrl(mResponse.getResults().get(position).getUrl());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.webView)
        WebView webView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
