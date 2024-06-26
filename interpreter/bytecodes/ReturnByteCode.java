package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 11:42
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class ReturnByteCode implements ByteCode{

    private String label = "";
    private String baseId = "";
    private int returnValue;
    @Override
    public void excute(VirtualMachine vm) {
        int returnValue = vm.pop();
        vm.popFrame();
        int returnAddress = vm.popReturnAddress();
        vm.setProgramCounter(returnAddress);
        vm.push(returnValue);
    }

    @Override
    public void init(List<String> args) {
        if (args.size() > 1) {
            this.label = args.get(1);
            this.baseId = label.split("<<")[0];
        }
    }
    @Override
    public String toString() {
        return "RETURN " + label + " EXIT " + baseId + ": " + returnValue;
    }
}
