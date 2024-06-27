package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 11:02
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class CallByteCode implements ByteCode {
    private String label;
    private int targetAddress;

    private String baseId;

    private int numArgs;

    @Override
    public void excute(VirtualMachine vm) {
        vm.pushReturnAddress(vm.getProgramCounter() + 1);
        vm.setProgramCounter(targetAddress);
       // this.numArgs = vm.getNextCallNumArgs();
    }

    @Override
    public void init(List<String> args) {
        this.label = args.get(1);
        this.baseId = label.split("<<")[0];
    }

    @Override
    public String toString() {// not sure
        if(numArgs == 0){
            return "CALL " + label + " " + baseId +"("+")";
        }
        return "CALL " + label + " " + baseId +"("+ numArgs + ")";
    }

    public void setTargetAddress(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getLabel() {
        return label;
    }
}
