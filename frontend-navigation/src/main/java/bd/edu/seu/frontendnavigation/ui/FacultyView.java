package bd.edu.seu.frontendnavigation.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("faculty")
public class FacultyView extends VerticalLayout {

    public FacultyView() {
        Header header = new Header();

        Div body = new Div();
        body.add(new Span("Faculty"));
        Footer footer = new Footer();

        add(header, body, footer);
    }
}
