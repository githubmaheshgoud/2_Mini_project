package com.product.service;

import java.awt.Color;
import java.util.List;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.product.entity.CitizenPlan;
import com.product.entity.SearchRequest;
import com.product.repository.CitizenPlanRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository cprepo;

	@Override
	public List<String> getPlanNames() {
		return cprepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {

		return cprepo.getPlanStatues();
	}

	@Override
	public List<CitizenPlan> getCitizenPlans(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();

		if (request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (request.getGender() != null && !request.getGender().equals("")) {
			entity.setGender(request.getGender());
		}

		Example<CitizenPlan> example = Example.of(entity);
		List<CitizenPlan> record = cprepo.findAll(example);

		return record;
	}

	@Override
	public void exportExcel(HttpServletResponse response) throws Exception {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("Citizens Info");
		XSSFRow headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("Id");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("SSN");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("Plan Name");
		headerRow.createCell(5).setCellValue("Plan Status");

		List<CitizenPlan> records = cprepo.findAll();

		int datarowIndex = 1;
		for (CitizenPlan record : records) {
			XSSFRow datarow = sheet.createRow(datarowIndex);
			datarow.createCell(0).setCellValue(record.getCid());
			datarow.createCell(1).setCellValue(record.getCname());
			datarow.createCell(2).setCellValue(record.getSsn());
			datarow.createCell(3).setCellValue(record.getGender());
			datarow.createCell(4).setCellValue(record.getPlanName());
			datarow.createCell(5).setCellValue(record.getPlanStatus());

			datarowIndex++;
		}
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}

	@Override
	public void exportPdf(HttpServletResponse response) throws Exception {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Citizen plans Info", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f ,1.5f});
		table.setSpacingBefore(10);
       
		
		 
		// set Table header data

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase(" ID", font1));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Palan Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Palan Status", font1));
		table.addCell(cell);
//set table data

		List<CitizenPlan> records = cprepo.findAll();
		for (CitizenPlan record : records) {

			table.addCell(String.valueOf(record.getCid()));
			table.addCell(record.getCname());
			table.addCell(String.valueOf(record.getSsn()));
			table.addCell(record.getGender());
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());

		}
		document.add(table);

		document.close();

	}

}
