package com.jinshaoye.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jinshaoye.dao.LinkManDao;
import com.jinshaoye.entity.LinkMan;

@Transactional
public class LinkManService {

	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	public void addLinkMan(LinkMan linkMan) {
		linkManDao.add(linkMan);
	}

	public List<LinkMan> listLinkMan() {
		return linkManDao.list();
	}

	public LinkMan findOne(int linkid) {
		return linkManDao.findOne(linkid);
	}

	public void updateLink(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	public List<LinkMan> findCondition(LinkMan linkMan) {
		return linkManDao.findCondition(linkMan);
	}
	
}
