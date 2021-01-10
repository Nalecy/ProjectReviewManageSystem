package com.nalecy.www.project.page;

import com.nalecy.www.project.util.ViewSwitcher;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    public void onClickRegister(ActionEvent actionEvent){
        ViewSwitcher.getInstance().showFxml("/xml/register.fxml","");
    }

}
