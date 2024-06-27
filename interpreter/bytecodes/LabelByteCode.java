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

    }

    @Override
    public void init(List<String> args) {
        if (!args.isEmpty()) {
            this.label = args.getLast();
        }
    }

    @Override
    public String toString() {
        return "LABEL " + this.label;
    }

    public String getLabel() {
        return label;
    }
}
