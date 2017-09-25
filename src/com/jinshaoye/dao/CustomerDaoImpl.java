package com.jinshaoye.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.jinshaoye.entity.Customer;
import com.jinshaoye.entity.Dict;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	//查询记录数
	@SuppressWarnings("all")
	public int findCount() {
		//调用hibernateTemplate里面的find方法实现
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		//从list中把值得到
		if(list != null && list.size()!=0) {
			Object obj = list.get(0);
			//变成int类型
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	//分页查询操作
	@SuppressWarnings("all")
	public List<Customer> findPage(int begin, int pageSize) {
		//创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//调用hibernateTemplate的方法实现
		//第一个参数是离线对象
		//第二个参数是开始位置
		//第三个参数是每页记录数
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	//条件查询
	@SuppressWarnings("all")
	public List<Customer> findCondition(Customer customer) {
		//创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//设置对实体类哪个属性
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		//调用hibernateTemplate里面的方法得到list集合
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	//多条件组合查询
	//使用离线对象方式实现多条件组合查询
	@SuppressWarnings("all")
	public List<Customer> findMoreCondition(Customer customer) {
		
		//创建离线对象，指定对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//判断条件值是否为空
		if(customer.getCustName()!=null && !"".equals(customer.getCustName())) {
			//设置对属性，设置值
			criteria.add(Restrictions.eq("custName", customer.getCustName()));
		}
		if(customer.getCustSource()!=null && !"".equals(customer.getCustSource())) {
			criteria.add(Restrictions.eq("custSource", customer.getCustSource()));
		}
		
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	//查询所有级别
	@SuppressWarnings("all")
	public List<Dict> findAllDictLevel() {
		
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}

	//客户来源统计
	public List findCountSource() {
		//得到session对象
		Session session = this.getSessionFactory().getCurrentSession();
		//创建SQLQuery对象
		SQLQuery sqlQuery = session.createSQLQuery("SELECT COUNT(*) AS num,custSource FROM t_customer GROUP BY custSource");
		/*
		 * 因为返回值有两个字段，一个字段是id，一个是名称，
		 * 所以把返回数据转换map结构
		 * */
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		//调用方法得到结果
		//返回list，默认每部分是数组形式
		List list = sqlQuery.list();
		
		return list;
	}

	//根据客户级别统计
	public List findCountLevel() {
		//获取session对象
		Session session = this.getSessionFactory().getCurrentSession();
		//创建SQLQuery对象
		SQLQuery sqlQuery = session.createSQLQuery("SELECT c.num,d.dname FROM (SELECT COUNT(*) AS num,custLevel FROM t_customer GROUP BY custLevel) c , t_dict d WHERE c.custLevel=d.did");
		//得到结果
		//转换map结构
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = sqlQuery.list();
		return list;
	}
}









