package bd.edu.seu.vaadindemo.ui;

import bd.edu.seu.vaadindemo.model.Student;
import bd.edu.seu.vaadindemo.service.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Route("student")
//@Theme(value = Lumo.class)
public class StudentView extends VerticalLayout {
    private StudentService studentService;
    private Binder<Student> studentBinder;
    private Grid<Student> studentGrid;

    public StudentView(StudentService studentService) {
        this.studentService = studentService;

        studentBinder = new Binder<>();

        TextField idField = new TextField("Student ID", "4 digit ID");
        TextField nameField = new TextField("Student Name", "Full name");
        DatePicker datePicker = new DatePicker("Date of Birth");
        Button submitButton = new Button("Submit");

        FormLayout formLayout = new FormLayout();
        formLayout.add(idField, nameField, datePicker, submitButton);

        studentBinder
                .forField(idField)
                .asRequired()
                .withValidator(id -> id.length() == 4, "IDs must be 4 digits long")
                .withConverter(new StringToLongConverter("ID must be a number"))
                .withValidator(id -> id > 999, "ID must be 4 digit number")
                .bind(student -> student.getId(), (student, id) -> student.setId(id));
//                .bind(Student::getId, Student::setId);
//        studentBinder.bind(nameField, Student::getName, Student::setName);
        studentBinder
                .forField(nameField)
                .asRequired()
                .withValidator(name -> name.length() >= 3, "Names should be at least 3 letters long")
                .withValidator(name -> name.length() <= 10, "Names cannot be more than 10 letters long")
                // TODO homework: ensure that the names do not have any special characters or digits
                .withValidator(name -> !name.contains("_"), "Names cannot have underscores")
                .bind(Student::getName, Student::setName);
        studentBinder
                .forField(datePicker)
                .asRequired()
                .withValidator(dob -> DAYS.between(dob, LocalDate.now()) > 16 * 365, "Students should be at least 16 years old!")
                .bind(Student::getDob, Student::setDob);

        studentGrid = new Grid<>();
        studentGrid
                .addColumn(Student::getId)
                .setWidth("150px")
                .setFlexGrow(0)
                .setHeader("Student ID");
        studentGrid
                .addColumn(Student::getName)
                .setFlexGrow(1)
                .setHeader(getStyledHeader("Student Name"));
        studentGrid
                .addColumn(Student::getDob)
                .setFlexGrow(1)
                .setHeader(getStyledHeader("Date of Birth"));
        studentGrid
                .addComponentColumn(student -> getEditColumn(student))
                .setWidth("50px")
                .setFlexGrow(0);
        studentGrid
                .addComponentColumn(student -> getDeleteColumn(student))
                .setWidth("50px")
                .setFlexGrow(0);

        studentGrid.setItems(studentService.findAll());

        add(formLayout, studentGrid);

        submitButton.addClickListener(event -> {
            Student student = new Student();
            try {
                studentBinder.writeBean(student);
                Notification.show(student.toString());
                Student savedStudent = studentService.save(student);
                studentGrid.setItems(studentService.findAll());
                Notification.show("Saved " + savedStudent.getName());
            } catch (ValidationException e) {
                System.err.println("*Days " + DAYS.between(LocalDate.now(), student.getDob()));
                Notification.show(e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                Notification.show(e.getMessage());
            }
        });

//        studentGrid.addItemClickListener(event -> {
//            Student selectedStudent = event.getItem();
//            studentBinder.readBean(selectedStudent);
//        });
        // TODO task for myself: find out why the numbers are shown with thousand separators
    }

    private Component getDeleteColumn(Student student) {
        Button button = new Button();
        button.setIcon(VaadinIcon.FILE_REMOVE.create());
        button.getElement().setProperty("title", "This is a delete button!");
        button.addClickListener(event -> {
            studentService.delete(student);
            studentGrid.setItems(studentService.findAll());
        });
        return button;
    }

    private Component getEditColumn(Student student) {
        Button button = new Button();
        button.setIcon(VaadinIcon.EDIT.create());
        button.addClickListener(event -> studentBinder.readBean(student));
        return button;
    }

    private Component getStyledHeader(String text) {
        Span header = new Span(text);
        header
                .getStyle()
                .set("background-color", "red")
                .set("border-width", "3px");
        return header;
    }
}
