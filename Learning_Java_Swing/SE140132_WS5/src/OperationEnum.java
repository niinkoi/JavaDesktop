/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khoi Nguyen
 */
public enum OperationEnum {
    PLUS("+"),
    DISTRACT("-"),
    MULTIPLY("*"),
    DIVISION("/"),
    NO_THING("NONE");
    
    private String value;

    private OperationEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}
