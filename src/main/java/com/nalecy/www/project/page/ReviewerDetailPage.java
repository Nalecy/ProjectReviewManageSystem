package com.nalecy.www.project.page;

import com.nalecy.www.project.common.DataProvider;
import com.nalecy.www.project.constant.ProjectStatusConstant;
import com.nalecy.www.project.entity.po.Project;
import com.nalecy.www.project.entity.po.Review;
import com.nalecy.www.project.entity.vo.ProjectVo;
import com.nalecy.www.project.service.ReviewService;
import com.nalecy.www.project.util.PromptAlert;
import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.ReviewerApplyView;
import com.nalecy.www.project.view.ReviewerProjectView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author nalecy
 * @since 2021/1/10
 */
@FXMLController
public class ReviewerDetailPage implements Initializable {

    @FXML
    public ChoiceBox<String> statusBox;

    @FXML
    public Button passBtn;

    @FXML
    public Label memberLabel;

    @FXML
    public Label nameLabel;

    @FXML
    public Label aboutLabel;

    @FXML
    public Label teacherLabel;

    @FXML
    public Label knowLabel;

    @FXML
    public Label timeLabel;

    @FXML
    public Button nonPassBtn;

    @FXML
    public Button backBtn;

    @Resource
    public ReviewService reviewService;

    public ProjectVo project;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        project = DataProvider.INSTANCE.getChooseProject();
        nameLabel.setText(project.getProjectName());
        memberLabel.setText(project.getMember());
        teacherLabel.setText(project.getTeacher());
        aboutLabel.setText(project.getIntroduction());
        knowLabel.setText(project.getIntellectual());
        timeLabel.setText(project.getCreateTime().toString());
    }

    @FXML
    public void onClickPass(ActionEvent actionEvent) {
        try {
            Review review = new Review();
            review.setApplyId(DataProvider.INSTANCE.getChooseApply().getId());
            reviewService.reviewProject(review, ProjectStatusConstant.PASS);
            PromptAlert.display("成功", "操作成功");
            ViewSwitcher.getInstance().showFxml("/xml/reviewer_apply.fxml", "项目评审员", ReviewerApplyView.class);
        } catch (IllegalArgumentException e) {
            PromptAlert.display("错误", e.getMessage());
        }
    }

    @FXML
    public void onClickNonPass(ActionEvent actionEvent) {
        try {
            Review review = new Review();
            review.setApplyId(DataProvider.INSTANCE.getChooseApply().getId());
            reviewService.reviewProject(review, ProjectStatusConstant.NOT_PASS);
            PromptAlert.display("成功", "操作成功");
            ViewSwitcher.getInstance().showFxml("/xml/reviewer_apply.fxml", "项目评审员", ReviewerApplyView.class);
        } catch (IllegalArgumentException e) {
            PromptAlert.display("错误", e.getMessage());
        }
    }

    @FXML
    public void onClickBack(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/reviewer_apply.fxml", "项目评审员", ReviewerApplyView.class);
    }
}
