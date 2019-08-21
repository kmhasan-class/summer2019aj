package bd.edu.seu.frontendnavigation.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends Dialog {

    public LoginView() {
        super();

        Image image = new Image();
        image.setSrc("https://seu.edu.bd/images/logo_1.png");

        TextField usernameField = new TextField("Username", "Your 13 digit ID");
        PasswordField passwordField = new PasswordField("Password", "Your password");
        Button loginButton = new Button("Login");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        loginButton.addClickShortcut(Key.ENTER);
        Label statusLabel = new Label();

        loginButton.addClickListener(event -> {
            loginButton.getUI().ifPresent(ui -> ui.navigate("faculty"));
        });

        FormLayout formLayout = new FormLayout();
        formLayout.add(image, usernameField, passwordField, loginButton, statusLabel);
        add(formLayout);

        setWidth("200px");

        open();
    }
}
