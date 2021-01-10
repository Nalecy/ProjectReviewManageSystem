package com.nalecy.www.project.page;

import com.nalecy.www.project.common.DataProvider;
import com.nalecy.www.project.entity.po.Project;
import com.nalecy.www.project.entity.vo.ProjectVo;
import com.nalecy.www.project.service.ProjectService;
import com.nalecy.www.project.util.PromptAlert;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.ProjectMasterMainView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author nalecy
 * @since 2021/1/11
 */
@FXMLController
public class ProjectMasterUpdatePage implements Initializable {

    public TextField projectNameField;
    public TextField projectMemberField;
    public TextArea projectAboutArea;
    public TextField projectKnowField;
    public TextField projectTeacherField;

    @Resource
    public ProjectService projectService;

    public void onClickUpdate(ActionEvent actionEvent) {
        try {
            ProjectVo project = DataProvider.INSTANCE.getChooseProject();
            projectService.updateProject(new Project()
                    .setId(project.getId())
                    .setProjectName(projectNameField.getText())
                    .setMember(projectMemberField.getText())
                    .setIntroduction(projectAboutArea.getText())
                    .setIntellectual(projectKnowField.getText())
                    .setTeacher(projectTeacherField.getText()));
            PromptAlert.display("成功","操作成功");
            ViewSwitcher.getInstance().showFxml("/xml/project_master_main.fxml", "项目负责人", ProjectMasterMainView.class);
        } catch (IllegalArgumentException e) {
            PromptAlert.display("错误", e.getMessage());
        }
    }

    public void onClickCancel(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/project_master_main.fxml", "项目负责人", ProjectMasterMainView.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProjectVo project = DataProvider.INSTANCE.getChooseProject();
        projectNameField.setText(project.getProjectName());
        projectMemberField.setText(project.getMember());
        projectAboutArea.setText(project.getIntroduction());
        projectKnowField.setText(project.getIntellectual());
        projectTeacherField.setText(project.getTeacher());
    }
}
