package uk.ac.abdn.fits.hibernate.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.QueryLog;

@Transactional(readOnly = false)
public interface QueryLogDAO {

	  void insertQueryLog(QueryLog query_log);
	  QueryLog getQueryLogById(int log_id);
	  List<QueryLog> getQueryLog();
}