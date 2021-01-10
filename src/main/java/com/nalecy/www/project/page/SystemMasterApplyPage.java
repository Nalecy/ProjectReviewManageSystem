package com.nalecy.www.project.page;

import com.nalecy.www.project.common.DataProvider;
import com.nalecy.www.project.constant.ApplyStatusConstant;
import com.nalecy.www.project.entity.po.Apply;
import com.nalecy.www.project.entity.vo.ApplyQueryVo;
import com.nalecy.www.project.entity.vo.ApplyVo;
import com.nalecy.www.project.entity.vo.ProjectVo;
import com.nalecy.www.project.service.ApplyService;
import com.nalecy.www.project.service.ProjectService;
import com.nalecy.www.project.util.PromptAlert;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.SystemMasterMainView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import javax.annotation.Resource;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author nalecy
 * @since 2021/1/10
 */
@FXMLController
public class SystemMasterApplyPage implements Initializable {

    public TableView<ApplyVo> pendingApply;

    @Resource
    public ProjectService projectService;

    @Resource
    public ApplyService applyService;

    public void onClickNonPass(ActionEvent actionEvent) {
        try {
            ObservableList<ApplyVo> selectedProject = pendingApply.getSelectionModel().getSelectedItems();
            if (selectedProject.size() > 0) {
                ApplyVo applyVo = selectedProject.get(0);
                applyService.updateApply(applyVo.getId(), ApplyStatusConstant.NOT_PASS);
                PromptAlert.display("成功", "操作成功");
                fetchData();
            } else {
                PromptAlert.display("错误", "未选择审核");
            }
        } catch (IllegalArgumentException e) {
            PromptAlert.display("错误", e.getMessage());
        }
    }

    public void onClickBack(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/system_master_main.fxml", "系统管理员", SystemMasterMainView.class);
    }

    public void onClickPass(ActionEvent actionEvent) {
        try {
            ObservableList<ApplyVo> selectedProject = pendingApply.getSelectionModel().getSelectedItems();
            if (selectedProject.size() > 0) {
                ApplyVo applyVo = selectedProject.get(0);
                applyService.updateApply(applyVo.getId(), ApplyStatusConstant.PASS);
                PromptAlert.display("成功", "操作成功");
                fetchData();
            } else {
                PromptAlert.display("错误", "未选择审核");
            }
        } catch (IllegalArgumentException e) {
            PromptAlert.display("错误", e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (projectService != null){
            fetchData();
        }
    }

    private void fetchData() {
        List<ApplyVo> records = applyService.selectApplyList(new ApplyQueryVo()).getRecords();
        ObservableList<ApplyVo> apply = FXCollections.observableArrayList();
        for (ApplyVo record : records) {
            if (record.getStatus().equals(ApplyStatusConstant.WAIT)) {
                apply.add(record);
            }
        }
        pendingApply.setItems(apply);
    }
}
