package com.kadet.http.view;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 20.09.13
 * Time: 2:07
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame extends JFrame {

    private MainPanel mainPanel;
    private final static String title = "Browser";

    public MainFrame () {
        this.mainPanel = new MainPanel();
        initialize();
    }

    public void initialize () {
        setContentPane(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle(title);
    }

}
