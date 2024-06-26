package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 10:54
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class LoadByteCode implements ByteCode{
    private String label;
    private int offset;

    @Override
    public void excute(VirtualMachine vm) {
        vm.load(this.offset);
    }

    @Override
    public void init(List<String> args) {
        this.offset = Integer.parseInt(args.get(1));
        if (args.size() > 2) {
            this.label = args.get(2);
        }

    }

    @Override
    public String toString() {
        String retVal = "Load " + this.offset;
        if (this.label != null) {
            retVal += " " + this.label + "\t\tint " + this.label;
        }
        return retVal;
    }
}
