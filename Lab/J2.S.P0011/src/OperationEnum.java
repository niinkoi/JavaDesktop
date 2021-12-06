/**
 * 
 * @author Khoi Nguyen
 */
public enum OperationEnum {
    PLUS("+"),
    DISTRACT("-"),
    MULTIPLY("*"),
    DIVISION("/"),
    ROOT("√"),
    SQUARE("x²"),
    NOTHING("");

    private String value;

    private OperationEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}