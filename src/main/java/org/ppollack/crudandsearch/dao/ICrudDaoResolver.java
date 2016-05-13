package org.ppollack.crudandsearch.dao;

public interface ICrudDaoResolver {

  ICrudDao resolveDao(String name);
}
