package bd.edu.seu.frontendnavigation.ui;

import bd.edu.seu.frontendnavigation.model.LoginToken;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.servlet.http.HttpSession;

@Route("student")
public class StudentView extends VerticalLayout {

    public StudentView(HttpSession httpSession) {
        Header header = new Header(httpSession);
        header.addAttachListener(event -> {
            LoginToken loginToken = header.getLoginToken();
            if (!loginToken.getRole().equals("student")) {
                httpSession.removeAttribute("user");
                header.getUI().ifPresent(ui -> ui.navigate("login"));
            }
        });

        Div body = new Div();
        body.add(new Span("Student"));
        Footer footer = new Footer();

        add(header, body, footer);
    }
}
