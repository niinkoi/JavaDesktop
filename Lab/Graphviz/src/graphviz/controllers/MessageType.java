/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphviz.controllers;

/**
 *
 * @author Khoi Nguyen
 */
public enum MessageType {
    CANCEL("The user cancelled the operation."),
    ERROR("");
    
    private final String messageContext;

    private MessageType(String msgContext) {
        this.messageContext = msgContext;
    }
    
    public String getMessage() {
        return this.messageContext;
    }
    
}
