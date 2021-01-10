package com.nalecy.www.project.page;

import com.nalecy.www.project.util.ViewSwitcher;
import com.nalecy.www.project.view.ReviewerApplyView;
import com.nalecy.www.project.view.ReviewerProjectView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;

/**
 * @author nalecy
 * @since 2021/1/10
 */
@FXMLController
public class ReviewerMainPage {

    public void onClickApply(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/reviewer_apply.fxml", "项目评审员", ReviewerApplyView.class);
    }

    public void onClickProject(ActionEvent actionEvent) {
        ViewSwitcher.getInstance().showFxml("/xml/reviewer_project.fxml", "项目评审员", ReviewerProjectView.class);
    }
}
