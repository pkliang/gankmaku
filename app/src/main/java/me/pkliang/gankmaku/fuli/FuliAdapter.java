package me.pkliang.gankmaku.fuli;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.github.florent37.glidepalette.GlidePalette;
import java.util.List;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import me.pkliang.gankmaku.R;
import me.pkliang.gankmaku.base.BaseAdapter;
import me.pkliang.gankmaku.domain.entity.Entry;
import me.pkliang.gankmaku.domain.entity.Response;

/**
 * Created by Omistaja on 8/11/2015.
 */
public class FuliAdapter extends BaseAdapter<FuliAdapter.ViewHolder>
    implements ListPreloader.PreloadModelProvider<Entry> {
  private final Context mContext;
  private final BitmapPool mPool;
  private PaletteCallback mPaletteCallback;
  private final ViewPreloadSizeProvider<Entry> mPreloadSizeProvider;

  protected FuliAdapter(Response mResponse, Context context,
      ViewPreloadSizeProvider<Entry> preloadSizeProvider) {
    super(mResponse);
    mContext = context;
    mPreloadSizeProvider = preloadSizeProvider;
    mPool = Glide.get(context).getBitmapPool();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fuli, parent, false);
    ViewHolder vh = new ViewHolder(v);
    mPreloadSizeProvider.setView(vh.meiZi);
    return vh;
  }

  @Override public void onBindViewHolder(ViewHolder vh, int i) {
    Glide.with(mContext).load(mResponse.getResults().get(i).getUrl()).bitmapTransform(
        new CenterCrop(mPool), new RoundedCornersTransformation(mPool, 8, 0))
        //.crossFade()
        .listener(
            GlidePalette.with(mResponse.getResults().get(i).getUrl()).intoCallBack(palette -> {
                  if (mPaletteCallback != null) mPaletteCallback.onPaletteReady(i, palette);
                })).diskCacheStrategy(DiskCacheStrategy.ALL).into(vh.meiZi);
  }

  public void setPaletteCallback(PaletteCallback paletteCallback) {
    mPaletteCallback = paletteCallback;
  }

  @Override public List<Entry> getPreloadItems(int position) {
    return getData().getResults().subList(position, position + 1);
  }

  @Override public GenericRequestBuilder getPreloadRequestBuilder(Entry item) {
    return Glide.with(mContext).load(item.getUrl()).bitmapTransform(new CenterCrop(mPool),
        new RoundedCornersTransformation(mPool, 8, 0))
        //.crossFade()
        .diskCacheStrategy(DiskCacheStrategy.ALL);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.meiZi) ImageView meiZi;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface PaletteCallback {
    void onPaletteReady(int position, Palette palette);
  }
}
