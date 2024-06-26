package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;
import java.util.Scanner;

/**
 * 6/24/24 @ 11:43
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class ReadByteCode implements ByteCode {
    @Override
    public void excute(VirtualMachine vm) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Please enter an integer: ");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
        vm.push(input);
    }

    @Override
    public void init(List<String> args) {

    }

    @Override
    public String toString() {
        return "READ";
    }
}
