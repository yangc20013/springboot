package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.User;

@Repository
public interface  UserRepository extends CrudRepository<User, Long> {
	@Query("select a from User a")
	List<User> findUser();
	
//	@Query("select r from CachedMtdPerformanceDataDtvds r where r.hrId = ?1 and r.firstDate = ?2 order by r.ach desc")
//	List<? extends CachedPerformanceDataDtvds> findByHrIdAndFirstDate(String hrId, Calendar firstDayOfMonth);
}
