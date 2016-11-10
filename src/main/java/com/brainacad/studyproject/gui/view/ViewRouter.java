package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.gui.MainFrame;

import javax.swing.*;

import static com.brainacad.studyproject.gui.view.View.LOGIN;

/**
 * Created by User on 11/8/2016.
 */
public class ViewRouter {
    private static ViewRouter instance;
    private static ViewFactory viewFactory = ViewFactory.getInstance();
    private JFrame mainFrame;
    private ViewRouter() {
        mainFrame = new MainFrame();
        mainFrame.setContentPane(viewFactory.getView(LOGIN).getContent());
        mainFrame.setVisible(true);
    }
    public static ViewRouter getInstance() {
        if (instance == null) {
            instance = new ViewRouter();
        }
        return instance;
    }
    public void switchView(View prev, View next) {
        RefreshableView prevView = viewFactory.getView(prev);
        RefreshableView nextView = viewFactory.getView(next);
        nextView.refresh();
        mainFrame.setContentPane(nextView.getContent());
        prevView.getContent().setVisible(false);
        nextView.getContent().setVisible(true);
        mainFrame.setVisible(true);
    }

}
