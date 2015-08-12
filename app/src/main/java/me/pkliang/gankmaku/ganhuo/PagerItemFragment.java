package me.pkliang.gankmaku.ganhuo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import me.pkliang.gankmaku.R;

/**
 * Created by Omistaja on 8/11/2015.
 */
public class PagerItemFragment extends Fragment {

    private String url;

    public PagerItemFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_ganhuo, container, false);
        WebView webView = (WebView) view.findViewById(R.id.webView);
        if (savedInstanceState == null) {
            url = getArguments().getString("url");
        } else {
            url = savedInstanceState.getString("url");
        }
        webView.loadUrl(url);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("url", url);
    }
}
