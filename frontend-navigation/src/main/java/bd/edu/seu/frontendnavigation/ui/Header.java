package bd.edu.seu.frontendnavigation.ui;

import bd.edu.seu.frontendnavigation.model.LoginToken;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import javax.servlet.http.HttpSession;

public class Header extends HorizontalLayout {
    private LoginToken loginToken;

    public Header(HttpSession httpSession) {
        super();

        loginToken = (LoginToken) httpSession.getAttribute("user");
        if (loginToken == null)
            loginToken = new LoginToken();

        Label fullnameLabel = new Label(loginToken.getFullname());
        Button logoutButton = new Button("Logout", VaadinIcon.EXIT.create());

        logoutButton.addClickListener(event -> logoutButton.getUI().ifPresent(ui -> ui.navigate("login")));

        add(fullnameLabel, logoutButton);
    }

    public LoginToken getLoginToken() {
        return loginToken;
    }
}
