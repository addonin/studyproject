package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.User;
import com.brainacad.studyproject.service.UserService;
import com.brainacad.studyproject.service.impl.UserServiceImpl;

import javax.swing.*;

import static com.brainacad.studyproject.gui.view.View.EDIT_USER;

/**
 * Created by User on 11/12/2016.
 */
public class EditUserView extends RefreshableView {

    private UserService userService;

    private JLabel testLabel;

    public EditUserView() {
        userService = new UserServiceImpl();
        testLabel = new JLabel();
        content.add(testLabel);
    }

    @Override
    public View getName() {
        return EDIT_USER;
    }

    @Override
    public void refresh(Object... params) {
        User user = userService.getUserById((Integer) params[0]);
        testLabel.setText(user.getId() + " : " + user.getUsername());
    }

}
