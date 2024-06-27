package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 11:42
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class ReturnByteCode implements ByteCode {

    private String label;
    private String baseId;
    private int returnValue;

    @Override
    public void excute(VirtualMachine vm) {
        returnValue = vm.peek();
        vm.popFrame();
        vm.push(returnValue);
        int returnAddress = vm.popReturnAddress();
        vm.setProgramCounter(returnAddress);
    }

    @Override
    public void init(List<String> args) {
        if (!args.isEmpty()) {
            this.label = args.getFirst();
            this.baseId = label.contains("<<") ? label.split("<<")[0] : label;
        }
    }

    @Override
    public String toString() {
        return "RETURN ";
    }
}
