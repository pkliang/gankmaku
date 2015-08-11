package me.pkliang.gankmaku.android;

import dagger.Module;
import dagger.Provides;
import me.pkliang.gankmaku.base.PerFragment;
import me.pkliang.gankmaku.domain.interactor.GetIOS;
import me.pkliang.gankmaku.domain.interactor.GetIOSImpl;

/**
 * Created by Administrator on 2015/8/11.
 */
@Module
public class IOSModule {

    @Provides
    @PerFragment
    GetIOS provideGetAndroidUseCase(GetIOSImpl getIOS) {
        return getIOS;
    }
}
