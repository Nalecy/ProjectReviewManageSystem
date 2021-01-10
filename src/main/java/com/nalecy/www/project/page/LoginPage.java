package com.nalecy.www.project.page;

import com.nalecy.www.project.Constants;
import com.nalecy.www.project.common.DataProvider;
import com.nalecy.www.project.entity.vo.LoginVo;
import com.nalecy.www.project.service.UserService;
import com.nalecy.www.project.util.PromptAlert;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.ProjectMasterMainView;
import com.nalecy.www.project.view.RegisterView;
import com.nalecy.www.project.view.ReviewerMainView;
import com.nalecy.www.project.view.ReviewerProjectView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.annotation.Resource;

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

    @Resource
    public UserService userService;

    @FXML
    public void onClickLogin(ActionEvent actionEvent){
        try {
            LoginVo login = userService.login(usernameEdt.getText(), passwordEdt.getText());
            DataProvider.INSTANCE.setCurUserId(login.getUserId());
            if (login.getRole().equals(Constants.PROJECT_MANAGER_VALUE)){
                ViewSwitcher.getInstance().showFxml("/xml/project_master_main.fxml", "项目负责人", ProjectMasterMainView.class);
            }else if (login.getRole().equals(Constants.PROJECT_REVIEWER_VALUE)){
                ViewSwitcher.getInstance().showFxml("/xml/reviewer_main.fxml", "项目评审员", ReviewerMainView.class);
            }else if (login.getRole().equals(Constants.PROJECT_SYS_MANAGER_VALUE)){

            }
        }catch (IllegalArgumentException e){
            PromptAlert.display("错误",e.getMessage());
        }
    }

    @FXML
    public void onClickRegister(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/register.fxml", "注册",RegisterView.class);
    }

}
