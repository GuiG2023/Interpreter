package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 10:54
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class FalseBranchByteCode implements ByteCode {
    private String label;
    private int targetAddress;

    private int value;

    @Override
    public void excute(VirtualMachine vm) {
        value = vm.pop();
        if (value == 0) {
            vm.setAddress(targetAddress);
        }
    }

    @Override
    public void init(List<String> args) {
        this.label = args.getLast();
    }

    @Override
    public boolean modifiesProgramCounter() {
        return value == 0;
    }

    @Override
    public String toString() {
        return "FALSEBRANCH " + label;
    }

    public void setTargetAddress(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getLabel() {
        return label;
    }


}
