package bd.edu.seu.frontendnavigation.ui;

import bd.edu.seu.frontendnavigation.model.LoginToken;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.servlet.http.HttpSession;

@Route("faculty")
public class FacultyView extends VerticalLayout {

    public FacultyView(HttpSession httpSession) {
        Header header = new Header(httpSession);
        header.addAttachListener(event -> {
            LoginToken loginToken = header.getLoginToken();
            if (!loginToken.getRole().equals("faculty")) {
                httpSession.removeAttribute("user");
                header.getUI().ifPresent(ui -> ui.navigate("login"));
            }
        });

        Div body = new Div();
        body.add(new Span("Faculty"));
        Footer footer = new Footer();

        add(header, body, footer);
    }
}
