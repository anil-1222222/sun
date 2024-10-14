package com.nt.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nt.entity.Emp;
import com.nt.repo.IEmpRepo;
import com.nt.repo.IHobiesRepo;
@Service
public class EmpServiceImpl implements IEmpService {
	@Autowired
	private IEmpRepo repo;
	@Autowired
	private IHobiesRepo hobRepo;
	@Autowired
	private Environment env;

	@Override
	public List<Emp> getAllDetails() {
		return repo.findAll() ;
	}

	@Override
	public String registerEmp(Emp emp) {
		return repo.save(emp).getEmpId()+" registered Successully";
	}

	@Override
	public Optional<Emp> getEmpRecordById(int id) {
		return repo.findById(id);
	}

	@Override
	public String updateEmp(Emp emp) {
		return repo.save(emp).getEmpId()+" updated Successully";
	}

	@Override
	public String deleteRecord(int id) {
		repo.deleteById(id);
		return id+" deleted Successfully";
	}

	@Override
	public Page<Emp> getAllPageble(Pageable pageble) {
		// TODO Auto-generated method stub
		return repo.findAll(pageble);
	}

	@Override
	public Set<String> getAllCountries() {
           Locale[] locales = Locale.getAvailableLocales();
           Set<String> countriesSet = new TreeSet();
           for(Locale l:locales) {
        	   countriesSet.add(l.getDisplayCountry());
           }
		return countriesSet;
	}

	@Override
	public Set<String> getAllLanguages() {
		 Locale[] locales = Locale.getAvailableLocales();
         Set<String> languageSet = new TreeSet();
         for(Locale l:locales) {
      	   languageSet.add(l.getDisplayLanguage());
         }
		return languageSet;
	}

	@Override
	public List<String> getAllHobbies() {
		return hobRepo.findAllHobbies();
	}

	@Override
	public List<String> getStatesByCountry(String country) {
		String statesInfo = env.getRequiredProperty(country);
		List<String> statesList = Arrays.asList(statesInfo.split(","));
		Collections.sort(statesList);
		return statesList;
	}

	@Override
	public String fetchResumePathById(Integer id) {
		return repo.getResumePathbyId(id);
	}

	@Override
	public String fetchPhotoPathById(Integer id) {
		// TODO Auto-generated method stub
		return repo.getPhotoPathbyId(id);
	}
	
	

}
