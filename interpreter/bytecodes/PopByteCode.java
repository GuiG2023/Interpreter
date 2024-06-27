package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 10:53
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class PopByteCode implements ByteCode{
    private int count; // the number of the value that we want to pop from rts
    @Override
    public void excute(VirtualMachine vm) {
        for (int i = 0; i < count; i++) {
            if (!vm.isAtFrameBoundary()) {
                vm.pop();
            } else {
                System.out.println("out of bounds");
                break;
            }
        }
    }

    @Override
    public void init(List<String> args) {
        if (args != null && !args.isEmpty()) {
            this.count = Integer.parseInt(args.getLast());
        }
    }

    @Override
    public boolean modifiesProgramCounter() {
        return false;
    }

    @Override
    public String toString() {
        return "POP " + this.count;
    }
}
