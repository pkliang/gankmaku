package me.pkliang.gankmaku.ganhuo;

import dagger.Module;
import dagger.Provides;
import me.pkliang.gankmaku.base.PerFragment;
import me.pkliang.gankmaku.domain.interactor.GetIOSUseCase;
import me.pkliang.gankmaku.domain.interactor.GetIOSUseCaseImpl;

/**
 * Created by Administrator on 2015/8/11.
 */
@Module
public class IOSModule {

    @Provides
    @PerFragment
    GetIOSUseCase provideGetAndroidUseCase(GetIOSUseCaseImpl getIOS) {
        return getIOS;
    }
}
