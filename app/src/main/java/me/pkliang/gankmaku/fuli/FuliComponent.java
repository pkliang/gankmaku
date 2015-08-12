package me.pkliang.gankmaku.fuli;

import dagger.Component;
import me.pkliang.gankmaku.application.AppComponent;
import me.pkliang.gankmaku.base.PerFragment;

/**
 * Created by Omistaja on 8/10/2015.
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = FuliModule.class)
public interface FuliComponent {
    void inject(FuliFragment fragment);
    FuliPresenter presenter();
}
