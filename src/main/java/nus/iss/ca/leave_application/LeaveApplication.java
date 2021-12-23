package nus.iss.ca.leave_application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.repositories.ApplicationRepository;

@SpringBootApplication
@RestController
public class LeaveApplication {

	@Autowired
	private ApplicationRepository arepo;
	
	@GetMapping("/getApplications")
	public List<Application> getApplications(){
		return arepo.findAll();
	}
	
	
    public static void main(String[] args) {
        SpringApplication.run(LeaveApplication.class, args);
    }

}
