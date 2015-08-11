package me.pkliang.gankmaku.fuli;

import dagger.Module;
import dagger.Provides;
import me.pkliang.gankmaku.base.PerFragment;
import me.pkliang.gankmaku.domain.interactor.GetFuliUseCase;
import me.pkliang.gankmaku.domain.interactor.GetFuliUseCaseImpl;

/**
 * Created by Omistaja on 8/10/2015.
 */
@Module
public class FuliModule {

    @Provides
    @PerFragment
    GetFuliUseCase provideGetFuliUseCase(GetFuliUseCaseImpl getFuliUseCase) {
        return getFuliUseCase;
    }
}
