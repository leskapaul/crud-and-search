package org.ppollack.crudandsearch;

import java.util.List;

public interface ISearchDao<T> {

  public List<T> search(String query);
}
