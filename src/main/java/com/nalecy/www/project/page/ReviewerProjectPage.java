package com.nalecy.www.project.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.common.DataProvider;
import com.nalecy.www.project.constant.ProjectStatusConstant;
import com.nalecy.www.project.entity.po.Apply;
import com.nalecy.www.project.entity.vo.ProjectQueryVo;
import com.nalecy.www.project.entity.vo.ProjectVo;
import com.nalecy.www.project.service.ApplyService;
import com.nalecy.www.project.service.ProjectService;
import com.nalecy.www.project.util.PromptAlert;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.ReviewerMainView;
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
import java.util.ResourceBundle;

/**
 * @author nalecy
 * @since 2021/1/10
 */
@FXMLController
public class ReviewerProjectPage implements Initializable {
    @FXML
    public TableView<ProjectVo> pendingTable;

    @FXML
    public Button detailBtn;

    @FXML
    public Button refreshBtn;

    @Resource
    public ProjectService projectService;

    @Resource
    public ApplyService applyService;


    @FXML
    public void onClickDetail(ActionEvent actionEvent) {
        try {
            ObservableList<ProjectVo> selectedProject = pendingTable.getSelectionModel().getSelectedItems();
            if (selectedProject.size() > 0) {
                ProjectVo projectVo = selectedProject.get(0);
                applyService.apply(new Apply()
                        .setProjectId(projectVo.getId())
                        .setUserId(DataProvider.INSTANCE.getCurUserId())
                );
                PromptAlert.display("成功", "申请成功");
            } else {
                PromptAlert.display("错误", "未选择项目");
            }
        } catch (IllegalArgumentException e) {
            PromptAlert.display("错误", e.getMessage());
        }
    }

    private void fetchData() {
        ProjectQueryVo query = new ProjectQueryVo();
        query.setSize(100);
        query.setStatus(ProjectStatusConstant.WAIT);
        Page<ProjectVo> projectVoPage = projectService.selectProjectList(query);
        ObservableList<ProjectVo> all = FXCollections.observableArrayList();
        all.addAll(projectVoPage.getRecords());
        pendingTable.setItems(all);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (projectService != null) {
            fetchData();
        }
    }

    @FXML
    public void onClickRefresh(ActionEvent actionEvent) {
        fetchData();
    }

    public void onClickBack(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/reviewer_main.fxml", "项目评审员", ReviewerMainView.class);
    }
}
