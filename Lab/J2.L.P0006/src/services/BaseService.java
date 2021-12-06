/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Khoi Nguyen
 */
public class BaseService {
    
    public void callInternal(String request) {
        
    }
    
    public void showErrorDialog(JFrame form, String message) {
        showMessageDialog(form, message, JOptionPane.ERROR_MESSAGE);
    }
    
    public void showInforDialog(JFrame form, String message) {
        showMessageDialog(form, message, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void showWarnningDialog(JFrame form, String message, String title) {
        showMessageDialog(form, message, JOptionPane.WARNING_MESSAGE);
    }
    
    public int showConfirmDialog(JFrame form, String message) {
        return JOptionPane.showConfirmDialog(form, message, form.getTitle(), JOptionPane.YES_NO_CANCEL_OPTION);
    }
    
    private void showMessageDialog(JFrame form, String message, int messageType) {
        JOptionPane.showMessageDialog(form, message, form.getTitle(), messageType);
    }
    
}
