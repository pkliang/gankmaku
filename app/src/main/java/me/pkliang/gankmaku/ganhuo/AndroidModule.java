package me.pkliang.gankmaku.ganhuo;

import dagger.Module;
import dagger.Provides;
import me.pkliang.gankmaku.base.PerFragment;
import me.pkliang.gankmaku.domain.interactor.GetAndroid;
import me.pkliang.gankmaku.domain.interactor.UseCase;

/**
 * Created by Omistaja on 8/11/2015.
 */
@Module public class AndroidModule {
  @Provides @PerFragment UseCase provideGetAndroidUseCase(GetAndroid getAndroidUseCase) {
    return getAndroidUseCase;
  }
}

