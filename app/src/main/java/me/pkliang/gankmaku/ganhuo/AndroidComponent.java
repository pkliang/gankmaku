package me.pkliang.gankmaku.ganhuo;

import dagger.Component;
import me.pkliang.gankmaku.application.AppComponent;
import me.pkliang.gankmaku.base.PerFragment;

/**
 * Created by Omistaja on 8/11/2015.
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = AndroidModule.class)
public interface AndroidComponent {
    void inject(AndroidFragment androidFragment);
    AndroidPresenter presenter();
}
