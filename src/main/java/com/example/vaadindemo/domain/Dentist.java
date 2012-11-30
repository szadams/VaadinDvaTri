package com.example.vaadindemo.domain;

public class Dentist {

	private long id;
	private String name = "";
	private String surname = "";
	private int experience = 0; // years
	private String imgPath = "";

	public Dentist(long id, String name, String surname, int experience,
			String imgPath) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.experience = experience;
		this.imgPath = imgPath;
	}

	public Dentist() {

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

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String toString() {
		return "Imie: " + name + " Nazwisko: " + surname + ". Doswiadczenie: " + experience + " lat.";
	}

}
