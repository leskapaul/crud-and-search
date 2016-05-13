package org.ppollack.crudandsearch.exceptions;

import java.util.HashMap;
import java.util.Map;

public class CrudException extends Exception {

  private Map<String, String> itemizedIssues;

  public CrudException(String message) {
    super(message);
  }

  public CrudException(String message, Throwable cause) {
    super(message, cause);
  }

  public CrudException(Throwable cause) {
    super(cause);
  }

  /**
   * Returns a map intended to be used to express itemized issues associated with this
   * exception.  An example might be:
   *
   * "someForeignKeyFieldName" -> "integrity constraint violation: cannot refer to an entity that
   *   does not exist"
   * "someOtherField" -> "value is invalid: must be less than 20 characters in length"
   */
  public Map<String, String> getItemizedIssues() {
    if (itemizedIssues == null) {
      itemizedIssues = new HashMap<>();
    }
    return itemizedIssues;
  }
}
