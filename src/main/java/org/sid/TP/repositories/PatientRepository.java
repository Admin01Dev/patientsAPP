package org.sid.TP.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.TP.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	public Page<Patient> findByNameContains(String name, Pageable pageable);
	public List<Patient> findBySick(boolean sick);
	public List<Patient> findByNameContainsAndSick(String name, boolean sick);
	@Transactional
	public List<Patient> deleteBySick(boolean sick);
//	@Query("delete from Patient p where p.sick like :x")
//	void deleteBysick(@Param("x") boolean sick);
	public Page<Patient> findAll(Pageable pageable);
}
