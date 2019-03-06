package com.company;

/*      You’re tasked with implementing a simple calculator. The calculator stores numbers as 10 bit unsigned integers on an internal stack and have the following commands
        Push <value>
        Pushes <value> on the memory stack. The stack has a maximum capacity of 5. When the stack capacity is exceeded the command results in error. <value> is guaranteed to be a valid

        Pop
        Pops a value from the memory stack and discards it. If no values on the stack no operation is performed

        Add
        Pops two values from the stack, adds them together, prints the result and pushes the result back to the stack. If the required operands are not on the stack the command results in an error

        Sub
        Pops two values from the stack subtract the second topmost from the topmost prints the result and push the result back to the stack. If the required operands are not on the stack the command results in an error

        Rules:
        A computation involving unsigned operands can never overflow. A result that cannot be represented by the resulting unsigned integer type is reduced modulo the number that is one greater than the largest value that can be represented by the resulting type.
        Example 5-7= 1022 (because -2 mod 1024 = 1022)

        Example interaction with the calculator:
        Push 10 (stack is 10)
        Push 7 (stack is 10, 7)
        Add
        >> 17 (stack is 17)
        Push 25 (stack is 17, 25)
        Sub
        >> 8 (stack is 8)

        Use Java or JavaScript, focus on correctness, submit solution through a git repo*/

public class Main {
    private static Calculator calculator = new Calculator();

    public static void main(String[] args) {
        System.out.println("Calculator commands are as follows:\n" +
                "Push <VALUE>\n" +
                "Pop\n" +
                "Add\n" +
                "Sub\n" +
                "Quit\n" +
                "Funk (Just for fun...k)");
        calculator.run();
    }
}
