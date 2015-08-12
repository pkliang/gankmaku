package me.pkliang.gankmaku.ganhuo;

import dagger.Component;
import me.pkliang.gankmaku.application.AppComponent;
import me.pkliang.gankmaku.base.PerFragment;

/**
 * Created by Administrator on 2015/8/11.
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = IOSModule.class)
public interface GetIOSComponent {
    void inject(IOSFragment fragment);
    IOSPresenter presenter();
}
