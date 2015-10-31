package me.pkliang.gankmaku.domain.interactor;

import javax.inject.Inject;
import me.pkliang.gankmaku.domain.Repository;
import me.pkliang.gankmaku.domain.entity.Response;
import rx.Observable;

/**
 * Created by liangsong on 31/10/15.
 */
public class GetFuli extends UseCase {
  @Inject public GetFuli(Repository repository) {
    super(repository);
  }

  @Override public Observable<Response> execute(int pageSize, int page) {
    return repository.getFuli(pageSize, page);
  }
}
