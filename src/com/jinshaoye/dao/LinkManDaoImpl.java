package com.jinshaoye.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.jinshaoye.entity.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	//添加联系人的方法
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

	//联系人列表的方法
	@SuppressWarnings("unchecked")
	public List<LinkMan> list() {
		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
	}

	//根据id查询联系人
	public LinkMan findOne(int linkid) {
		return this.getHibernateTemplate().get(LinkMan.class, linkid);
	}

	//修改联系人的方法
	public void update(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}

	//多条件组合查询-离线对象方式实现
	@SuppressWarnings("all")
	public List<LinkMan> findCondition(LinkMan linkMan) {
		//创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		if(linkMan.getLkmName()!=null && !"".equals(linkMan.getLkmName())) {
			criteria.add(Restrictions.eq("lkmName", linkMan.getLkmName()));
		}
		if(linkMan.getCustomer().getCid()!=null && linkMan.getCustomer().getCid()>0) {
			criteria.add(Restrictions.eq("customer.cid", linkMan.getCustomer().getCid()));
		}
		
		return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
	}
}




