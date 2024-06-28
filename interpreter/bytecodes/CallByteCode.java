package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 6/24/24 @ 11:02
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class CallByteCode implements ByteCode {
    private String label;
    private int targetAddress;

    private String baseId;
    private List<Integer> args;

    private int numArgs;


    @Override
    public void execute(VirtualMachine vm) {
        vm.pushReturnAddress(vm.getProgramCounter() + 1);
        vm.setProgramCounter(targetAddress);
        this.args = vm.getArgsForNextCall();
        numArgs = vm.getNextCallNumArgs();


    }

    @Override
    public void init(List<String> args) {
        this.label = args.get(1);
        this.baseId = label.split("<<")[0];
    }

    @Override
    public boolean modifiesProgramCounter() {
        return true;
    }

    @Override
    public String toString() {// not sure
        if (numArgs == 0) {
            return "CALL " + label + " " + baseId + "(" + ")";
        }
        return "CALL " + label + " " + baseId +"(" + args.stream().map(Object::toString).collect(Collectors.joining(", "))+ ")";
    }

    public void setTargetAddress(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getLabel() {
        return label;
    }
}
