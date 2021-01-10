package com.nalecy.www.project.page;

import com.nalecy.www.project.common.DataProvider;
import com.nalecy.www.project.constant.ApplyStatusConstant;
import com.nalecy.www.project.entity.vo.ApplyQueryVo;
import com.nalecy.www.project.entity.vo.ApplyVo;
import com.nalecy.www.project.service.ApplyService;
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

    public void onClickReview(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(applyService != null){
            fetchData();
        }
    }

    private void fetchData() {
        ApplyQueryVo query = new ApplyQueryVo()
                .setUserIds(Collections.singletonList(DataProvider.INSTANCE.getCurUserId()));
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
}
