package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * 6/22/24 @ 18:52
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class LitByteCode implements ByteCode {
    private String label;
    private int value;

    @Override
    public void excute(VirtualMachine vm) {
        vm.push(this.value);
    }

    @Override
    public void init(List<String> args) {
        this.value = Integer.parseInt(args.get(1));
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
        String retVal = "LIT " + this.value;
        if (this.label != null) {
            retVal += " " + this.label + "\t\tint " + this.label;
        }
        return retVal;
    }

//    public static void main(String[] args) {
//        LitByteCode lc = new LitByteCode();
//        List<String> bcargs = new ArrayList<>();
//        bcargs.add("LIT");
//        bcargs.add("1");
//        bcargs.add("i");
//        lc.init(bcargs);
//        System.out.println(lc);
//    }
}
