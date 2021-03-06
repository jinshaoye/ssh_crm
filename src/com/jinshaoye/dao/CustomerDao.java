package com.jinshaoye.dao;

import java.util.List;

import com.jinshaoye.entity.Customer;
import com.jinshaoye.entity.Dict;

public interface CustomerDao extends BaseDao<Customer>{

	int findCount();

	List<Customer> findPage(int begin, int pageSize);

	List<Customer> findCondition(Customer customer);

	List<Customer> findMoreCondition(Customer customer);

	List<Dict> findAllDictLevel();

	List findCountSource();

	List findCountLevel();

}
