package com.nalecy.www.project.page;

import com.nalecy.www.project.common.DataProvider;
import com.nalecy.www.project.constant.ApplyStatusConstant;
import com.nalecy.www.project.entity.vo.ApplyQueryVo;
import com.nalecy.www.project.entity.vo.ApplyVo;
import com.nalecy.www.project.entity.vo.ProjectQueryVo;
import com.nalecy.www.project.entity.vo.ProjectVo;
import com.nalecy.www.project.service.ApplyService;
import com.nalecy.www.project.service.ProjectService;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.MainLoginView;
import com.nalecy.www.project.view.SystemMasterApplyView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author nalecy
 * @since 2021/1/10
 */
@FXMLController
public class SystemMasterMainPage implements Initializable {

    public TableView<ProjectVo> projectTable;

    public TableView<ApplyVo> applyTable;

    @Resource
    public ProjectService projectService;

    @Resource
    public ApplyService applyService;

    public void onClickApply(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/system_apply.fxml","系统管理员", SystemMasterApplyView.class);
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
        apply.addAll(records);

        List<ProjectVo> projectVos = projectService.selectProjectList(new ProjectQueryVo()).getRecords();
        ObservableList<ProjectVo> project = FXCollections.observableArrayList();
        project.addAll(projectVos);

        projectTable.setItems(project);
        applyTable.setItems(apply);
    }

    public void onClickBack(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/login.fxml","登录", MainLoginView.class);
    }

    public void onClickRefresh(ActionEvent actionEvent) {
        fetchData();
    }
}
