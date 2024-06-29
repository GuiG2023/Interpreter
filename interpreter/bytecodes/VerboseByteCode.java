package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 11:43
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class VerboseByteCode implements ByteCode{
    private String verboseValue;
    @Override
    public void execute(VirtualMachine vm) {
        if ("ON".equalsIgnoreCase(verboseValue)) {
            vm.setVerboseMode(true);
        } else if ("OFF".equalsIgnoreCase(verboseValue)) {
            vm.setVerboseMode(false);
        }
    }

    @Override
    public void init(List<String> args) {
        if (args.size() > 1) {
            this.verboseValue = args.get(1).toUpperCase();
        }
    }

    @Override
    public boolean modifiesProgramCounter() {
        return false;
    }

    @Override
    public String toString() {
        return "VERBOSE " + verboseValue;
    }
}
