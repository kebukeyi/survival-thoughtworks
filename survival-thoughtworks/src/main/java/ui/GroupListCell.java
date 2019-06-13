package ui;

import bean.Group;
import com.jfoenix.controls.JFXListCell;

public class GroupListCell extends JFXListCell<Group> {

    @Override
    protected void updateItem(Group item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setText(item.getStart());
            setText(item.getEnd());
            setText(item.getSpeed());

        }
    }
}
