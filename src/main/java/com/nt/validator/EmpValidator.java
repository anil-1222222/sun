package com.nt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nt.entity.Emp;
@Component
public class EmpValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("EmpValidator.supports()");
		return clazz.isAssignableFrom(Emp.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("EmpValidator.validate()");
		
		Emp emp=(Emp)target;
		
		if(emp.getEmpName()==null || emp.getEmpName().isBlank())
			errors.rejectValue("empName","emp.name.required");
		else if(emp.getEmpName().length()<5)
			errors.rejectValue("empName", "emp.name.minlength");
		
		if(emp.getSal()==null)
			errors.rejectValue("sal", "emp.sal.required");
		else if(emp.getSal().isNaN())
			errors.rejectValue("sal","emp.sal.numeric");
		else if(emp.getSal()<10000 || emp.getSal()>100000)
			errors.rejectValue("sal", "emp.sal.range");

	}

}
