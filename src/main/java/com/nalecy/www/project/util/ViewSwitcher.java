package com.nalecy.www.project.util;

import com.nalecy.www.project.ProjectApplication;
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


    private Stage curStage;

    public void setScene(Stage scene) {
        curStage = scene;
    }

    public void showFxml(String fxml,String title) {
        try {
            Parent other = new FXMLLoader(getClass().getResource(fxml)).load();
            curStage.setTitle(title);
            curStage.setScene(new Scene(other));
        } catch (IOException e) {
            System.out.println(fxml + "加载失败");
            e.printStackTrace();
        }
    }

}
