package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 11:42
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class BopByteCode implements ByteCode {

    private String operator;

    @Override
    public void excute(VirtualMachine vm) {
        int result;
        int value1 = vm.pop();
        int value2 = vm.pop();
        switch (operator) {
            case "+":
                result = value1 + value2;
                break;
            case "-":
                result = value1 - value2;
                break;
            case "*":
                result = value1 * value2;
                break;
            case "/":
                result = value1 / value2;
                break;
            case "==":
                result = (value1 == value2) ? 1 : 0;
                break;
            case "!=":
                result = (value1 != value2) ? 1 : 0;
                break;
            case "<=":
                result = (value1 <= value2) ? 1 : 0;
                break;
            case "<":
                result = (value1 < value2) ? 1 : 0;
                break;
            case ">=":
                result = (value1 >= value2) ? 1 : 0;
                break;
            case ">":
                result = (value1 > value2) ? 1 : 0;
                break;
            case "&":
                result = (value1 != 0 && value2 != 0) ? 1 : 0;
                break;
            case "|":
                result = (value1 != 0 || value2 != 0) ? 1 : 0;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }

        vm.push(result);

    }

    @Override
    public void init(List<String> args) {
        this.operator = args.getLast();
    }

    @Override
    public String toString() {
        return "Bop " + operator;
    }
}
