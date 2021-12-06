/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import forms.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import services.FileService;

/**
 *
 * @author Khoi Nguyen
 */
public class FileController {
    
    private final FileService fileService = new FileService(new JFileChooser());

    private MainForm mainForm;
    
    public FileController(MainForm mainForm) {
        this.mainForm = mainForm;
    }
    
    public void initialize() {
        doOpenFile(mainForm);
        doNewFile(mainForm);
        checkDraftSaved(mainForm);
    }
    
    public void doOpenFile(MainForm mainForm) {
        mainForm.getOpenFileMenuItem().addActionListener(event -> {
            fileService.loadFileChooser();
            fileService.openFile(mainForm, event);
        });
    }
    
    public void doNewFile(MainForm mainForm) {
        mainForm.getNewFileMenuItem().addActionListener(event -> {
            fileService.clearScreen(mainForm, event);
        });
    }
    
    public void checkDraftSaved(MainForm mainForm) {
        mainForm.getTextArea().addCaretListener(event -> {
            fileService.saveDraftState(mainForm, event);
        });
    }
    
}
