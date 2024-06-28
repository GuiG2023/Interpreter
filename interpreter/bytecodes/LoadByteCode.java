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
        try {
            vm.load(this.offset);
        } catch (Exception e) {
            System.err.println("Load operation failed: " + e.getMessage());
        }
    }

    @Override
    public void init(List<String> args) {
        this.offset = Integer.parseInt(args.get(1));
        if (args.size() > 2) {
            this.label = args.get(2);
        }

    }

    @Override
    public boolean modifiesProgramCounter() {
        return false;
    }

    @Override
    public String toString() {
        String retVal = "Load " + this.offset;
        if (this.label != null) {
            retVal += " " + "\t< load" + " " + this.label+">";
        }
        return retVal;
    }
}
