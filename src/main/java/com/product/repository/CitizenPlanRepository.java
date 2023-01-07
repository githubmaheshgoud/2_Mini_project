package com.product.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.product.entity.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Serializable> {
  @Query(value="select distinct(plan_name) from citizen_plan",nativeQuery = true )
	public List<String> getPlanNames();
  @Query(value="select distinct(plan_status) from citizen_plan",nativeQuery = true)

public List<String> getPlanStatues();





	

}
