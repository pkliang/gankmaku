package me.pkliang.gankmaku.fuli;

import dagger.Module;
import dagger.Provides;
import me.pkliang.gankmaku.base.PerFragment;
import me.pkliang.gankmaku.domain.interactor.GetFuli;
import me.pkliang.gankmaku.domain.interactor.UseCase;

/**
 * Created by Omistaja on 8/10/2015.
 */
@Module public class FuliModule {

  @Provides @PerFragment UseCase provideGetFuli(GetFuli getFuli) {
    return getFuli;
  }
}
