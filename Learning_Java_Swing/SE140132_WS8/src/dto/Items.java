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
public class Items extends AbstractDto {
    
    private String unit;
    private float price;
    private boolean supplying;
    private Suppliers supplier;

    public Items() {
    }
    
    
    public Items(String code, String name, String unit,
                    float price, boolean supplying,
                    Suppliers supplier) {
        
        super(code, name);
        this.unit = unit;
        this.price = price;
        this.supplying = supplying;
        this.supplier = supplier;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isSupplying() {
        return supplying;
    }

    public void setSupplying(boolean supplying) {
        this.supplying = supplying;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

}
