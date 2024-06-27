package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 10:56
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class ArgsByteCode implements ByteCode{
    private int numArgs;
    @Override
    public void excute(VirtualMachine vm) {
        vm.newFrameAt(numArgs);
        vm.setNextCallNumArgs(numArgs);
    }

    @Override
    public void init(List<String> args) {
        if (args != null && !args.isEmpty()) {
            this.numArgs = Integer.parseInt(args.get(1));
        }
    }

    @Override
    public boolean modifiesProgramCounter() {
        return false;
    }

    @Override
    public String toString() {
        return "ARGS " + numArgs;
    }

    public int getNumArgs() {
        return numArgs;
    }
}
