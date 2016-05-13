package org.ppollack.crudandsearch.pathology.common.dao;

import org.ppollack.crudandsearch.dao.ICrudDao;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;

public interface IPersonDao<T extends IPerson, K> extends ICrudDao<T, K> {

}
