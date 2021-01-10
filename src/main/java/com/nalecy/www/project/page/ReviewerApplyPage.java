package com.nalecy.www.project.page;

import com.nalecy.www.project.common.DataProvider;
import com.nalecy.www.project.constant.ApplyStatusConstant;
import com.nalecy.www.project.entity.po.Apply;
import com.nalecy.www.project.entity.po.Project;
import com.nalecy.www.project.entity.vo.ApplyQueryVo;
import com.nalecy.www.project.entity.vo.ApplyVo;
import com.nalecy.www.project.entity.vo.ProjectQueryVo;
import com.nalecy.www.project.entity.vo.ProjectVo;
import com.nalecy.www.project.service.ApplyService;
import com.nalecy.www.project.service.ProjectService;
import com.nalecy.www.project.util.PromptAlert;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.ReviewerDetailView;
import com.nalecy.www.project.view.ReviewerMainView;
import com.nalecy.www.project.view.ReviewerProjectView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author nalecy
 * @since 2021/1/10
 */
@FXMLController
public class ReviewerApplyPage implements Initializable {


    public TableView<ApplyVo> passTable;
    public TableView<ApplyVo> pendingTable;

    @Resource
    public ApplyService applyService;

    @Resource
    public ProjectService projectService;

    public void onClickReview(ActionEvent actionEvent) {
        try {
            ObservableList<ApplyVo> selectedProject = passTable.getSelectionModel().getSelectedItems();
            if (selectedProject.size() > 0) {
                ApplyVo applyVo = selectedProject.get(0);
                Project project = projectService.getById(applyVo.getProjectId());
                DataProvider.INSTANCE.setChooseProject(project);
                DataProvider.INSTANCE.setChooseApply(applyVo);
                ViewSwitcher.getInstance().showFxml("/xml/reviewer_detail.fxml","项目审核",ReviewerDetailView.class);
            } else {
                PromptAlert.display("错误", "未选择项目");
            }
        } catch (IllegalArgumentException e) {
            PromptAlert.display("错误", e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (applyService != null) {
            fetchData();
        }
    }

    private void fetchData() {
        ApplyQueryVo query = new ApplyQueryVo().setUserIds(Collections.singletonList(DataProvider.INSTANCE.getCurUserId()));
        List<ApplyVo> records = applyService.selectApplyList(query).getRecords();
        ObservableList<ApplyVo> pending = FXCollections.observableArrayList();
        for (ApplyVo record : records) {
            if (record.getStatus().equals(ApplyStatusConstant.WAIT)) {
                pending.add(record);
            }
        }
        ObservableList<ApplyVo> pass = FXCollections.observableArrayList();
        for (ApplyVo record : records) {
            if (record.getStatus().equals(ApplyStatusConstant.PASS)) {
                pass.add(record);
            }
        }
        pendingTable.setItems(pending);
        passTable.setItems(pass);
    }

    public void onClickBack(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/reviewer_main.fxml", "项目评审员", ReviewerMainView.class);
    }

    public void onClickRefresh(ActionEvent actionEvent) {
        fetchData();
    }
}
