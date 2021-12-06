
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khoi Nguyen
 */
public class CalculatorService {
    
    final List<OperationEnum> operList = Arrays
        .asList(OperationEnum.PLUS, 
                OperationEnum.DISTRACT,
                OperationEnum.MULTIPLY,
                OperationEnum.DIVISION,
                OperationEnum.ROOT,
                OperationEnum.SQUARE);
    
    private final String ZERO = "0";
    
    public static boolean isEqualBtnPress = false;
    
//    public String getNumber(JTextField field) {
//        String value = field.getTE;
//        if (isEqualBtnPress) {
//            isEqualBtnPress = false;
//            return ZERO;
//        }
//        System.out.println("Value is " + value);
//        return new BigDecimal(value) + "";
//    }
    
    public BigDecimal getOperationResult(JTextField field, OperationEnum method, BigDecimal... nums) {
        if(!isEqualBtnPress) {
            if(method == OperationEnum.NOTHING) {
                //nums[0] = getNumber(field.getText());
            } else {
                nums[1] = getNumber(field);
                switch(method) {
                    case PLUS:
                        System.out.println("[0]: " + nums[0] + " [1]: " + nums[1]);
                        nums[0] = nums[0].add(nums[1]);
                        return nums[0];
                    case DISTRACT:
                        return nums[0].subtract(nums[1]);
                    case MULTIPLY:
                        return nums[0].multiply(nums[1]);
                    case DIVISION:
                        return !String.valueOf(nums[1]).equals(ZERO)? 
                                nums[0].divide(nums[1]) : BigDecimal.valueOf(Double.MIN_VALUE);
        //            case SQUARE:
        //                return Math.pow(nums[0], 2);
                }
            }
        }
        isEqualBtnPress = true;
        return BigDecimal.ZERO;
    }
    
    private BigDecimal getNumber(JTextField field) {
        BigDecimal temp;
        try {
            temp = new BigDecimal(field.getText());
        } catch (Exception e) {
            System.err.println(ConstantError.INVALID_INPUT);
            return BigDecimal.TEN;
        }
        return temp;
    }
    
    public Optional<OperationEnum> renderInstance(String cmd) {
        return operList
                .stream()
                .filter(oper -> oper.getValue().equals(cmd))
                .findAny();
    }
}
