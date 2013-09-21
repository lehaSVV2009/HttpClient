package com.kadet.http.view;

import com.kadet.http.controller.MethodSelectionListener;
import com.kadet.http.controller.SendButtonListener;

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

    private final static String SEND_REQUEST_TEXT = "Send Request";
    private final static String POST_PARAMETER_TEXT = "POST Parameter: ";
    private final static String URL_TEXT = "Url: ";

    private final static java.util.List<String> methods = new ArrayList<String>();
    private final static java.util.List<String> urls = new ArrayList<String>();


    static {
        methods.add("GET");
        methods.add("POST");
        methods.add("HEAD");
    }

    static {
        urls.add("http://www.spaces.ru/");
        urls.add("http://spaces.ru/search/?sid=2201168027516682");
    }

    private JLabel urlLabel;
    private JComboBox urlsComboBox;
    private JLabel postParameterLabel;
    private JTextField postParameterTextField;
    private JComboBox methodsComboBox;
    private JButton sendButton;
    private JEditorPane htmlArea;
    private JTextArea loggerArea;


    public MainPanel () {
        initialize();
        adjustAllElements();
        addAllElements();
    }

    private void initialize () {

    }



    private void adjustAllElements () {
        adjustUrlsField();
        adjustPostParameterField();
        adjustMethodsComboBox();
        adjustHTMLArea();
        adjustLoggerArea();
        adjustSendButton();
    }

    private void adjustUrlsField() {
        urlLabel = new JLabel(URL_TEXT);
        urlsComboBox = new JComboBox(urls.toArray());
    }

    private void adjustPostParameterField () {
        postParameterLabel = new JLabel(POST_PARAMETER_TEXT);
        postParameterTextField = new JTextField("lopez", 25);
        postParameterTextField.setEnabled(false);
    }


    private void adjustMethodsComboBox () {
        methodsComboBox = new JComboBox(methods.toArray());
        methodsComboBox.addItemListener(new MethodSelectionListener(postParameterTextField));
    }

    private void adjustSendButton() {
        sendButton = new JButton(SEND_REQUEST_TEXT);
        sendButton.addActionListener(new SendButtonListener(methodsComboBox, this));
    }

    private void adjustHTMLArea() {
        htmlArea = new JEditorPane();
        htmlArea.setEditable(false);
        htmlArea.setText("<html><body>Html будет здесь</body></html>");
    }

    private void adjustLoggerArea () {
        loggerArea = new JTextArea();
        loggerArea.setEditable(false);
        loggerArea.setText("Logger\n");
        loggerArea.setBorder(new EtchedBorder());
    }

    private void addAllElements () {
        setLayout(new BorderLayout());
        JScrollPane loggerScrollPane = new JScrollPane(loggerArea);
        loggerScrollPane.setPreferredSize(new Dimension(400, 800));
        add(loggerScrollPane, BorderLayout.EAST);
        add(createBrowserPanel(), BorderLayout.CENTER);
    }

    private JPanel createBrowserPanel () {
        JPanel browserPanel = new JPanel();
        browserPanel.setLayout(new BorderLayout());
        browserPanel.add(createDataInputPanel(), BorderLayout.NORTH);
        browserPanel.add(new JScrollPane(htmlArea), BorderLayout.CENTER);
        return browserPanel;
    }

    private JPanel createDataInputPanel () {
        JPanel dataInputPanel = new JPanel();
        dataInputPanel.add(urlLabel);
        dataInputPanel.add(urlsComboBox);
        dataInputPanel.add(postParameterLabel);
        dataInputPanel.add(postParameterTextField);
        dataInputPanel.add(methodsComboBox);
        dataInputPanel.add(sendButton);
        dataInputPanel.setPreferredSize(new Dimension(500, 100));
        return dataInputPanel;
    }

    public JEditorPane getHtmlArea() {
        return htmlArea;
    }

    public JTextArea getLoggerArea() {
        return loggerArea;
    }

    public JTextField getPostParameterTextField() {
        return postParameterTextField;
    }

    public JComboBox getUrlsComboBox() {
        return urlsComboBox;
    }
}
