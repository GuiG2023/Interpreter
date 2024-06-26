package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/22/24 @ 18:52
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class GotoByteCode implements ByteCode {
    private String label;
    private int targetAddress;

    @Override
    public void excute(VirtualMachine vm) {
        vm.setAddress(targetAddress);

    }


    @Override
    public void init(List<String> args) {
        if (!args.isEmpty()) {
            this.label = args.getLast();
        }


    }

    @Override
    public String toString() {
        return "GoTo " + this.label;
    }
}