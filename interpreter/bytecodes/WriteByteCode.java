package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 11:43
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class WriteByteCode implements ByteCode{
    @Override
    public void excute(VirtualMachine vm) {
        int topValue = vm.peek();
        System.out.println(topValue);
    }

    @Override
    public void init(List<String> args) {

    }

    @Override
    public boolean modifiesProgramCounter() {
        return false;
    }

    @Override
    public String toString() {
        return "WRITE";
    }
}
