package uk.ac.abdn.fits.hibernate.dao.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.QueryLogDAO;
import uk.ac.abdn.fits.hibernate.model.QueryLog;
import uk.ac.abdn.fits.hibernate.model.QueryLogGroupedDTO;
import uk.ac.abdn.fits.hibernate.model.User;

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

  @SuppressWarnings("unchecked")
@Override
  public List<QueryLog> getQueryLogByDateRange(Timestamp start, Timestamp end) {
	  Session session = sessionFactory.getCurrentSession();
	  //String sql = "SELECT * FROM query_log WHERE timestamp >= :start_timestamp";
	  String sql = "SELECT * FROM query_log";
	  SQLQuery query = session.createSQLQuery(sql);
	  query.addEntity(QueryLog.class);
	  //query.setParameter("start_timestamp", start);
	  
	  List<QueryLog> results = query.list();
	  
	  return results;
    //return (QueryLog) sessionFactory.getCurrentSession().get(QueryLog.class, log_id);
  }

  @SuppressWarnings("unchecked")
@Override
  public  List<QueryLogGroupedDTO> getMobilityStatusByDate(String start, String end) {
	  List<QueryLogGroupedDTO> results = null;
	  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Date startDate = null;
	  Date endDate = null;

	  Session session = sessionFactory.getCurrentSession();

	  Criteria criteria = session.createCriteria(QueryLog.class);

	  try{

		  startDate = formatter.parse("2015-09-19 00:00:00");
		  criteria.add(Restrictions.ge("timestamp", startDate)); 
	  }
	  catch (Exception e){
		  e.printStackTrace();
	  }
	  
	  try{

		  endDate = formatter.parse("2015-09-21 00:00:00");
		  criteria.add(Restrictions.lt("timestamp", endDate));
	  }
	  catch (Exception e){
		  e.printStackTrace();
	  }
	  
	  ProjectionList projectionList = Projections.projectionList(); 

	  projectionList.add(Projections.groupProperty("mobility_status"),"column_name");
	  projectionList.add(Projections.rowCount(), "count");  
    
	  criteria.setProjection(projectionList);
	  criteria.setResultTransformer(Transformers.aliasToBean(QueryLogGroupedDTO.class));
    
	  results = criteria.list();
	  
	  return results;
	    
	    //List<QueryLogGroupedDTO> results = session.createCriteria(QueryLog.class).
	    	//	setProjection(Projections.projectionList().add(Projections.groupProperty("mobility_status"), "grouped_column").add(Projections.rowCount(), "count"))  
              //  .setResultTransformer(Transformers.aliasToBean(QueryLogGroupedDTO.class))  
                //.list(); 
	    
	  //List results = query.list();
	
	 // System.out.println("return size ----------- " + results.size());
	    //List<QueryLogGroupedDTO> results = criteria.list();

//	  return results;
    //return (QueryLog) sessionFactory.getCurrentSession().get(QueryLog.class, log_id);
  }
  
  public  List<QueryLogGroupedDTO> getQueryTotalGroupByDate1(Timestamp start, Timestamp end) {
	  System.out.println("inside getQueryTotalGroupByDate");

	  Session session = sessionFactory.getCurrentSession();
	  //String sql = "SELECT * FROM query_log";
	  //String sql = "SELECT Cast(timestamp as date) queryDate, Count(*) AS Total FROM query_log WHERE timestamp > '2015-09-02 11:00:00' GROUP BY Cast(timestamp as date)";
	  String sql = "SELECT Cast(timestamp as date) queryDate, Count(*) AS count FROM query_log GROUP BY Cast(timestamp as date)";
	  SQLQuery query = session.createSQLQuery(sql);
        
	  List<QueryLogGroupedDTO> res = new ArrayList<QueryLogGroupedDTO>();

	  List<Object[]> rows = query.list();

	  for(Object[] item:rows){
		  QueryLogGroupedDTO query_log_dto = new QueryLogGroupedDTO();
		  	Map<String, BigInteger> map_obj = new HashMap<String,BigInteger>();
		  	query_log_dto.setCount(((BigInteger)item[1]).longValue());
		  	query_log_dto.setColumn_name((String)item[0].toString());
		  	
		  	map_obj.put((String)item[0].toString(),((BigInteger) item[1]));
		  	res.add(query_log_dto);
//			System.out.println("return size ----------- " + item[0] +":"+item[1]);

		}


	  
	  	for(QueryLogGroupedDTO map_obj: res){

				System.out.println("return size ----------- " + map_obj.getCount() +":"+map_obj.getColumn_name());
	  	}

		//System.out.println("return size ----------- " + res.size() + q1.get("queryDate"));

	    return res;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public List<QueryLog> getQueryLog() {
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QueryLog.class);
    
    return criteria.list();
  }

}