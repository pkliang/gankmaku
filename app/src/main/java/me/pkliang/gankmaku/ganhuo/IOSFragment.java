package me.pkliang.gankmaku.ganhuo;

import me.pkliang.gankmaku.application.App;

/**
 * Created by Administrator on 2015/8/11.
 */
public class IOSFragment extends BaseGanhuoFragment {

  private GetIOSComponent component;

  @Override protected void injectDependencies() {
    component = DaggerGetIOSComponent.builder()
        .appComponent(App.getAppComponent())
        .iOSModule(new IOSModule())
        .build();
    component.inject(this);
  }

  @Override public GanhuoPresenter createPresenter() {
    return component.presenter();
  }
}
