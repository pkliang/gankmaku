package me.pkliang.gankmaku.base.view;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.support.v4.widget.SwipeRefreshLayout;
import java.io.InputStream;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.DanmakuFactory;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import master.flame.danmaku.ui.widget.DanmakuSurfaceView;
import me.pkliang.gankmaku.base.presenter.PaginationRxPresenter;

/**
 * Created by Omistaja on 8/11/2015.
 */
public abstract class BaseDanmakuFragment<CV extends SwipeRefreshLayout, M, V extends PaginationView<M>, P extends PaginationRxPresenter<V, M>>
    extends BaseSwipeToRefreshLoadMoreFragment<CV, M, V, P> {

  protected void initDanmaku(DanmakuSurfaceView danmakuSurfaceView, BaseDanmakuParser parser) {
    if (danmakuSurfaceView != null) {
      //parser = createParser(null);
      danmakuSurfaceView.setCallback(new DrawHandler.Callback() {
            @Override public void updateTimer(DanmakuTimer timer) {
            }

            @Override public void prepared() {
              danmakuSurfaceView.start();
            }
          });
      danmakuSurfaceView.prepare(parser);
      //danmakuSurfaceView.showFPS(true);
      danmakuSurfaceView.enableDanmakuDrawingCache(true);
      danmakuSurfaceView.setZOrderOnTop(true);
      danmakuSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }
  }

  protected BaseDanmakuParser createParser(InputStream stream) {

    if (stream == null) {
      return new BaseDanmakuParser() {

        @Override protected Danmakus parse() {
          return new Danmakus();
        }
      };
    }

    ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

    try {
      loader.load(stream);
    } catch (IllegalDataException e) {
      e.printStackTrace();
    }
    BaseDanmakuParser parser = new BiliDanmukuParser();
    IDataSource<?> dataSource = loader.getDataSource();
    parser.load(dataSource);
    return parser;
  }

  protected void testDanMu(DanmakuSurfaceView danmakuSurfaceView, BaseDanmakuParser mParser,
      String string, int textColor, int borderColor) {
    BaseDanmaku danmaku = DanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);

    danmaku.text = string;
    danmaku.padding = 5;
    danmaku.priority = 1;
    danmaku.time = mParser.getTimer().currMillisecond + 100;
    danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
    //danmaku.textSize = 25f;
    danmaku.textColor = textColor;
    danmaku.textShadowColor = Color.WHITE;
    //danmaku.underlineColor = Color.GREEN;
    danmaku.borderColor = borderColor;

    danmakuSurfaceView.addDanmaku(danmaku);
  }

  protected void destroyDanmaku(DanmakuSurfaceView danmakuSurfaceView) {
    if (danmakuSurfaceView != null) {
      danmakuSurfaceView.release();
      danmakuSurfaceView = null;
    }
  }
}
