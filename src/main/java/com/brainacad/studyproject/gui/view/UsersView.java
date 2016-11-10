package com.brainacad.studyproject.gui.view;

import static com.brainacad.studyproject.gui.view.View.USERS;

/**
 * Created by User on 11/10/2016.
 */
public class UsersView extends RefreshableView {

    @Override
    public View getName() {
        return USERS;
    }

    @Override
    public void refresh() {
        //TODO: get actual state
    }
}
