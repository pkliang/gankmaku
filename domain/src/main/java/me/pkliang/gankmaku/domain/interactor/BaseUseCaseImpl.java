package me.pkliang.gankmaku.domain.interactor;

import javax.inject.Inject;

import me.pkliang.gankmaku.domain.Repository;

/**
 * Created by Omistaja on 8/10/2015.
 */
public class BaseUseCaseImpl {

    protected final Repository repository;

    @Inject
    public BaseUseCaseImpl(Repository repository) {
        this.repository = repository;
    }
}
