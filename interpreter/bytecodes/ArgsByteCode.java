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
       vm.args(numArgs);
    }

    @Override
    public void init(List<String> args) {
        this.numArgs = Integer.parseInt(args.getLast());
    }

    @Override
    public String toString() {
        return "Args " + numArgs;
    }
}
