package bd.edu.seu.frontendnavigation.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Header extends HorizontalLayout {
    public Header() {
        super();

        Label fullnameLabel = new Label("Fullname");
        Button logoutButton = new Button("Logout", VaadinIcon.EXIT.create());

        logoutButton.addClickListener(event -> logoutButton.getUI().ifPresent(ui -> ui.navigate("login")));

        add(fullnameLabel, logoutButton);
    }
}
