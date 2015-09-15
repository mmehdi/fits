package uk.ac.abdn.fits.hibernate.dao.impl;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.SurchargeMobilityStatusDAO;
import uk.ac.abdn.fits.hibernate.model.SurchargeMobilityStatus;


@Service
public class SurchargeMobilityStatusDAOImpl implements
		SurchargeMobilityStatusDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertSurchargeMobilityStatus(
			SurchargeMobilityStatus mobility_status) {
		sessionFactory.
	      getCurrentSession();
		sessionFactory.getCurrentSession().save(mobility_status);
	}

	@Override
	public SurchargeMobilityStatus getSurchargeMobilityStatusByOpId(int operatorId) {
		
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from Surcharge_mobility_status where operator_id = :operator_id");
		  query.setParameter("operator_id", operatorId);
		  if(query.list().size() > 0){
			  return (SurchargeMobilityStatus)query.list().get(0);
		  }
		  return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SurchargeMobilityStatus> getSurchargeMobilityStatuss() {
		Criteria criteria = sessionFactory.
			      getCurrentSession().
			      createCriteria(SurchargeMobilityStatus.class);
		return criteria.list();
	}

}
