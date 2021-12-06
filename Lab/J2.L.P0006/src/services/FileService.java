/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import forms.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import static utils.Constants.FileConstant.*;
import static utils.Constants.MessageConstant.*;

/**
 * <strong>FileService</strong> provides set of service 
 * that user request to use.<br>
 * List of services:
 *  <li>{@link #loadFileChooser() loadFileChooser}</li>
 *  <li>{@link #openFile(java.awt.event.ActionEvent) openFile}</li>
 *  <li>{@link #saveAsFile() saveAsFile}</li>
 * 
 * @author Khoi Nguyen
 */
public class FileService extends BaseService {
    
    private JFileChooser fileChooser;

    public FileService(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }
    
    /**
     * Loads the FileChoose window
     */
    public void loadFileChooser() {
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    return file.getName().endsWith(TEXT_EXTENSION);
                }
            }

            @Override
            public String getDescription() {
                return TEXT_DESCRIPTION;
            }
        });
        
        // Set current directory
        fileChooser.setCurrentDirectory(new File(System.getProperty(CURRENT_DIRECTORY)));
    }

    /**
     * Helps user to open existed file.<br>
     * Use {@link #clearScreen(java.awt.event.ActionEvent) clearScreen} to empty text area.
     * @param mainForm
     * @param fileChooser
     * @param event to listen to
     */
    public void openFile(MainForm mainForm, ActionEvent event) {
        if (!confirmSave(mainForm));
        
        File fileToOpen = null;
        while (true) {
            fileChooser.showOpenDialog(mainForm);
            fileToOpen = fileChooser.getSelectedFile();
            
            if (!fileToOpen.exists()) {
                break;
            }
            
            showErrorDialog(mainForm, FILE_NOT_FOUND);
        }
        
        mainForm.setCurrentFile(fileToOpen);
        
        // Clean writing screen
        clearScreen(mainForm, event);
        
        loadFileIntoBody(mainForm);
        mainForm.setSaved(true);
    }
    
    /**
     * Helps to empty the current text area.
     * @param mainForm
     * @param event to listen to
     */
    public void clearScreen(MainForm mainForm, ActionEvent event) {
        if (confirmSave(mainForm)) {
            mainForm.getTextArea().setText(EMPTY);
        } else {
            saveAsFile(mainForm);
        }
    }
    
    /**
     * Implements Save As methods
     * @param mainForm
     * @param fileChooser
     */
    public void saveAsFile(MainForm mainForm) {
        File fileToSave = null;
        while (true) {
            fileChooser.showSaveDialog(mainForm);
            fileToSave = fileChooser.getSelectedFile();
            
            if (!fileToSave.exists()) {
                break;
            }
            
            int choice = showConfirmDialog(mainForm, SAVE_CONFIRMATION + fileToSave.getName());
            if (choice == JOptionPane.YES_OPTION) {
                break;
            }
        }
        
        try {
            File file = new File(fileToSave.getCanonicalPath());
            mainForm.setCurrentFile(file);
            mainForm.setBody(EMPTY);
            loadBodyIntoFile(mainForm);
            
        } catch (IOException e) {
            showErrorDialog(mainForm, e.getMessage());
        }
    }
    
    /**
     * Callback action to do before close the application.
     * @param event to listen to
     */
    public void doBeforeClose(MainForm mainForm, WindowEvent event) {
        boolean isSaved = mainForm.isSaved();
        if (!isSaved) {
            int choice = showConfirmDialog(mainForm, SAVE_CONFIRMATION);
            switch (choice) {
                // When user want to save before saving
                case JOptionPane.YES_OPTION:
                    chooseSaveOrLoad(mainForm);
                    break;
                // When user want to close immediately
                case JOptionPane.NO_OPTION:
                    System.exit(0);
                default:;
            }
        } else {
            System.exit(0);
        }
    }
    
    /**
     * Save current status of draft
     * @param mainForm
     * @param event to listen to
     */
    public void saveDraftState(MainForm mainForm, CaretEvent event) {
        if (!mainForm.getTextArea().getText().equals(mainForm.getBody())) {
            mainForm.setSaved(false);
        } else {
            mainForm.setSaved(true);
        }
    }
    
    
    /**
     * Loads context from file then write into body of text area
     */
    @SuppressWarnings("null")
    private void loadFileIntoBody(MainForm mainForm) {
        try (
                FileInputStream inputStream = new FileInputStream(mainForm.getCurrentFile().orElse(null));
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            ) {
            
            String lastSaved = EMPTY;
            while (lastSaved != null) {
                lastSaved = reader.readLine();
                if (lastSaved == null) {
                    break;
                }
                mainForm.getTextArea().append(lastSaved + LINE_BREAK);
            }
            
            // when no diff between old state and current state of selected file
            JTextArea currentArea = mainForm.getTextArea();
            
            mainForm.setBody(currentArea.getText());
            mainForm.setSaved(true);
            currentArea.setCaretPosition(0); // set pointer at the BOF
            
        } catch (IOException e) {
            showErrorDialog(mainForm, e.getMessage());
        }
    }
    
    /**
     * Loads context from body then write to file.
     */
    private void loadBodyIntoFile(MainForm mainForm) {
        
        // If current file is not existed then save as new file.
        if (mainForm.getCurrentFile().get() == null) {
            saveAsFile(mainForm);
            return;
        }
        
        // Otherwise, write the context into existed file.
        try (FileWriter writer = new FileWriter(mainForm.getCurrentFile().orElse(null))) {

            String currentBody = mainForm.getTextArea().getText();
            
            writer.write(currentBody);
            mainForm.setBody(currentBody);
        } catch (IOException e) {
            showErrorDialog(mainForm, e.getMessage());
        }
    }
    

    /**
     * To check if user confirm to save or not.
     * @return false if CANCEL_OPTION is chose.
     * Otherwise, return true.
     */
    private boolean confirmSave(MainForm mainForm) {
        boolean yesConfirmation = mainForm.isSaved();
        
        if (!yesConfirmation) {
            String message = EMPTY;
            if (mainForm.getCurrentFile() == null) {
                message = "Do you want to save your current changes?";
            } else {
                message = SAVE_CONFIRMATION + mainForm.getCurrentFile().orElse(null).getName();
            }
            
            int choice = showConfirmDialog(mainForm, message);
            
                if (choice == JOptionPane.YES_OPTION) {
                    chooseSaveOrLoad(mainForm);
                }

                if (choice == JOptionPane.CANCEL_OPTION) {
                    return false;
            }
        }
        return true;
    }
    
    /**
     * If there is no current file, then save as new file.
     * Otherwise, load context into existed file.
     */
    private void chooseSaveOrLoad(MainForm mainForm) {
        if (mainForm.getCurrentFile() == null) {
            saveAsFile(mainForm);
        } else {
            loadBodyIntoFile(mainForm);
        }
    }

}
