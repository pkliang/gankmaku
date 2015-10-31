package me.pkliang.gankmaku.base.view;

/**
 * Created by Omistaja on 8/10/2015.
 */
public interface IDataAdapter<DATA> {

  void notifyDataChanged(DATA data, boolean isRefresh);

  DATA getData();

  boolean isEmpty();
}
