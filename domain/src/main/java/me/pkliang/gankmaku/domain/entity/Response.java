package me.pkliang.gankmaku.domain.entity;

import java.util.List;

/**
 * Created by Omistaja on 8/10/2015.
 */
public class Response {

  private List<Entry> results;
  private boolean error;

  public Response setResults(List<Entry> results) {
    this.results = results;
    return this;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public List<Entry> getResults() {
    return results;
  }

  public boolean isError() {
    return error;
  }
}
