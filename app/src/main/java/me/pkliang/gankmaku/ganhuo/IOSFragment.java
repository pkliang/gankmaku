package me.pkliang.gankmaku.ganhuo;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import me.pkliang.gankmaku.application.App;

/**
 * Created by Administrator on 2015/8/11.
 */
public class IOSFragment extends BaseGanhuoFragment<IOSPresenter>
        implements RecyclerViewPager.OnPageChangedListener {

    private GetIOSComponent component;

    @Override
    protected void injectDependencies() {
        component = DaggerGetIOSComponent.builder()
                .appComponent(App.getAppComponent())
                .iOSModule(new IOSModule())
                .build();
        component.inject(this);
    }

    @Override
    public IOSPresenter createPresenter() {
        return component.presenter();
    }
}
