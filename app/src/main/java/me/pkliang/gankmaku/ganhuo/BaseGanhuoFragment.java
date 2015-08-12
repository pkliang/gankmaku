package me.pkliang.gankmaku.ganhuo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import hugo.weaving.DebugLog;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuSurfaceView;
import me.pkliang.gankmaku.R;
import me.pkliang.gankmaku.base.presenter.PaginationRxPresenter;
import me.pkliang.gankmaku.base.view.BaseDanmakuFragment;
import me.pkliang.gankmaku.base.view.IDataAdapter;
import me.pkliang.gankmaku.domain.entity.Entry;
import me.pkliang.gankmaku.domain.entity.Response;

/**
 * Created by Omistaja on 8/12/2015.
 */
public abstract class BaseGanhuoFragment<P extends PaginationRxPresenter<GanhuoView, Response>> extends BaseDanmakuFragment<SwipeRefreshLayout, Response, GanhuoView, P>
        implements RecyclerViewPager.OnPageChangedListener {

    @Bind(R.id.sv_danmaku)
    DanmakuSurfaceView mDanmakuView;

    protected GanhuoAdapter adapter;

    private BaseDanmakuParser mParser;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Response response = new Response();
        response.setResults(new ArrayList<>());
        //adapter = new FragmentsAdapter(getChildFragmentManager(),response);
        WebChromeClient chromeClient = new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                getActivity().setProgress(progress * 1000);
            }
        };
        adapter = new GanhuoAdapter(response, chromeClient);
        emptyRecyclerView.setAdapter(adapter);
        emptyRecyclerView.setPadding(0, 0, 0, 0);
        //emptyRecyclerView.setFlingFactor(0);
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        emptyView.setText(getText(R.string.no_data));

        emptyRecyclerView.addOnPageChangedListener(this);
        mParser = createParser(null);
        initDanmaku(mDanmakuView, mParser);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyDanmaku(mDanmakuView);
    }

    @Override
    protected IDataAdapter<Response> getIDataAdapter() {
        return adapter;
    }

    @Override
    protected int getFirstPage() {
        return 1;
    }

    @Override
    protected int getPageSize() {
        return 10;
    }

    @DebugLog
    @Override
    public void OnPageChanged(int oldPos, int newPos) {
        mDanmakuView.clearDanmakusOnScreen();
        Entry entry = getIDataAdapter().getData().getResults().get(newPos);
        testDanMu(mDanmakuView, mParser, entry.getDesc(), Color.BLACK, Color.TRANSPARENT);
        testDanMu(mDanmakuView, mParser, "by " + entry.getWho(), Color.BLACK, Color.TRANSPARENT);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }
}
