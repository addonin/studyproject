package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.User;
import com.brainacad.studyproject.service.UserService;
import com.brainacad.studyproject.service.impl.UserServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.function.Consumer;

import static com.brainacad.studyproject.gui.view.View.USERS;

/**
 * Created by User on 11/10/2016.
 */
public class UsersView extends RefreshableView {

    private JTable usersTable;
    private DefaultTableModel tableModel;

    private UserService userService;

    public UsersView() {
        userService = new UserServiceImpl();
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        String col[] = {"ID","USERNAME", "EDIT"};
        tableModel = new DefaultTableModel(col, 0);
        usersTable = new JTable(tableModel);
        //usersTable.setDefaultRenderer(User.class, new UsersTableCellRenderer());
        content.add(usersTable);
    }

    @Override
    public View getName() {
        return USERS;
    }

    @Override
    public void refresh() {
        Collection<User> users = userService.getAllUsers();
        users.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                tableModel.addRow(UsersView.this.map(user));
            }
        });
    }

    public Object[] map(User user) {
        return new Object[] {user.getId(), user.getUsername(), user.getRole().toString()};
    }

}
