package com.nt.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nt.entity.Emp;

public interface IEmpService {
	public List<Emp> getAllDetails();
	public String registerEmp(Emp emp);
	public Optional<Emp> getEmpRecordById(int id);
	public String updateEmp(Emp emp);
	public String deleteRecord(int id);
	
	Page<Emp> getAllPageble(Pageable pageble);
	
	Set<String> getAllCountries();
	Set<String> getAllLanguages();
	
	List<String> getAllHobbies();
	
	public List<String> getStatesByCountry(String country);
	
	public String fetchResumePathById(Integer id);
	public String fetchPhotoPathById(Integer id);

}
