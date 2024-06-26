package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/22/24 @ 18:52
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class HaltByteCode implements ByteCode {
    @Override
    public void init(List<String> args) {

    }
    @Override
    public void excute(VirtualMachine vm) {
        System.out.println("Executing HALT ByteCode");
        vm.halt();
    }

//    @Override
//    public String toString() {
//        return "Halt";
//    }
}
