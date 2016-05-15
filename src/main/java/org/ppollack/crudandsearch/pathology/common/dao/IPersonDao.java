package org.ppollack.crudandsearch.pathology.common.dao;

import org.ppollack.crudandsearch.dao.ICrudDao;
import org.ppollack.crudandsearch.pathology.common.model.IPerson;

import java.io.Serializable;

public interface IPersonDao<T extends IPerson, ID extends Serializable> extends ICrudDao<T, ID> {

}
