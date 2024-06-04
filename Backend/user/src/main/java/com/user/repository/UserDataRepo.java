package com.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.user.entity.UserData;

@Repository 
public interface UserDataRepo extends MongoRepository<UserData,String>{
//     UserData findById(long userId);
	
	@Query("{'userName' : :#{#userName}}")
	Optional<UserData> findByName(@Param("userName") String userName);
}
