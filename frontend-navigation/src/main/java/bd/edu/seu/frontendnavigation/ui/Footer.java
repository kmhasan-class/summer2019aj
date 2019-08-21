package bd.edu.seu.frontendnavigation.ui;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Footer extends HorizontalLayout {
    public Footer() {
        super();

        add(new Span("Developed by the awesome people from the AJ course!"));
    }
}
