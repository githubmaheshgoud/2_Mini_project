package com.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="citizen_plan")
@Data
@NoArgsConstructor

public class CitizenPlan {
	@Id
	@GeneratedValue
	private Integer cid;
	
	private String planName;
	
	private String planStatus;
	private String cname;
	private String cemail;
	private String gender;
	private Integer phno;
	private Integer ssn;

}
