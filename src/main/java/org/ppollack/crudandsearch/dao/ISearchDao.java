package org.ppollack.crudandsearch.dao;

import org.springframework.data.domain.Page;

public interface ISearchDao<T> {

  public Page<? extends T> search(String query);
}
