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
        vm.setAddress(vm.getProgramCounter());
        vm.setAddress(targetAddress);
        this.numArgs = vm.getArgs();
    }

    @Override
    public void init(List<String> args) {
        this.label = args.get(1);
        this.baseId = label.split("<<")[0];
    }

    @Override
    public String toString() {// not sure
        return "CALL " + label + " " + baseId +"("+ numArgs + ")";
    }
}
