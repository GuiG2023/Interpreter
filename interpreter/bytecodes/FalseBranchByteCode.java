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

    @Override
    public void excute(VirtualMachine vm) {
        int value = vm.pop();
        if (value == 0){
            vm.setAddress(targetAddress);
        }
    }

    @Override
    public void init(List<String> args) {
        this.label = args.getLast();
    }

    @Override
    public String toString() {
        return "FalseBranch "+ label;
    }
}
