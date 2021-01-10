package com.nalecy.www.project.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.common.DataProvider;
import com.nalecy.www.project.entity.vo.ProjectQueryVo;
import com.nalecy.www.project.entity.vo.ProjectVo;
import com.nalecy.www.project.service.ProjectService;
import com.nalecy.www.project.util.PromptAlert;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.ProjectMasterApplyView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * @author nalecy
 * @since 2021/1/10
 */
@FXMLController
public class ProjectMasterMainPage implements Initializable {

    @FXML
    public TableView<ProjectVo> allProjectTable;

    @FXML
    public TableView<ProjectVo> pendingProjectTable;

    @FXML
    public Button submitBtn;

    @FXML
    public Button helpBtn;

    @FXML
    public Button refreshBtn;

    @Resource
    public ProjectService projectService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (projectService != null) {
            fetchData();
        }
    }

    private void fetchData(){
        ProjectQueryVo query = new ProjectQueryVo();
        query.setSize(100);
        query.setUserIds(Collections.singletonList(DataProvider.INSTANCE.getCurUserId()));
        Page<ProjectVo> projectVoPage = projectService.selectProjectList(query);
        ObservableList<ProjectVo> all = FXCollections.observableArrayList();
        all.addAll(projectVoPage.getRecords());
        allProjectTable.setItems(all);

        ObservableList<ProjectVo> pending = FXCollections.observableArrayList();
        for (ProjectVo record : projectVoPage.getRecords()) {
            if (record.getStatus().equals(0)){
                pending.add(record);
            }
        }
        pendingProjectTable.setItems(pending);
    }


    public void onClickSubmit(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/project_master_apply.fxml","申请", ProjectMasterApplyView.class);
    }

    public void onClickHelp(ActionEvent actionEvent) {
        PromptAlert.display("项目填报指南","这里是项目填报指南");
    }

    public void onClickRefresh(ActionEvent actionEvent) {
        fetchData();
    }
}
