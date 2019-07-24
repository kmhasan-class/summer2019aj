package bd.edu.seu.vaadindemo.ui;

import bd.edu.seu.vaadindemo.model.Student;
import bd.edu.seu.vaadindemo.repository.StudentRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route("student")
public class StudentView extends VerticalLayout {
    private StudentRepository studentRepository;

    public StudentView(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

        TextField idField = new TextField("Student ID", "13 digit ID");
        TextField nameField = new TextField("Student Name", "Full name");
        DatePicker datePicker = new DatePicker();
        Button submitButton = new Button("Submit");

        FormLayout formLayout = new FormLayout();
        formLayout.add(idField, nameField, datePicker, submitButton);

        Grid<Student> studentGrid = new Grid<>(Student.class);
        studentGrid.setItems(studentRepository.findAll());

        add(formLayout, studentGrid);

        submitButton.addClickListener(event -> {
            long id = Long.parseLong(idField.getValue());
            String name = nameField.getValue();
            LocalDate dateOfBirth = datePicker.getValue();

            Student student = new Student(id, name, dateOfBirth);

            Student savedStudent = studentRepository.save(student);

            studentGrid.setItems(studentRepository.findAll());

            Notification.show("Saved " + savedStudent.getName());
        });

        studentGrid.addItemClickListener(event -> {
            Student selectedStudent = event.getItem();
            // TODO do this with Binder
            // TODO in class: add support for insert/update
            idField.setValue("" + selectedStudent.getId());
            nameField.setValue("" + selectedStudent.getName());
            datePicker.setValue(selectedStudent.getDob());
        });
    }
}
