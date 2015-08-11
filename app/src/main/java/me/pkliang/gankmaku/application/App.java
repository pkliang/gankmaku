package me.pkliang.gankmaku.application;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

/**
 * Created by Omistaja on 8/10/2015.
 */
public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);

        Hawk.init(this)
            .setStorage(HawkBuilder.newSqliteStorage(this))
            .setLogLevel(LogLevel.FULL)
            .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
