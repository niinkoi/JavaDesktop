/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Khoi Nguyen
 */
public class Suppliers extends AbstractDto {
    
    private String address;
    private boolean colloborating;

    public Suppliers() {
    }
    
    public Suppliers(String code, String name,
                        String address, boolean colloborating) {
        super(code, name);
        this.address = address;
        this.colloborating = colloborating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isColloborating() {
        return colloborating;
    }

    public void setColloborating(boolean colloborating) {
        this.colloborating = colloborating;
    }
    
}
