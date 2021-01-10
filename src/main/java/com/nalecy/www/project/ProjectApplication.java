package com.nalecy.www.project;

import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.MainLoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.GUIState;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(ProjectApplication.class, MainLoginView.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GUIState.setStage(stage);
        super.start(stage);
    }
}
