package com.nalecy.www.project.page;

import com.nalecy.www.project.ProjectApplication;
import com.nalecy.www.project.constant.RoleConstant;
import com.nalecy.www.project.entity.po.User;
import com.nalecy.www.project.service.RoleService;
import com.nalecy.www.project.service.UserRoleService;
import com.nalecy.www.project.service.UserService;
import com.nalecy.www.project.service.impl.UserRoleServiceImpl;
import com.nalecy.www.project.service.impl.UserServiceImpl;
import com.nalecy.www.project.util.PromptAlert;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.MainLoginView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.nalecy.www.project.Constants.*;

/**
 * @author nalecy
 * @since 2021/1/9
 */
@FXMLController
public class RegisterPage implements Initializable {

    @FXML
    public ChoiceBox<String> positionBox;

    @FXML
    public TextField usernameField;

    @FXML
    public TextField phoneField;

    @FXML
    public TextField emailField;

    @FXML
    public ChoiceBox<String> sexBox;

    @FXML
    public PasswordField passwordField;

    @FXML
    public PasswordField passwordConfirmField;

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @FXML
    public void onClickRegister(ActionEvent actionEvent) {
        try {
            User user = new User();
            user.setName(usernameField.getText());
            user.setSex(sexBox.getValue().equals("男") ? 1 : 0);
            user.setPassword(passwordField.getText());
            user.setEmail(emailField.getText());
            user.setPhone(phoneField.getText());
            userService.signUp(user);

            int id = userService.login(usernameField.getText(), passwordField.getText()).getUserId();
            switch (positionBox.getValue()) {
                case PROJECT_MANAGER_VALUE:
                    userRoleService.updateRole(id, RoleConstant.USER);
                    break;
                case PROJECT_REVIEWER_VALUE:
                    userRoleService.updateRole(id, RoleConstant.JUDGE);
                    break;
                case PROJECT_SYS_MANAGER_VALUE:
                    userRoleService.updateRole(id, RoleConstant.SYSTEM_MANAGER);
                    break;
            }
            PromptAlert.display("成功","注册成功");
            ViewSwitcher.getInstance().showFxml("/xml/login.fxml","登录",MainLoginView.class);
        }catch (IllegalArgumentException e){
            PromptAlert.display("错误",e.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        positionBox.setItems(FXCollections.observableArrayList(Arrays.asList(PROJECT_MANAGER_VALUE, PROJECT_REVIEWER_VALUE, PROJECT_SYS_MANAGER_VALUE)));
        sexBox.setItems(FXCollections.observableArrayList(Arrays.asList("男", "女")));
        sexBox.setValue("男");
    }

    public void onClickBack(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/login.fxml","登录",MainLoginView.class);
    }
}

