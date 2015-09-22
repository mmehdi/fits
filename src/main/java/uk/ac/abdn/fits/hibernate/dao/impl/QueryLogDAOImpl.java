package uk.ac.abdn.fits.hibernate.dao.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.QueryLogDAO;
import uk.ac.abdn.fits.hibernate.model.QueryLog;
import uk.ac.abdn.fits.hibernate.model.QueryLogGroupedDTO;

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
  public  List<QueryLogGroupedDTO> getMobilityStatusByDate(String start, String end) {
	  return getGroupedDataByDate("mobility_status", start, end);
  }
  
  @Override
  public  List<QueryLogGroupedDTO> getAgeGroupByDate(String start, String end) {
	  return getGroupedDataByDate("age_group", start, end);
  }
  
  @Override
  public  List<QueryLogGroupedDTO> getPurposeByDate(String start, String end) {
	  return getGroupedDataByDate("purpose", start, end);
  }
  
  /*
   * method to return queries by date range
   * @see uk.ac.abdn.fits.hibernate.dao.QueryLogDAO#getQueryLogByDateRange(java.lang.String, java.lang.String)
   */
@SuppressWarnings("unchecked")
@Override
  public List<QueryLog> getQueryLogByDateRange(String start, String end) {
	  List<QueryLog> results = null;
	  
  	  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  	  Date startDate = null;
  	  Date endDate = null;

  	  Session session = sessionFactory.getCurrentSession();

  	  Criteria criteria = session.createCriteria(QueryLog.class);

  	  try{
  		  if(start!=null && start.length()>0){
  			  startDate = formatter.parse(start);
  			  criteria.add(Restrictions.ge("timestamp", startDate)); 
  		  }
  	  }
  	  catch (Exception e){
  		  e.printStackTrace();
  	  }
  	  
  	  try{
  		  if(end!=null && end.length()>0){
  			  endDate = formatter.parse(end);
  			 
  			  //increment end date by one day - 
  			  Calendar c = Calendar.getInstance(); 
  			  c.setTime(endDate); 
  			  c.add(Calendar.DATE, 1);
  			  endDate = c.getTime();
  			  
  			  criteria.add(Restrictions.lt("timestamp", endDate));
  		  }
  	  }
  	  catch (Exception e){
  		  e.printStackTrace();
  	  }
  	  
  	  ProjectionList projectionList = Projections.projectionList(); 
  	  projectionList.add(Projections.property("id"), "id");
  	  projectionList.add(Projections.property("from_postcode"),"from_postcode");
  	  projectionList.add(Projections.property("from_address"),"from_address");
  	  projectionList.add(Projections.property("to_postcode"),"to_postcode");
  	  projectionList.add(Projections.property("to_address"),"to_address");
  	  projectionList.add(Projections.property("age_group"),"age_group");
  	  projectionList.add(Projections.property("mobility_status"),"mobility_status");
  	  projectionList.add(Projections.property("purpose"),"purpose");
  	  projectionList.add(Projections.property("is_return"),"is_return");
  	  projectionList.add(Projections.property("timestamp"),"timestamp");

  	  criteria.addOrder(Order.asc(("timestamp")));
  	  criteria.setProjection(projectionList);
  	  criteria.setResultTransformer(Transformers.aliasToBean(QueryLog.class));
      
  	  results = criteria.list();
  	  
  	  return results;
  	  
  }


  /*
   * method to return grouped queries by mobility_status, age_group or journey purpose
   */
  @SuppressWarnings("unchecked")
    private  List<QueryLogGroupedDTO> getGroupedDataByDate(String column_name, String start, String end) {
  	  List<QueryLogGroupedDTO> results = null;
  	  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  	  Date startDate = null;
  	  Date endDate = null;

  	  Session session = sessionFactory.getCurrentSession();

  	  Criteria criteria = session.createCriteria(QueryLog.class);

  	  try{
  		  if(start!=null && start.length()>0){
  			  startDate = formatter.parse(start);
  			  criteria.add(Restrictions.ge("timestamp", startDate)); 
  		  }
  	  }
  	  catch (Exception e){
  		  e.printStackTrace();
  	  }
  	  
  	  try{
  		  if(end!=null && end.length()>0){
  			  endDate = formatter.parse(end);
  			 
  			  //increment end date by one day - 
  			  Calendar c = Calendar.getInstance(); 
  			  c.setTime(endDate); 
  			  c.add(Calendar.DATE, 1);
  			  endDate = c.getTime();
  			  
  			  criteria.add(Restrictions.lt("timestamp", endDate));
  		  }
  	  }
  	  catch (Exception e){
  		  e.printStackTrace();
  	  }
  	  
  	  ProjectionList projectionList = Projections.projectionList(); 

  	  projectionList.add(Projections.groupProperty(column_name),"column_name");
  	  projectionList.add(Projections.rowCount(), "count");  
  	  criteria.addOrder(Order.desc(("timestamp")));

  	  criteria.setProjection(projectionList);
  	  criteria.setResultTransformer(Transformers.aliasToBean(QueryLogGroupedDTO.class));
      
  	  results = criteria.list();
  	  
  	  return results;
    }
  
  /*
   * method to return queries grouped by date.
   * @see uk.ac.abdn.fits.hibernate.dao.QueryLogDAO#getAllQueryLogDataGroupByDate(java.lang.String, java.lang.String)
   */
  @SuppressWarnings("unchecked")
  @Override
  public  List<QueryLogGroupedDTO> getAllQueryLogDataGroupByDate(String start, String end) {

	  Session session = sessionFactory.getCurrentSession();
	  //String sql = "SELECT * FROM query_log";
	  //String sql = "SELECT Cast(timestamp as date) queryDate, Count(*) AS Total FROM query_log WHERE timestamp > '2015-09-02 11:00:00' GROUP BY Cast(timestamp as date)";
	  String sql = "SELECT Cast(timestamp as date) queryDate, Count(*) AS count FROM query_log";
	  List<QueryLogGroupedDTO> res = new ArrayList<QueryLogGroupedDTO>();
	  
	  try{
		  String where_clause="";
		  if(start!=null && start.length()>0){
			  where_clause = " WHERE timestamp>='"+start+"'";
			  	if(end!=null && end.length()>0)
			  		where_clause = where_clause +" && timestamp<='"+end+"'";
		  }
		  else if(end!=null && end.length()>0)
			  	where_clause = " WHERE timestamp<='"+end+"'";
		   
		  sql = sql + where_clause;
		  sql = sql + " GROUP BY Cast(timestamp as date) ORDER BY timestamp ASC";
		  
		  SQLQuery query = session.createSQLQuery(sql);
	        
	
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

	  }
	  catch (Exception e){
		  e.printStackTrace();
	  }
	    return res;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public List<QueryLog> getQueryLog() {
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QueryLog.class);
    
    return criteria.list();
  }

}