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

    private final static java.util.List<String> methods = new ArrayList<String>();

    static {
        methods.add("GET");
        methods.add("POST");
        methods.add("HEAD");
    }

    private JLabel headLabel;
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
        adjustPostParameterField();
        adjustMethodsComboBox();
        adjustHTMLArea();
        adjustLoggerArea();
        adjustSendButton();
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
//        htmlArea.setContentType("text/html");
        htmlArea.setEditable(false);
        htmlArea.setText("<html><body>Html здесь</body></html>");
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
        repaint();
        revalidate();
    }

    private JPanel createBrowserPanel () {
        JPanel browserPanel = new JPanel();
        browserPanel.setLayout(new BoxLayout(browserPanel, BoxLayout.Y_AXIS));
        browserPanel.add(createDataInputPanel());
        browserPanel.add(new JScrollPane(htmlArea));
        return browserPanel;
    }

    private JPanel createDataInputPanel () {
        JPanel dataInputPanel = new JPanel();
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
}
