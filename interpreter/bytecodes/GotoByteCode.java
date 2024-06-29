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
    public void execute(VirtualMachine vm) {
        vm.setProgramCounter(targetAddress);

    }


    @Override
    public void init(List<String> args) {
        if (!args.isEmpty()) {
            this.label = args.getLast();
        }


    }

    @Override
    public boolean modifiesProgramCounter() {
        return true;
    }

    @Override
    public String toString() {
        return "GOTO " + this.label;
    }

    public int getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getLabel() {
        return label;
    }
}
