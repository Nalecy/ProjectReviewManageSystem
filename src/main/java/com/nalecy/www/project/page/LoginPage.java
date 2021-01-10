package com.nalecy.www.project.page;

import com.nalecy.www.project.ProjectApplication;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.RegisterView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author nalecy
 * @since 2021/1/9
 */
@FXMLController
public class LoginPage {

    @FXML
    public TextField usernameEdt;

    @FXML
    public PasswordField passwordEdt;

    @FXML
    public Button loginBtn;

    @FXML
    public Button registerBtn;

    @FXML
    public void onClickLogin(ActionEvent actionEvent){

    }

    @FXML
    public void onClickRegister(ActionEvent actionEvent) throws IOException {
        ViewSwitcher.getInstance().showFxml("/xml/register.fxml", "");
        ProjectApplication.showView(RegisterView.class);
    }

}
