package com.nalecy.www.project;

import com.nalecy.www.project.view.MainPageView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(ProjectApplication.class, MainPageView.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }
}
