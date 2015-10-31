package me.pkliang.gankmaku.ganhuo;

import dagger.Module;
import dagger.Provides;
import me.pkliang.gankmaku.base.PerFragment;
import me.pkliang.gankmaku.domain.interactor.GetIOS;
import me.pkliang.gankmaku.domain.interactor.UseCase;

/**
 * Created by Administrator on 2015/8/11.
 */
@Module public class IOSModule {

  @Provides @PerFragment UseCase provideGetAndroidUseCase(GetIOS getIOS) {
    return getIOS;
  }
}
