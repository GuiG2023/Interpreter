package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

class RunTimeStack {

    private List<Integer> runTimeStack;//rts

    /**
     * This ArrayList is used to represent the runtime stack.
     * It will be an ArrayList because we will need to access
     * ALL locations of the runtime stack.
     */

    private Stack<Integer> framePointer;//fps

    /**
     * This stack is used to record the beginning of each activation
     * record (frame) when calling functions.
     */

    private int nextCallNumArgs = 0;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial frame pointer value, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    /**
     * Used for displaying the current state of the runTimeStack.
     * It will print portions of the stack based on respective frame markers.
     * Example [1,2,3] [4,5,6] [7,8]
     * Frame pointers would be 0,3,6
     */
    public String verboseDisplay() {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        for (int i = 1; i < framePointer.size(); i++) {
            int end = framePointer.get(i);
           // make sure not out of bounds of runtime stack
            if (end > runTimeStack.size()) {
                end = runTimeStack.size();
            }
            sb.append(runTimeStack.subList(start, end)).append(" ");
            start = framePointer.get(i);
        }
        //  to make sure start in the bound
        if (start <= runTimeStack.size()) {
            sb.append(runTimeStack.subList(start, runTimeStack.size()));
        }
        return sb.toString();
    }

    /**
     * returns the top of the runtime stack,
     * but does not remove * @return copy of the top of the stack.
     */
    public int peek() {
        return this.runTimeStack.getLast();
    }

    /**
     * push the value i to the top of the stack. * @param i value to be pushed.
     *
     * @return value pushed
     */
    public int push(int value) {
        this.runTimeStack.add(value);
        return value;
    }

    /**
     * removes to the top of the runtime stack. * @return the value popped.
     */
    public int pop() {
        if (runTimeStack.isEmpty()) {
            throw new EmptyStackException();
        } else {
            return runTimeStack.removeLast();
        }
    }

    /**
     * Takes the top item of the run time stack, and stores
     * it into a offset starting from the current frame.
     *
     * @param offsetInFrame number of slots above current frame maker
     * @return the item just stored
     */
    public int store(int offsetInFrame) {

        if (framePointer.isEmpty()) {
            throw new IllegalStateException("Frame pointer stack is empty.");
        }
        if (runTimeStack.isEmpty()) {
            throw new EmptyStackException();
        }
        int val = this.pop();
        int index = framePointer.peek() + offsetInFrame;
        if (offsetInFrame < 0 || index >= runTimeStack.size()) {
            throw new IllegalArgumentException("Cannot store out of the current frame!");
        }
        runTimeStack.set(index, val);
        return val;
    }

    /**
     * Takes a value from the run time stack that is at offset
     * from the current frame marker and pushes it onto the top of the stack.
     *
     * @param offsetInFrame number of slots above current frame marker
     * @return item just loaded into the offset
     */
    public int load(int offsetInFrame) {
        if (framePointer.isEmpty()) {
            throw new EmptyStackException();
        }
        if (offsetInFrame < 0) {
            throw new IllegalArgumentException("Cannot load element from outside of current frame!");
        }
        int index = framePointer.peek() + offsetInFrame;
        if (index >= runTimeStack.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + runTimeStack.size());
        }
        int val = runTimeStack.get(index);
        this.push(val);
        return val;
    }

    /**
     * create a new frame pointer at the index offset slots down
     * from the top of the runtime stack.
     *
     * @param offsetFromTopOfRunStack slots down from the top of the runtime stack
     */
    public void newFrameAt(int offsetFromTopOfRunStack) {
        if (offsetFromTopOfRunStack > runTimeStack.size()) {
            throw new IllegalArgumentException("OUT OF BOUNDS");
        }
        int newFrameIndex = runTimeStack.size() - offsetFromTopOfRunStack;
        framePointer.push(newFrameIndex);
        nextCallNumArgs = 0;

    }

    /**
     * pop the current frame off the runtime stack.
     * Also removes the frame pointer value from the FramePointer Stack.
     */

    public void popFrame() {
        if (framePointer.isEmpty()) {
            throw new IllegalStateException("Frame pointer stack is empty.");
        }
        int startIndex = framePointer.pop(); // the boundary
        while (runTimeStack.size() > startIndex) {
            runTimeStack.removeLast(); // More robust and clear
        }
    }

    public void args(int numArgs) {
        newFrameAt(numArgs);
    }

    public int getArgs() {
        if (framePointer.isEmpty() || runTimeStack.isEmpty()) {
            throw new IndexOutOfBoundsException("No arguments available in the current frame.");
        }
        int frameStart = framePointer.peek();
        return runTimeStack.size() - frameStart;
    }

    public boolean isAtFrameBoundary() {
        return !framePointer.isEmpty() && framePointer.peek() == runTimeStack.size();
    }


    //test by creating new main
    public static void main(String[] args) {
        // test of runtime stack
        RunTimeStack rts = new RunTimeStack();
        rts.push(4);
        rts.push(5);
        rts.push(6);
        rts.push(7);
        rts.push(8);
        rts.push(9);
        rts.push(10);
        rts.push(11);
        rts.push(12);
        System.out.println(rts.verboseDisplay());

        rts.newFrameAt(7);
        rts.newFrameAt(4);
        System.out.println(rts.verboseDisplay());

        rts.store(0);// current frame， store 12 at current frame[0]
        System.out.println(rts.verboseDisplay());


        //System.out.println(rts.framePointer.peek());


        rts.framePointer.forEach(System.out::println);
        System.out.println(rts.framePointer.peek());
        System.out.println(rts.runTimeStack.size());
        rts.load(0); // load 12
        System.out.println(rts.verboseDisplay());

        rts.store(0);
        System.out.println(rts.verboseDisplay());

        rts.pop();
        System.out.println(rts.verboseDisplay());

        System.out.println(rts.peek());

        rts.push(50);
        rts.push(60);
        System.out.println(rts.verboseDisplay());

        rts.args(2);
        System.out.println(rts.verboseDisplay());

        rts.popFrame();
        System.out.println(rts.verboseDisplay());


    }

    public void setNextCallNumArgs(int numArgs) {
         nextCallNumArgs = numArgs;
    }

    public int getNextCallNumArgs() {
        return nextCallNumArgs;
    }



    public List<Integer> getArgsForNextCall() {
        List<Integer> args = new ArrayList<>();
        int startIndex = runTimeStack.size() - nextCallNumArgs;
        for (int i = startIndex; i < runTimeStack.size(); i++) {
            args.add(runTimeStack.get(i));
        }
        return args;
    }
}
