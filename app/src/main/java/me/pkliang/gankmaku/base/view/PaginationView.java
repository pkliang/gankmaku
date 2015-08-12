package me.pkliang.gankmaku.base.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * Created by Omistaja on 7/6/2015.
 */
public interface PaginationView<M> extends MvpLceView<M> {
    void setMoreData(M data, boolean hasMore);
}
