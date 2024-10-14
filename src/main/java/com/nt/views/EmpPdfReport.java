package com.nt.views;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.entity.Emp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("pdf_report")
public class EmpPdfReport extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get model attribute data
		List<Emp> list=(List<Emp>) map.get("result");
		
		//add heading content
		Paragraph para=new Paragraph("Employees Report",new Font(Font.COURIER,20,Font.BOLD,Color.BLUE));
		document.add(para);
		
		//create table having employees info
		Table table=new Table(6,list.size());
		table.setBackgroundColor(Color.lightGray);
		table.setBorder(10);
		table.setComplete(false);
		table.setWidth(100);
		for(Emp emp:list) {
			table.addCell(String.valueOf(emp.getEmpId()));
			table.addCell(emp.getEmpName());
			table.addCell(emp.getGender());
			table.addCell(emp.getCountry());
			table.addCell(emp.getState());
			table.addCell(String.valueOf(emp.getSal()));
			//table.addCell(String.valueOf(emp.getDob()));
		//	table.addCell(String.valueOf(emp.getDoj()));
			//table.addCell(String.valueOf(emp.getLanguages()));
			//table.addCell(String.valueOf(emp.getHobbies()));
			//table.addCell(emp.getPhotoPath());
			//table.addCell(emp.getResumePath());
		}
		document.add(table);
		
	}

}
