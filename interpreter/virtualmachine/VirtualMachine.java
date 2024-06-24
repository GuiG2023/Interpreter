package interpreter.virtualmachine;

import java.util.Stack;
import interpreter.loaders.Program;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;

    public VirtualMachine(Program program) {
        this.program = program;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<>();
        this.programCounter = 0;
    }

    public void executeProgram() {
    }

    public int push(int value) {
        return this.runTimeStack.push(value);
    }
}
