package org.sid.TP;

import java.util.Date;

import org.sid.TP.entities.Patient;
import org.sid.TP.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class TpApplication implements CommandLineRunner{

	
	@Autowired
	private PatientRepository patient;
	
	public static void main(String[] args) {
		SpringApplication.run(TpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
				
		patient.save(new Patient(null, "TEST M1", new Date(), false));
		patient.save(new Patient(null, "TEST P2", new Date(), true));
		patient.save(new Patient(null, "TEST P3", new Date(), false));
		patient.save(new Patient(null, "TEST M4", new Date(), true));
		patient.save(new Patient(null, "TEST P5", new Date(), true));
		patient.save(new Patient(null, "TEST M6", new Date(), true));
		patient.save(new Patient(null, "TEST P8", new Date(), true));
		patient.save(new Patient(null, "TEST M7", new Date(), false));
		patient.save(new Patient(null, "TEST P9", new Date(), false));
		patient.save(new Patient(null, "TEST M5", new Date(), true));
		patient.save(new Patient(null, "TEST P1", new Date(), true));
		patient.save(new Patient(null, "TEST M2", new Date(), false));
		patient.save(new Patient(null, "TEST P6", new Date(), true));
		patient.save(new Patient(null, "TEST M9", new Date(), true));
		
		System.out.println("\nAll Patients:");
		patient.findAll().forEach(p -> {System.out.println(p.toString());});
		
		System.out.println("\nPatients That Have P In Their Names By Page:");
		for (int index = 0; index < patient.findByNameContains("P", PageRequest.of(index, 3)).getTotalPages(); index++) {
			System.out.println("Page " + (index + 1));
			patient.findByNameContains("P", PageRequest.of(index, 3)).forEach(p -> {System.out.println(p.toString());});
		}
		
		System.out.println("\nPatients That Are Not Sick:");
		patient.findBySick(false).forEach(p -> {System.out.println(p.toString());});
		
		System.out.println("\nPatients That Have M In Their Names And Are Sick");
		patient.findByNameContainsAndSick("M", true).forEach(p -> {System.out.println(p.toString());});
		
		System.out.println("\nAll Patients After Removal Of Cured Patients:");
		patient.deleteBySick(false);
		patient.findAll().forEach(p -> {System.out.println(p.toString());});
		
//		Page<Patient> pagePatients = patient.findAll(PageRequest.of(0, 3));
//		System.out.println("Num of Pages: " + pagePatients.getTotalPages());
//		List<Patient> listPatients = pagePatients.getContent();
//		listPatients.forEach(p -> {System.out.println(p.toString());});

		
		
	}
		
}
