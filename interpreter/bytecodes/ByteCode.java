package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/20/24 @ 20:33
 *
 * @ Author : Guiran LIU
 * Description:
 */
public interface ByteCode {
    void excute(VirtualMachine vm);
    void init(List<String> args);
}
