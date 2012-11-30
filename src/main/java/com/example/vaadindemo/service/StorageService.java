package com.example.vaadindemo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.vaadindemo.domain.Dentist;
import com.example.vaadindemo.domain.Patient;

public class StorageService {

private List<Dentist> dentists = new ArrayList<Dentist>();
	
	private List<Patient> patients = new ArrayList<Patient>();

	public void addDentist(Dentist dentist) {
		Dentist d = new Dentist();
		d.setId(dentist.getId());
		d.setName(dentist.getName());
		d.setSurname(dentist.getSurname());
		d.setExperience(dentist.getExperience());
		d.setImgPath(dentist.getImgPath());
		dentists.add(d);
	}

	public List<Dentist> getAllDentists() {
		return dentists;
	}

	public void deleteDentist(Dentist d) {
		Dentist toRemove = null;
		for (Dentist dentist : dentists){
			if (dentist.getId() == d.getId()) {
				toRemove = dentist;
				break;
			}
		}
		dentists.remove(toRemove);
	}
	
	public void addPatient(Patient patient) {
		Patient p = new Patient();
		p.setId(patient.getId());
		p.setName(patient.getName());
		p.setSurname(patient.getSurname());
		p.setYob(patient.getYob());
		p.setDescriptionDesease(patient.getDescriptionDesease());
		patients.add(p);
	}

	public List<Patient> getAllPatients() {
		return patients;
	}

	public void deletePatient(Patient p) {
		Patient toRemove = null;
		for (Patient patient : patients){
			if (patient.getId() == p.getId()) {
				toRemove = patient;
				break;
			}
		}
		patients.remove(toRemove);
	}

}
