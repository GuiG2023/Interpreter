package interpreter.virtualmachine;

import java.util.List;
import java.util.Stack;

import interpreter.bytecodes.ByteCode;
import interpreter.loaders.Program;

public class VirtualMachine {

    private RunTimeStack runTimeStack;
    private Stack<Integer> returnAddress;
    private Program program;
    private int programCounter;
    private boolean isRunning;
    private boolean verboseMode;//  only use to test my verbose mode


    public VirtualMachine(Program program) {
        this.program = program;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<>();
        this.programCounter = 0;
    }

    public void executeProgram() {
        /**
         * The principle, I think, should be similar to the JAL (jump & link)command in the CSC256 class.
         * After jumping to execute other commands, the pointer should return to its original position.
         * such as jump(goto ; falsebranch) commands, do not change the program counter
         */
        isRunning = true;
        while (isRunning) {
            ByteCode code = program.getCode(programCounter);
            if (code != null) {
                //System.out.println("Executing: " + code);
                code.execute(this);
                if (verboseMode) {
                    String postState = runTimeStack.verboseDisplay(); // State after execution
                    System.out.println(code); // Print the executed ByteCode
                    System.out.println(postState);
                }

                if (!code.modifiesProgramCounter()) {// so we have this to check if the method should call pc++
                    programCounter++;
                }
            } else {
                isRunning = false;
                System.out.println("Program halted");
            }
        }
    }

    public int push(int value) {
        return this.runTimeStack.push(value);
    }

    public void setAddress(int targetAddress) {
        this.programCounter = targetAddress;
    }

    public boolean isVerbose() {
        return verboseMode;
    }

    public void halt() {
        isRunning = false;
    }

    public int pop() {
        return this.runTimeStack.pop();
    }

    public boolean isAtFrameBoundary() {
        return this.runTimeStack.isAtFrameBoundary();
    }

    public boolean isEmpty() {
        return this.returnAddress.isEmpty();
    }

    public int store(int offset) {
        return this.runTimeStack.store(offset);
    }

    public int load(int offset) {
        return this.runTimeStack.load(offset);
    }

    public void args(int numArgs) {
        this.runTimeStack.args(numArgs);
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public int getArgs() {
        return this.runTimeStack.getArgs();
    }

    public int peek() {
        return this.runTimeStack.peek();
    }

    public void popFrame() {
        this.runTimeStack.popFrame();
    }

    public int popReturnAddress() {
        return this.returnAddress.pop();
    }

    public void setProgramCounter(int address) {
        this.programCounter = address;
    }

    public void setVerboseMode(boolean verboseMode) {
        this.verboseMode = verboseMode;
    }

    public void pushReturnAddress(int address) {
        this.returnAddress.push(address);
    }




    public void newFrameAt(int numArgs) {
        this.runTimeStack.newFrameAt(numArgs);
    }

    public void setNextCallNumArgs(int numArgs) {
        runTimeStack.setNextCallNumArgs(numArgs);
    }

    public List<Integer> getArgsForNextCall() {
        return runTimeStack.getArgsForNextCall();
    }

    public int getNextCallNumArgs() {
        return runTimeStack.getNextCallNumArgs();
    }
}
