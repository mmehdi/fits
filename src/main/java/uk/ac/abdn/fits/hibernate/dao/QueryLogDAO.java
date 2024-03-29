package uk.ac.abdn.fits.hibernate.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.QueryLog;
import uk.ac.abdn.fits.hibernate.model.QueryLogGroupedDTO;

@Transactional(readOnly = false)
public interface QueryLogDAO {

	  void insertQueryLog(QueryLog query_log);
	  QueryLog getQueryLogById(int log_id);
	  List<QueryLog> getQueryLog();
	  List<QueryLog> getQueryLogByDateRange(String start, String end);
	  List<QueryLogGroupedDTO> getMobilityStatusByDate(String start, String end);
	  List<QueryLogGroupedDTO> getAgeGroupByDate(String start, String end);
	  List<QueryLogGroupedDTO> getPurposeByDate(String start, String end);
	  
	  List<QueryLogGroupedDTO> getAllQueryLogDataGroupByDate(String start, String end);
	  
	List<QueryLogGroupedDTO> getAllQueryLogDataGroupByDayOfWeek(String start,String end);
		
	  public void closeSession();


}