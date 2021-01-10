package com.nalecy.www.project.page;

import com.nalecy.www.project.common.UserProvider;
import com.nalecy.www.project.entity.po.Project;
import com.nalecy.www.project.service.ProjectService;
import com.nalecy.www.project.util.PromptAlert;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.ProjectMasterMainView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author nalecy
 * @since 2021/1/10
 */
@FXMLController
public class ProjectMasterApplyPage implements Initializable {

    @FXML
    public TextField projectNameField;

    @FXML
    public TextField projectMemberField;

    @FXML
    public TextArea projectAboutArea;

    @FXML
    public TextField projectKnowField;

    @FXML
    public TextField projectTeacherField;

    @FXML
    public Button submitBtn;

    @Resource
    public ProjectService projectService;

    public void onClickSubmit(ActionEvent actionEvent) {
        try {
            Project project = new Project();
            project.setUserId(UserProvider.INSTANCE.getCurUserId());
            project.setIntellectual(projectKnowField.getText());
            project.setIntroduction(projectAboutArea.getText());
            project.setStatus(0);
            project.setTeacher(projectTeacherField.getText());
            project.setMember(projectMemberField.getText());
            project.setProjectName(projectNameField.getText());
            project.setType(0);
            projectService.insertProject(project);
            PromptAlert.display("成功","请等待审批");
            ViewSwitcher.getInstance().showFxml("/xml/project_master_main.fxml", "项目负责人", ProjectMasterMainView.class);
        }catch (IllegalArgumentException e){
            PromptAlert.display("错误",e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
