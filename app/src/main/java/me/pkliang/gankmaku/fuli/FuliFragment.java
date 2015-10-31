package me.pkliang.gankmaku.fuli;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Bind;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import hugo.weaving.DebugLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuSurfaceView;
import me.pkliang.gankmaku.R;
import me.pkliang.gankmaku.application.App;
import me.pkliang.gankmaku.base.view.BaseDanmakuFragment;
import me.pkliang.gankmaku.base.view.IDataAdapter;
import me.pkliang.gankmaku.domain.entity.Entry;
import me.pkliang.gankmaku.domain.entity.Response;
import me.pkliang.gankmaku.widget.RecyclerViewPreloader;

/**
 * Created by Omistaja on 8/10/2015.
 */
public class FuliFragment
    extends BaseDanmakuFragment<SwipeRefreshLayout, Response, FuliView, FuliPresenter>
    implements FuliAdapter.PaletteCallback, RecyclerViewPager.OnPageChangedListener {

  private FuliComponent component;
  private FuliAdapter adapter;
  private Map<Integer, Palette> paletteMap;
  private SystemBarTintManager tintManager;
  private BaseDanmakuParser mParser;

  @Bind(R.id.rootView) FrameLayout rootView;
  @Bind(R.id.sv_danmaku) DanmakuSurfaceView mDanmakuView;

  private static final int PRELOAD_AHEAD_ITEMS = 5;

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    tintManager = new SystemBarTintManager(getActivity());
    tintManager.setStatusBarTintEnabled(true);
    tintManager.setNavigationBarTintEnabled(true);

    Response response = new Response().setResults(new ArrayList<>());
    ViewPreloadSizeProvider<Entry> preloadSizeProvider = new ViewPreloadSizeProvider<>();
    adapter = new FuliAdapter(response, getActivity(), preloadSizeProvider);
    adapter.setPaletteCallback(this);
    emptyRecyclerView.setAdapter(adapter);
    emptyRecyclerView.setLayoutManager(
        new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
    emptyView.setText(getText(R.string.no_data));

    emptyRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
          @Override public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
            // do something
          }

          @Override public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            int childCount = emptyRecyclerView.getChildCount();
            int width = emptyRecyclerView.getChildAt(0).getWidth();
            int padding = (emptyRecyclerView.getWidth() - width) / 2;
            //mCountText.setText("Count: " + childCount);

            for (int j = 0; j < childCount; j++) {
              View v = recyclerView.getChildAt(j);
              float rate = 0;
              if (v.getLeft() <= padding) {
                if (v.getLeft() >= padding - v.getWidth()) {
                  rate = (padding - v.getLeft()) * 1f / v.getWidth();
                } else {
                  rate = 1;
                }
                v.setScaleY(1 - rate * 0.1f);
              } else {
                if (v.getLeft() <= recyclerView.getWidth() - padding) {
                  rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                }
                v.setScaleY(0.9f + rate * 0.1f);
              }
            }
          }
        });
    // registering addOnLayoutChangeListener  aim to setScale at first layout action
    emptyRecyclerView.addOnLayoutChangeListener(
        (v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
          if (emptyRecyclerView.getChildCount() < 3) {
            if (emptyRecyclerView.getChildAt(1) != null) {
              View v1 = emptyRecyclerView.getChildAt(1);
              v1.setScaleY(0.9f);
            }
          } else {
            if (emptyRecyclerView.getChildAt(0) != null) {
              View v0 = emptyRecyclerView.getChildAt(0);
              v0.setScaleY(0.9f);
            }
            if (emptyRecyclerView.getChildAt(2) != null) {
              View v2 = emptyRecyclerView.getChildAt(2);
              v2.setScaleY(0.9f);
            }
          }
        });

    RecyclerViewPreloader<Entry> preloader =
        new RecyclerViewPreloader<>(Glide.with(getActivity()), adapter, preloadSizeProvider,
            PRELOAD_AHEAD_ITEMS);
    emptyRecyclerView.addOnScrollListener(preloader);
    emptyRecyclerView.addOnPageChangedListener(this);

    paletteMap = new HashMap<>();

    mParser = createParser(null);
    initDanmaku(mDanmakuView, mParser);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    destroyDanmaku(mDanmakuView);
  }

  @Override protected IDataAdapter<Response> getIDataAdapter() {
    return adapter;
  }

  @Override protected int getFirstPage() {
    return 1;
  }

  @Override protected int getPageSize() {
    return 20;
  }

  @Override public FuliPresenter createPresenter() {
    return component.presenter();
  }

  @Override protected void injectDependencies() {
    component = DaggerFuliComponent.builder()
        .appComponent(App.getAppComponent())
        .fuliModule(new FuliModule())
        .build();
    component.inject(this);
  }

  @Override protected int getLayoutRes() {
    return R.layout.fragment_main;
  }

  @DebugLog @Override public void onPaletteReady(int i, Palette palette) {
    paletteMap.put(i, palette);
  }

  @Override public void OnPageChanged(int oldPosition, int newPosition) {
    //Log.d("FuliFragment", "addOnPageChangedListener " + newPosition);
    Palette palette = paletteMap.get(newPosition);
    if (palette != null) {
      int color = palette.getVibrantColor(0x000000);
      rootView.setBackgroundColor(color);
      tintManager.setStatusBarTintColor(color);
      tintManager.setNavigationBarTintColor(color);

      mDanmakuView.clearDanmakusOnScreen();
      Entry entry = getIDataAdapter().getData().getResults().get(newPosition);
      testDanMu(mDanmakuView, mParser, "by " + entry.getWho(), palette.getVibrantColor(0x000000),
          Color.TRANSPARENT);
    }
  }
}
