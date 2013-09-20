package com.kadet.http.view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 20.09.13
 * Time: 2:07
 * To change this template use File | Settings | File Templates.
 */
public class MainPanel extends JPanel {

    private final static String sendRequestText = "Send Request";

    private final static java.util.List<String> methods = new ArrayList<String>();

    static {
        methods.add("GET");
        methods.add("POST");
        methods.add("HEAD");
    }

    private JComboBox methodsComboBox;
    private JButton sendButton;
    private JTextArea htmlArea;
    private JTextArea loggerArea;


    public MainPanel () {
        initialize();
        adjustAllElements();
        addAllElements();
    }

    private void initialize () {

    }

    private void adjustAllElements () {
        adjustMethodsComboBox();
        adjustSendButton();
        adjustHTMLArea();
        adjustLoggerArea();
    }

    private void adjustMethodsComboBox () {
        methodsComboBox = new JComboBox(methods.toArray());
    }

    private void adjustSendButton() {
        sendButton = new JButton(sendRequestText);
    }

    private void adjustHTMLArea() {
        htmlArea = new JTextArea();
        htmlArea.setText("html text Area");
        htmlArea.setEditable(false);
    }

    private void adjustLoggerArea () {
        loggerArea = new JTextArea();
        loggerArea.setPreferredSize(new Dimension(400, 800));
        loggerArea.setEditable(false);
        loggerArea.setText("logger");
        loggerArea.setBorder(new EtchedBorder());
    }

    private void addAllElements () {
        setLayout(new BorderLayout());
        add(loggerArea, BorderLayout.EAST);
        add(createBrowserPanel(), BorderLayout.CENTER);
    }

    private JPanel createBrowserPanel () {
        JPanel browserPanel = new JPanel();
        browserPanel.setLayout(new BoxLayout(browserPanel, BoxLayout.Y_AXIS));
        browserPanel.add(createDataInputPanel());
        browserPanel.add(htmlArea);
        return browserPanel;
    }

    private JPanel createDataInputPanel () {
        JPanel dataInputPanel = new JPanel();
        dataInputPanel.add(methodsComboBox);
        dataInputPanel.add(sendButton);
        return dataInputPanel;
    }

}
