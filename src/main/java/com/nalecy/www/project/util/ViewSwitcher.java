package com.nalecy.www.project.util;

import com.nalecy.www.project.ProjectApplication;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.GUIState;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author nalecy
 * @since 2020/11/1
 */
public class ViewSwitcher {
    private ViewSwitcher() {
    }

    private static final ViewSwitcher instance = new ViewSwitcher();

    public static ViewSwitcher getInstance() {
        return instance;
    }

    public void showFxml(String fxml,String title, Class<? extends AbstractFxmlView> newView) {
        try {
            Parent other = new FXMLLoader(getClass().getResource(fxml)).load();
            Stage stage = GUIState.getStage();
            stage.setTitle(title);
            stage.setScene(new Scene(other));
            ProjectApplication.showView(newView);
        } catch (IOException e) {
            System.out.println(fxml + "加载失败");
            e.printStackTrace();
        }
    }

}
