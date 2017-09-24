package com.jinshaoye.dao;

import java.util.List;

import com.jinshaoye.entity.Visit;

public interface VisitDao {

	void add(Visit visit);

	List<Visit> findAll();

}
