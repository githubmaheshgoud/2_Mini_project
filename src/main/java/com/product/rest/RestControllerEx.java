package com.product.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.CitizenPlan;
import com.product.entity.SearchRequest;
import com.product.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class RestControllerEx {

	@Autowired
	private ReportService repo;

	// localhost:8080/planName/java
	@GetMapping("/planName")
	public ResponseEntity<List<String>> getPlanName() {
		List<String> planNames = repo.getPlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);

	}

	@GetMapping("/planStatus")
	public ResponseEntity<List<String>> getPlanStatus() {
		List<String> planStatus = repo.getPlanStatuses();
		return new ResponseEntity<>(planStatus, HttpStatus.OK);

	}

	@PostMapping("/search")
	public ResponseEntity<List<CitizenPlan>> getSerch(@RequestBody SearchRequest request) {
		List<CitizenPlan> citizenPlans = repo.getCitizenPlans(request);
		return new ResponseEntity<>(citizenPlans, HttpStatus.OK);

	}

	@GetMapping("/export")
	public void exportExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		String key = "Content-Disposition";
		String value = "attachment;file=citizens.xls";
		response.setHeader(key, value);
		repo.exportExcel(response);
		response.flushBuffer();

	}

	@GetMapping("/pdf")
	public void exportPDF(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");

		String key = "Content-Disposition";
		String value = "attachment;plans.pdf";

		response.setHeader(key, value);

		repo.exportPdf(response);
		response.flushBuffer();
	}
}
