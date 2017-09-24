package com.jinshaoye.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jinshaoye.dao.VisitDao;
import com.jinshaoye.entity.Visit;

@Transactional
public class VisitService {

	private VisitDao visitDao;

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public void addVisit(Visit visit) {
		visitDao.add(visit);
	}

	public List<Visit> findAll() {
		return visitDao.findAll();
	}
	
}
