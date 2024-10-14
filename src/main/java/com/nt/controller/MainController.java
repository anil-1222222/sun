package com.nt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.Emp;
import com.nt.model.FileModel;
import com.nt.service.IEmpService;
import com.nt.validator.EmpValidator;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
	
	@Autowired
	private IEmpService service;
	@Autowired
	private EmpValidator validator;
	@Autowired
	private Environment env;
	@Autowired
	private ServletContext sc;
	@GetMapping("/")
	public String home(@PageableDefault(page=0,size=3,sort="empId",direction =Direction.ASC)Pageable oageable,Map<String, Object> map) {
         //List<Emp> list = service.getAllDetails();
		Page<Emp> page = service.getAllPageble(oageable);
		map.put("page", page);
		System.out.println(page);
		return "home";
	}
	
	@GetMapping("/report")
	public String showReport(Map<String, Object>map,@RequestParam(name="type")String type) {
		List<Emp> list = service.getAllDetails();
		map.put("result", list);
		if(type.equalsIgnoreCase("excel")) {
			return "excel_report";
		}
		else {
			return "pdf_report";
		}
	}
	
	@GetMapping("/add")
	public String addPage(@ModelAttribute("emp1")FileModel emp1) {
		return "addPage";
	}
	
	@PostMapping("/add")
	public String addpage(@ModelAttribute("emp")Emp emp,RedirectAttributes atrs,BindingResult errors,@ModelAttribute("emp1")FileModel emp1) throws Exception{
		
		if(validator.supports(emp.getClass())) {
			validator.validate(emp, errors);
			if(errors.hasErrors()) {
				return "addPage";
			}
		}
		//get folder of file uploading from the application properties file
		String storeLocation=env.getRequiredProperty("upload.store");
		
		//Create StoreLocation folder if it is not alreadt there
		File storeLocationFolder=new File(storeLocation);
		if(!storeLocationFolder.exists())
			storeLocationFolder.mkdir();
		
		//get MultiPartFile Objects representing the uploaded files
		MultipartFile file1 = emp1.getResume();
		MultipartFile file2 = emp1.getPhoto();
		
		//create inputStream from the MultiPartFile objects
		InputStream file1Is = file1.getInputStream();
		InputStream file2Is = file2.getInputStream();
		
		//get original file names from the list of uploaded files
		String fileName1 = file1.getOriginalFilename();
		String fileName2 = file2.getOriginalFilename();
		
		//create OutputStreams having destination file names
		FileOutputStream file1Os = new FileOutputStream(storeLocationFolder.getAbsolutePath()+"/"+fileName1);
		FileOutputStream file2Os = new FileOutputStream(storeLocationFolder.getAbsolutePath()+"/"+fileName2);
		
		//copy the content of uploaded files from to Dest files
		IOUtils.copy(file1Is,file1Os);
		IOUtils.copy(file2Is,file2Os);
		
		//close streams
		file1Is.close();
		file1Os.close();
		file2Is.close();
		file2Os.close();
		
		
		
		//create Entity Class object having content of Model Class obj
		Emp emp2 = new Emp();
		emp2.setEmpId(emp1.getEmpId());
		emp2.setEmpName(emp1.getEmpName());
		emp2.setCountry(emp1.getCountry());
		emp2.setDob(emp1.getDob());
		emp2.setDoj(emp1.getDoj());
		emp2.setGender(emp1.getGender());
		emp2.setHobbies(emp1.getHobbies());
		emp2.setLanguages(emp1.getLanguages());
		String file1Path=(storeLocationFolder.getAbsolutePath()+"/"+fileName1).replace("/", "\\");
		String file2Path=(storeLocationFolder.getAbsolutePath()+"/"+fileName2).replace("/", "\\");
		
		emp2.setPhotoPath(file2Path);
		emp2.setResumePath(file1Path);
		emp2.setSal(emp1.getSal());
		emp2.setState(emp1.getState());
		
		
		String register = service.registerEmp(emp2);
		atrs.addFlashAttribute("msg", register);
		return "redirect:/";
		
	}
	
	@GetMapping("/edit")
	public String updatePage(@ModelAttribute("emp")Emp emp,@RequestParam("empId")int empId) {
		Emp emp2 = service.getEmpRecordById(empId).get();
		BeanUtils.copyProperties(emp2, emp);
		return "editPage";
	}
	
	@PostMapping("/edit")
	public String updatepage(@ModelAttribute("emp")Emp emp,RedirectAttributes atrs,BindingResult errors) {
		if(validator.supports(emp.getClass())) {
			validator.validate(emp, errors);
			if(errors.hasErrors()) {
				return "editPage";
			}
		}
		String register = service.updateEmp(emp);
		atrs.addFlashAttribute("msg", register);
		return "redirect:/";
	}
	
	@GetMapping("delete")
	public String deleteRecord(@RequestParam("empId")int empId,RedirectAttributes atrs) {
		String deleteRecord = service.deleteRecord(empId);
		//List<Emp> list = service.getAllDetails();
		atrs.addFlashAttribute("msg", deleteRecord);
		//map.put("list",list );
		return "redirect:/";
	}
	/*
	@PostMapping("states")
	public String showStatesByCountry(@RequestParam("country")String country,@ModelAttribute("emp")Emp emp,Map<String,Object>map,BindingResult errors,RedirectAttributes atrs) {
		if(validator.supports(emp.getClass())) {
			validator.validate(emp, errors);
			if(errors.hasErrors()) {
				return "Page";
			}
			List<String> statesList = service.getStatesByCountry(country);
			map.put("statesInfo", statesList);
			String register = service.registerEmp(emp);
			atrs.addFlashAttribute("msg", register);
		}
		return "redirect:/";
	}
	*/
	
	@PostMapping("states")
	public String showStatesByCountry(@RequestParam("country")String country,@ModelAttribute("emp1")FileModel emp1,Map<String,Object>map) {
			List<String> statesList = service.getStatesByCountry(country);
			map.put("statesInfo", statesList);
			//String register = service.registerEmp(emp);
			//atrs.addFlashAttribute("msg", register);
		return "addPage";
	}
	
	@GetMapping("/download")
	public String fileDownload(@RequestParam("empId")int empId,@RequestParam("type")String type,HttpServletResponse res)throws Exception{
		String filePath=null;
		if(type.equalsIgnoreCase("resume")) {
			filePath=service.fetchResumePathById(empId);
		}
		else {
			filePath=service.fetchPhotoPathById(empId);
		}
		
		//create java.io.File object pointing to the file to download
		File file=new File(filePath);
		
		//get the length of the file and make it as the response content length
		res.setContentLengthLong(file.length());
		
		//get the MIME of the file to be download and make it as the response content type
		String mimeType=sc.getMimeType(filePath);
		mimeType=mimeType!=null?mimeType:"application/octet-stream";
		res.setContentType(mimeType);
		
		//create Input pointing to the file to be downloaded
		FileInputStream is = new FileInputStream(filePath);
		
		//create OutputStream pointing to the response object
		ServletOutputStream os = res.getOutputStream();
		
		//give instruction to browser to make the recieved content as the downloadable file
		res.setHeader("Content-Disposition", "attachment;fileName="+file.getName());
		
		//copy file to be download content to response object
		IOUtils.copy(is,os);
		
		//close streams
		is.close();
		os.close();
		return null; //this indicates response should go to browser directly, without taking the support of ViewResolver and View comps
		
	}
	
	
	
	@ModelAttribute("countriesInfo")
	public Set<String> populateCountries(){
		return service.getAllCountries();
	}
	
	@ModelAttribute("languagesInfo")
	public Set<String> populateLanguages(){
		return service.getAllLanguages();
	}
	
	@ModelAttribute("hobbiesInfo")
	public List<String> populateHobbies(){
		return service.getAllHobbies();
	}
	
	@InitBinder
	public void myInitBinder(WebDataBinder binder) {
		System.out.println("MainController.myInitBinder()");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(sdf, false);
		binder.registerCustomEditor(java.util.Date.class, editor);
	}

}
