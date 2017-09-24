package com.jinshaoye.dao;

import java.util.List;

import com.jinshaoye.entity.LinkMan;

public interface LinkManDao {

	void add(LinkMan linkMan);

	List<LinkMan> list();

	LinkMan findOne(int linkid);

	void update(LinkMan linkMan);

	List<LinkMan> findCondition(LinkMan linkMan);

}
