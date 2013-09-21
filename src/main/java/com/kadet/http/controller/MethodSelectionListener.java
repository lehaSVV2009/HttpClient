package com.kadet.http.controller;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created with IntelliJ IDEA.
 * User: Кадет
 * Date: 21.09.13
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */
public class MethodSelectionListener implements ItemListener {

    private JTextField postParameterTextField;

    public MethodSelectionListener(JTextField postParameterTextField) {
        this.postParameterTextField = postParameterTextField;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            String item = (String) event.getItem();
            if ("POST".equals(item)) {
                postParameterTextField.setEnabled(true);
            } else {
                postParameterTextField.setEnabled(false);
            }
        }
    }
}
