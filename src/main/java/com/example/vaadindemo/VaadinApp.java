package com.example.vaadindemo;

import com.example.vaadindemo.domain.Dentist;
import com.example.vaadindemo.domain.Patient;
import com.example.vaadindemo.service.StorageService;
import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;

public class VaadinApp extends Application {

	private static final long serialVersionUID = 1L;

	private BeanItemContainer<Dentist> dentistContainer = new BeanItemContainer<Dentist>(
			Dentist.class);
	private Table dentistsTable = new Table("Stomatolodzy", dentistContainer);

	private BeanItemContainer<Patient> patientContainer = new BeanItemContainer<Patient>(
			Patient.class);
	private Table patientsTable = new Table("Pacjenci", patientContainer);

	private Window mainWindow = new Window();
	
	private Button addDentistButton = new Button("Dodaj stomatologa");
	private Button addPatientButton = new Button("Dodaj pacjenta");

	private Window dentistFormWindow;
	private Window patientFormWindow;

	private StorageService storageService;

	public VaadinApp(StorageService storageService) {
		this.storageService = storageService;
	}

	public StorageService getStorageService() {
		return storageService;
	}

	@Override
	public void init() {

//		setTheme("moj_theme");
		
		initDataSource();
		updatePatientTable();
		
		HorizontalLayout hl = new HorizontalLayout();
		
		hl.addComponent(dentistsTable);
		
		dentistsTable.setSelectable(true);
		dentistsTable.setImmediate(true);

		dentistsTable.addListener(new Table.ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (dentistsTable.getValue() != null) {
					mainWindow.showNotification("" + dentistsTable.getValue());
				}
			}
		});
		
		hl.addComponent(patientsTable);
		
		patientsTable.setSelectable(true);
		patientsTable.setImmediate(true);

		patientsTable.addListener(new Table.ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (patientsTable.getValue() != null) {
					mainWindow.showNotification("" + patientsTable.getValue());
				}
			}
		});

		addDentistButton.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				if (dentistFormWindow != null) {
					mainWindow.removeWindow(dentistFormWindow);
				}

				BeanItem<Dentist> beanDentistItem = new BeanItem<Dentist>(
						new Dentist());
				DentistFormWindowFactory dff = new DentistFormWindowFactory(
						beanDentistItem, VaadinApp.this);

				dentistFormWindow = dff.createWindow();
				mainWindow.addWindow(dentistFormWindow);
			}
		});

		mainWindow.addComponent(addDentistButton);
		
		addPatientButton.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				if (patientFormWindow != null) {
					mainWindow.removeWindow(patientFormWindow);
				}

				BeanItem<Patient> beanPatientItem = new BeanItem<Patient>(
						new Patient());
				PatientFormWindowFactory pff = new PatientFormWindowFactory(
						beanPatientItem, VaadinApp.this);

				patientFormWindow = pff.createWindow();
				mainWindow.addWindow(patientFormWindow);
			}
		});
	
		mainWindow.addComponent(addPatientButton);
		
		mainWindow.addComponent(hl);
		
		setMainWindow(mainWindow);
	}

	private void initDataSource() {

		Dentist d1 = new Dentist();
		d1.setId(1L);
		d1.setName("Adam");
		d1.setSurname("Kowalski");
		d1.setExperience(2);
		d1.setImgPath("");
		storageService.addDentist(d1);

		Dentist d2 = new Dentist();
		d2.setId(2L);
		d2.setName("Zuzanna");
		d2.setSurname("Przekop");
		d2.setExperience(5);
		d2.setImgPath("");
		storageService.addDentist(d2);

		Patient p1 = new Patient();
		p1.setId(1L);
		p1.setName("Leon");
		p1.setSurname("Koncewicz");
		p1.setYob(1970);
		p1.setDescriptionDesease("Zapalenie okostnej");
		storageService.addPatient(p1);

		Patient p2 = new Patient();
		p2.setId(2L);
		p2.setName("Anette");
		p2.setSurname("Deux");
		p2.setYob(1979);
		p2.setDescriptionDesease("Rozszczep podniebienia");
		storageService.addPatient(p2);
	}

	public void updatePatientTable() {

		dentistContainer.removeAllItems();
		dentistContainer.addAll(storageService.getAllDentists());
		
		patientContainer.removeAllItems();
		patientContainer.addAll(storageService.getAllPatients());
	}
}