package com.example.vaadindemo.domain;

public class Patient {
	private long id;
	private String name;
	private String surname;
	private int yob = 0; // year of birth
	private String descriptionDesease = "";

	public Patient(long id, String name, String surname, int yob,
			String descriptionDesease) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.yob = yob;
		this.descriptionDesease = descriptionDesease;
	}

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getYob() {
		return yob;
	}

	public void setYob(int yob) {
		this.yob = yob;
	}

	public String getDescriptionDesease() {
		return descriptionDesease;
	}

	public void setDescriptionDesease(String descriptionDesease) {
		this.descriptionDesease = descriptionDesease;
	}

	public String toString() {
		return "Imie: " + name + " Nazwisko: " + surname + ". Opis choroby: " + descriptionDesease + ".";
	}
	
}
