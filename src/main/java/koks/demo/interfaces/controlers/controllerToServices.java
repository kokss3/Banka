package koks.demo.interfaces.controlers;

import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

@Repository
public interface controllerToServices {

    String login(ModelMap model);
    String runStart();
}
