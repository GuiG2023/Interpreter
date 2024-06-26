package interpreter.virtualmachine;

import java.util.Stack;

import interpreter.bytecodes.ByteCode;
import interpreter.loaders.Program;

public class VirtualMachine {

    private RunTimeStack runTimeStack;
    private Stack<Integer> returnAddress;
    private Program program;
    private int programCounter;
    private boolean isRunning;
    private boolean verboseMode;

    public VirtualMachine(Program program) {
        this.program = program;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<>();
        this.programCounter = 0;
    }

    public void executeProgram() {
        isRunning = true;
        while (isRunning) {
            ByteCode code = program.getCode(programCounter);
            if (code != null) {
//                System.out.println("Executing ByteCode: " + code.getClass().getSimpleName());
                code.excute(this);
                programCounter++;
            } else {
                isRunning = false;
//                System.out.println("after halt");
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
        this.returnAddress.push(address);
    }

    public void setVerboseMode(boolean verboseMode) {
        this.verboseMode = verboseMode;
    }
}
