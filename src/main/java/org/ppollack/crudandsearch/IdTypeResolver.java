package org.ppollack.crudandsearch;

public class IdTypeResolver {

  public static <T> T typifyId(Class<T> idType, Object id) {
    return (T) idType.cast(id);
  }
}
