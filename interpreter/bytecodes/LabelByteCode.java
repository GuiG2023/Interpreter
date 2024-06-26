package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 11:43
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class LabelByteCode implements ByteCode {
    private String label;

    @Override
    public void excute(VirtualMachine vm) {
        if (vm.isVerbose()) {
            System.out.println(this.toString());
        }
    }

    @Override
    public void init(List<String> args) {
        this.label = args.getLast();
    }

    @Override
    public String toString() {
        return "LABEL " + this.label;
    }
}
