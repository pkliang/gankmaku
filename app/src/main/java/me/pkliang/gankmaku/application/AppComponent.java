package me.pkliang.gankmaku.application;

import javax.inject.Singleton;

import dagger.Component;
import me.pkliang.gankmaku.domain.Repository;

/**
 * Created by Omistaja on 8/10/2015.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = AppModule.class)
public interface AppComponent {

    // Field injections of any dependencies of the DemoApplication
    void inject(App application);

    Repository repository();
}
