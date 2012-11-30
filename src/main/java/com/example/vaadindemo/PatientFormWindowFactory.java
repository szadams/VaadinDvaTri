package com.example.vaadindemo;

import java.util.ArrayList;
import java.util.List;

import com.example.vaadindemo.domain.Patient;
import com.example.vaadindemo.util.IntRangeValidator;
import com.example.vaadindemo.util.StringRangeValidator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PatientFormWindowFactory extends Form implements FormFieldFactory {

	private static final long serialVersionUID = 1L;

	List<String> visibleFields = new ArrayList<String>();

	Button addButton = new Button("Add");
	Button closeButton = new Button("Close");

	Window modalWindow;

	BeanItem<Patient> patientBeanItem;

	VaadinApp vaadinApp;

	public PatientFormWindowFactory(BeanItem<Patient> patientBeanItem,
			VaadinApp vaadinApp) {

		this.vaadinApp = vaadinApp;

		GridLayout gl = new GridLayout(2, 4);
		gl.setSpacing(true);

		setLayout(gl);

		this.patientBeanItem = patientBeanItem;

		visibleFields.add("name");
		visibleFields.add("surname");
		visibleFields.add("yob");
		visibleFields.add("descriptionDesease");

		setImmediate(true);
		setValidationVisibleOnCommit(true);

		// 1.
		setFormFieldFactory(this);
		// 2.
		setItemDataSource(patientBeanItem);
		// 3.
		setVisibleItemProperties(visibleFields);

		closeButton.addListener(ClickEvent.class, this, "closeWindowAction");
		addButton.addListener(ClickEvent.class, this, "addPatientAction");

		gl.addComponent(addButton, 0, 3);
		gl.addComponent(closeButton, 1, 3);
	}

	@Override
	protected void attachField(Object propertyId, Field field) {
		String property = (String) propertyId;

		GridLayout gl = (GridLayout) getLayout();

		if ("name".equals(property)) {
			gl.addComponent(field, 0, 0, 1, 0);

		} else if ("surname".equals(property)) {
			gl.addComponent(field, 0, 2, 1, 2);

		} else if ("yob".equals(property)) {
			gl.addComponent(field, 0, 1, 1, 1);
		} 
//		else if ("descriptionDesease".equals(property)) {
//			gl.addComponent(field, 0, 2, 2, 2); // //////////// ????????????
//		}
	}

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {

		String field = (String) propertyId;

		if ("name".equals(field)) {
			final ComboBox nameComboBox = new ComboBox();
			String[] names = new String[] { "Leon", "Krzystof", "Barbara",
					"Anette" };

			nameComboBox.setNewItemsAllowed(true);

			for (String name : names) {
				nameComboBox.addItem(name);
			}
			nameComboBox.setRequired(true);
			return nameComboBox;
		} else if ("surname".equals(field)) {
			TextField surnameTextField = new TextField("Nazwisko");
			surnameTextField.setRequired(true);
			int min = 2;
			int max = 100;
			surnameTextField.addValidator(new StringRangeValidator(min, max,
					"Tekst powinien być długości: od " + min + " do " + max));
			return surnameTextField;

		} else if ("yob".equals(field)) {
			TextField yobTextField = new TextField("Rok urodzin");
			yobTextField.setRequired(true);

			int min = 1900;
			int max = 2012;
			yobTextField.addValidator(new IntRangeValidator(min, max,
					"Wymagana wartość liczbowa z zakresu (" + min + ", " + max
							+ ")"));
			return yobTextField;
		} else if ("descriptionDesease".equals(field)) {
			TextField descriptionDeseaseTextField = new TextField(
					"Opis choroby");
			descriptionDeseaseTextField.setRequired(true);

			int min = 0;
			int max = 2000;
			descriptionDeseaseTextField.addValidator(new StringRangeValidator(
					min, max, "Wymagana długość tekstu od " + min + " do "
							+ max + "."));
			return descriptionDeseaseTextField;
		}
		return null;
	}

	public Window createWindow() {
		modalWindow = new Window();
		modalWindow.setModal(true);
		modalWindow.setClosable(false);
		modalWindow.addComponent(this);
		modalWindow.setCaption("Dodawanie pacjenta");

		((VerticalLayout) modalWindow.getContent()).setSizeUndefined();
		((VerticalLayout) modalWindow.getContent()).setMargin(true);
		((VerticalLayout) modalWindow.getContent()).setSpacing(true);

		return modalWindow;
	}

	public void closeWindowAction(ClickEvent event) {
		closeFormWindow();
		vaadinApp.updatePatientTable();
	}

	public void addPatientAction(ClickEvent event) {
		commit();
		Patient patient = patientBeanItem.getBean();
		vaadinApp.getStorageService().addPatient(patient);
		closeFormWindow();
		vaadinApp.updatePatientTable();
	}

	private void closeFormWindow() {
		Window mainWindow = modalWindow.getParent();
		mainWindow.removeWindow(modalWindow);
	}

}
