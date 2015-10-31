package me.pkliang.gankmaku.domain.interactor;

import me.pkliang.gankmaku.domain.Repository;
import me.pkliang.gankmaku.domain.entity.Response;
import rx.Observable;

/**
 * Created by liangsong on 31/10/15.
 */
public abstract class UseCase {

  protected final Repository repository;

  protected UseCase(Repository repository) {
    this.repository = repository;
  }

  public abstract Observable<Response> execute(int pageSize, int page);
}
