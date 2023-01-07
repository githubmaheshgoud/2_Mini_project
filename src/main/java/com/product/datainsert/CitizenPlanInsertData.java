package com.product.datainsert;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.product.entity.CitizenPlan;
import com.product.repository.CitizenPlanRepository;

@Component
public class CitizenPlanInsertData implements ApplicationRunner {
	@Autowired
	private CitizenPlanRepository repos;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		CitizenPlan plan = new CitizenPlan();
		plan.setCemail("mahesh@gmail.com");
		plan.setCid(101);
		plan.setCname("mahesh");
		plan.setGender("male");
		plan.setPhno(9688);
		plan.setPlanName("java");
		plan.setPlanStatus("accepted");
		plan.setSsn(9876);
		CitizenPlan plan1 = new CitizenPlan();
		plan1.setCemail("naresh@gmail.com");
		plan1.setCid(102);
		plan1.setCname("naresh");
		plan1.setGender("male");
		plan1.setPhno(9875);
		plan1.setPlanName("python");
		plan1.setPlanStatus("terminated");
		plan1.setSsn(98988877);
		CitizenPlan plan2 = new CitizenPlan();
		plan2.setCemail("srikanth@gmail.com");
		plan2.setCid(103);
		plan2.setCname("srikanth");
		plan2.setGender("male");
		plan2.setPhno(9865);
		plan2.setPlanName("devopd");
		plan2.setPlanStatus("accepted");
		plan2.setSsn(9544);

		List<CitizenPlan> c	=Arrays.asList(plan, plan1, plan2);
		repos.saveAll(c);
	}

}
