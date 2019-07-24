package bd.edu.seu.vaadindemo.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class MainView extends VerticalLayout {
    public MainView() {

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        TextField number1Field = new TextField("Number 1", "Enter a number");
        TextField number2Field = new TextField("Number 2", "Enter a number");
        TextField resultField = new TextField("Result", "Result will be displayed here");
        Button addButton = new Button("Add");
        addButton.addClickListener(event -> {
            int number1 = Integer.parseInt(number1Field.getValue());
            int number2 = Integer.parseInt(number2Field.getValue());
            int result = number1 + number2;
            resultField.setValue("" + result);
        });
        horizontalLayout.add(number1Field, number2Field, addButton, resultField);
        add(horizontalLayout);

        Button button = new Button();
        button.setText("Click me");
        button.addClickListener(event -> Notification.show("Hello World!"));

        add(button);
    }
}
