package interpreter.loaders;

import interpreter.bytecodes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {

    private List<ByteCode> program;

    /**
     * Instantiates a program object using an
     * ArrayList
     */
    public Program() {
        this.program = new ArrayList<>();

    }

    /**
     * Gets the size of the current program.
     *
     * @return size of program
     */
    public int getSize() {
        return program.size();
    }

    /**
     * Grabs an instance of a bytecode at an index.
     *
     * @param programCounter index of bytecode to get.
     * @return a bytecode.
     */
    public ByteCode getCode(int programCounter) {
        if (programCounter < program.size()) {
            return program.get(programCounter);
        }
        return null;
    }

    /**
     * Adds a bytecode instance to the Program List.
     *
     * @param c bytecode to be added
     */
    public void addCode(ByteCode c) {
        this.program.add(c);

    }

    /**
     * Makes multiple passes through the program ArrayList
     * resolving addrss for Goto,Call and FalseBranch
     * bytecodes. These bytecodes can only jump to Label
     * codes that have a matching label value.
     * HINT: make note of what type of data-structure
     * ByteCodes are stored in.
     * **** METHOD SIGNATURE CANNOT BE CHANGED *****
     */
    public void resolveAddress() {
        //create a map to record all labels, tyring to record all labels

        Map<String, Integer> labelAddresses = new HashMap<>();

        //first iteration to record all labels
        for (int i = 0; i < program.size(); i++) {
            ByteCode byteCode = program.get(i);
            if (byteCode instanceof LabelByteCode labelByteCode) {
                labelAddresses.put(labelByteCode.getLabel(), i);
            }
        }


        //second iteration to resolve the address that some method need
        for (ByteCode byteCode : program) {
            if (byteCode instanceof GotoByteCode gotoByteCode) {
                gotoByteCode.setTargetAddress(labelAddresses.get(gotoByteCode.getLabel()));
            } else if (byteCode instanceof CallByteCode callByteCode) {
                callByteCode.setTargetAddress(labelAddresses.get(callByteCode.getLabel()));
            } else if (byteCode instanceof FalseBranchByteCode falseBranchByteCode) {
                falseBranchByteCode.setTargetAddress(labelAddresses.get(falseBranchByteCode.getLabel()));
            }
        }




    }
}   