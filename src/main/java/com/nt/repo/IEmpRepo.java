package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.Emp;

public interface IEmpRepo extends JpaRepository<Emp, Integer>{
	@Query("select resumePath from Emp where empId=:id")
	public String getResumePathbyId(Integer id);
	
	@Query("select photoPath from Emp where empId=:id")
	public String getPhotoPathbyId(Integer id);

}
