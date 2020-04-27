package com.practicas.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.practicas.model.Car;
import com.practicas.model.Classification;

@Repository("carDao")
@Transactional
public class CarDaoImpl extends AbstractDao<Serializable, Car> implements CarDao {

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Car> findPaginationCars(int start, int end) {
		
		List<Car> listCar = getEntityManager()
				.createQuery("SELECT c FROM Car c ORDER BY c.id ASC LIMIT :start,:end")
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
		
		return listCar;
	}

	public List<Car> findPaginationCarsEjemplo(int start, int end, Map<String, Object> params, String order, String ascDesc) {
		
		StringBuffer sb = new StringBuffer("SELECT c FROM Car c WHERE ");
		for(Map.Entry<String, Object> entry: params.entrySet()) {
			sb.append(" AND ");
			sb.append(entry.getKey());
			sb.append(" = ");
			sb.append(" :");
			sb.append(entry.getKey());
		}
		
		if(order != null && !order.equals("")) {
			sb.append(" ORDER BY "+order+","+ascDesc);
		}
		if(start > 0) {
			sb.append(" LIMIT "+start+","+end);
		}
		if(end > 0) {
			sb.append(" , "+end);
		}
		Query q = getEntityManager().createQuery(sb.toString());
		
		for(Map.Entry<String, Object> entry: params.entrySet()) {
			if(entry.getValue() instanceof String) {
				q.setParameter(entry.getKey(), (String)entry.getValue());
			}else if(entry.getValue() instanceof Integer) {
				q.setParameter(entry.getKey(), (Integer)entry.getValue());
			}
		}
		List<Car> listCar = q.getResultList();
		
		return listCar;
	}
	
	public Car findCarByName(String name) {

		try {
			Car c = (Car) getEntityManager()
					.createQuery("SELECT c FROM Car c where c.name = :name")
					.setParameter("name", name).getSingleResult();
			return c;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Car save(Car c) {
		Car c1 = findCarByName(c.getName());
		if(c1 == null) {
			return persist(c);
		}
		return c1;
	}
	
	public Car getByPk(Integer key) {
		return getByKey(key);
	}

}
