package com.nt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.Hobbies;

public interface IHobiesRepo extends JpaRepository<Hobbies, Integer> {
	
	@Query("SELECT hobbies FROM Hobbies")
	List<String> findAllHobbies();

}
