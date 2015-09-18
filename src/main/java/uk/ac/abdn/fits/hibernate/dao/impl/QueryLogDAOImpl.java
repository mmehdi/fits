package uk.ac.abdn.fits.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.QueryLogDAO;
import uk.ac.abdn.fits.hibernate.model.QueryLog;

@Service
public class QueryLogDAOImpl implements QueryLogDAO {

	
  @Autowired
  private SessionFactory sessionFactory;
  
  
  @Override
  public void insertQueryLog(QueryLog query_log) {
    sessionFactory.getCurrentSession().save(query_log);
    
  }
  
  @Override
  public QueryLog getQueryLogById(int log_id) {
    return (QueryLog) sessionFactory.
      getCurrentSession().
      get(QueryLog.class, log_id);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<QueryLog> getQueryLog() {
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QueryLog.class);
    return criteria.list();
  }

}