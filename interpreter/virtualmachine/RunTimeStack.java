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
            sb.append(runTimeStack.subList(start, framePointer.get(i))).append(" ");
            start = framePointer.get(i); // update new start of the frame
        }
        sb.append(runTimeStack.subList(start, runTimeStack.size()));
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

        int val = this.pop();
        int index = framePointer.peek() + offsetInFrame;
        if (offsetInFrame < 0 || index > runTimeStack.size() - 1) {
            throw new IllegalArgumentException(" ！！cannot store out of the current frame!");
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
        if (offsetInFrame < 0) {
            throw new IllegalArgumentException("！！cannot load element from outside of current frame!");
        }
        int index = framePointer.peek() + offsetInFrame;
        if (index >= runTimeStack.size()) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
//        System.out.println(index);
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

    }

    /**
     * pop the current frame off the runtime stack.
     * Also removes the frame pointer value from the FramePointer Stack.
     */

    public void popFrame() {
        int startIndex = framePointer.pop(); // the boundary
        while (runTimeStack.size() > startIndex) {
            runTimeStack.remove(runTimeStack.getLast()); // pop to the boundary
        }

    }

    public void args(int numArgs) {
        newFrameAt(numArgs);
    }

    public int getArgs() {
        int frameStart = framePointer.peek();
        if (frameStart < runTimeStack.size()) {
            return runTimeStack.get(frameStart);
        } else {
            throw new IndexOutOfBoundsException("No arguments available in the current frame.");
        }
    }


    //test by creating new main
    public static void main(String[] args) {
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
        rts.load(0); // load 12
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

    public boolean isAtFrameBoundary() {
        int frameIndex = runTimeStack.getLast();
        return framePointer.contains(frameIndex);
    }
}
