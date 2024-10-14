package com.nt.views;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;


import com.nt.entity.Emp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("excel_report")
public class EmpExcelReport extends AbstractXlsView{
	int i=0;
	@Override
	protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		
		List<Emp> list=(List<Emp>) map.get("result");
		Sheet sheet1=workbook.createSheet("sheet1");
		
		
		list.forEach(emp->{
		Row row=sheet1.createRow(i);
		row.createCell(0).setCellValue(emp.getEmpId());
		row.createCell(1).setCellValue(emp.getEmpName());
		row.createCell(2).setCellValue(emp.getGender());
		row.createCell(3).setCellValue(emp.getCountry());
		row.createCell(4).setCellValue(emp.getState());
		row.createCell(5).setCellValue(emp.getSal());
		row.createCell(6).setCellValue(String.valueOf(emp.getDob()));
		row.createCell(7).setCellValue(String.valueOf(emp.getDoj()));
		i++;
		});
		
	}

}
