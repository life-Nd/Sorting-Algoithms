// Student name: Ralph NDUWIMANA
// Student id: 261066154

import java.util.Scanner;

public class Stacks {
    // Driver code
    public static void main(String[] args) {
        /* Start with the empty stack. */
        MyStack stack = new MyStack();
        processUserInput(stack);
    }

    /**
     * Print in the console the instructions on how to use the program.
     */
    public static void inputRequest() {
        System.out.println(
                "Enter:\t Integer: Add to the Stack, \t \"+\": Add the last 2 items,\t \"*\": Multiply the last 2 items,\t \"?\": Print the Stack,\t \"P\": Pop the Stack \t or \t \"Z\": Exit the program");
    }

    /**
     * @param stack The current instance of MyStack used.
     */
    public static void processUserInput(MyStack stack) {

        Boolean run = true;
        while (run) {
            inputRequest();
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if (!input.isEmpty()) {
                try {
                    int value = Integer.parseInt(input);
                    stack.push(stack, value);

                } catch (Exception e) {
                    boolean has2Elements = stack.top != null && stack.top.next != null;
                    switch (input.toUpperCase()) {
                        case "+":
                            /**
                             * Apply that operation on the 2 last items pushed to the stack (Hint make use
                             * pop)
                             * If the stack doesn’t have 2 items, the operation is ignored
                             * The result is stored and pushed to the stack.
                             */
                            if (has2Elements) {
                                int num1 = stack.pop(stack);
                                int num2 = stack.pop(stack);
                                stack.push(stack, addition(num1, num2));
                            } else
                                System.out.println("🚫 Doesn't have two elements to add");
                            break;
                        case "*":
                            // Apply that operation on the 2 last items pushed to the stack (Hint make use
                            // pop)
                            // If the stack doesn’t have 2 items just ignore the operation
                            // Store the result and push it to the stack
                            if (has2Elements) {
                                int num1 = stack.pop(stack);
                                int num2 = stack.pop(stack);
                                stack.push(stack, multiplication(num1, num2));
                            } else
                                System.out.println("🚫 Doesn't have two elements to multiply");
                            break;
                        case "?":
                            // If the user entered the ? symbol, print the stack— using toString() to print
                            // all the stacks items without popping them
                            printStack("📚 Print of", stack);
                            break;
                        case "P":
                            // If the user put P, pop the stack and print the value
                            stack.pop(stack);
                            printStack("Popped", stack);
                            break;
                        case "Z":
                            // If the user put the Z symbol, end the program
                            run = false;
                            sc.close();
                            break;

                        default:
                            System.out.println("❗️ Ignored input:");
                    }
                }
            }
        }

    }

    /**
     * 
     * Add num1 to num2
     * 
     * @param num1 The value of the Node at the top.
     * @param num2 The value of the Node after the one at the top.
     * @return The total of num1 + num2
     */
    public static int addition(int num1, int num2) {
        System.out.print("Adding " + num1 + " + " + num2 + " = ");
        int total;
        total = num1 + num2;
        System.out.println(total);
        return total;
    }

    /**
     * multiply num1 with num2
     * 
     * @param num1 The value of the Node at the top.
     * @param num2 The value of the Node after the one at the top.
     * @return The total of num1 * num2
     */
    public static int multiplication(int num1, int num2) {
        System.out.print("Multiplying " + num1 + " * " + num2 + " = ");
        int total;
        total = num1 * num2;
        System.out.println(total);
        return total;
    }

    /**
     * Prints the stack in the console.
     * @param action A reference for clarity in the console log.
     * @param stack  The current instance of MyStack used.
     */
    public static void printStack(String action, MyStack stack) {
        // System.out.println("---------------------------");
        System.out.println(action + " Stack: [" + stack.toStr(stack) + "]\n");
        // System.out.println("---------------------------");
    }

}

class MyStack {

    /**
     * The constructor for this class
     */
    public MyStack() {
    }

    Node top; // top of the stack
    int size; // size of the stack

    static class Node {
        int data; // This variable will contain the data of the node
        Node next; // This variable will contain the next node

        // A constructor that is initiated when creating a new node
        Node(int value) {
            data = value;
            next = null;
        }
    }

    /**
     * Push a new Node to the top of the Stack.
     * 
     * @param stack The current instance of MyStack used.
     * @param data  The value of the new Node
     * @return
     */
    public MyStack push(MyStack stack, int data) {
        // A new node is created with the data entered
        Node new_node = new Node(data);
        // First we check if there is a node in the stack top
        // Mostly this happens if the stack is empty
        if (stack.top == null) {
            // The top of the stack would then be the new_node
            stack.top = new_node;

        } else {
            /**
             * If the stack had a node in the top position
             * We insert the new node to the top
             * and give it the next node as the node that was
             * previously in the top position
             */
            new_node.next = stack.top;
            stack.top = new_node;
        }
        stack.size++;
        System.out.println("✅ Pushed " + data + " to top of Stack");
        return stack;
    }

    /**
     * @param stack The current instance of MyStack used.
     * @return True/False if the Stack is empty or not
     */
    public boolean isEmpty(MyStack stack) {
        return getSize(stack) == 0;
    }

    /**
     * @param stack The current instance of MyStack used.
     * @return The size of the stack
     */
    public int getSize(MyStack stack) {
        // Returns the size of the Stack.
        // This variable is updated each time we pop or push.
        return stack.size;
    }

    /**
     * @param stack The current instance of MyStack used.
     * @return The value of the Top node that was popped.
     */
    public int pop(MyStack stack) {
        // The node at the top (top) is removed, the next node becomes the new node at
        // the top
        // Last In, First Out
        int poppedValue = stack.top.data;
        stack.top = stack.top.next;
        // Variable size of the Stack is updated by removing 1
        // each time this function is called.
        stack.size--;
        System.out.println("❎ Popped " + poppedValue);
        return poppedValue;

    }

    /**
     * @param stack The current instance of MyStack used.
     * 
     *              Parses the Stack from the top to the bottom Node
     *              while concatenating the value of each Node to a String.
     * @return The concatenated values of all nodes.
     */

    public String toStr(MyStack stack) {
        String stackStr = "";
        Node currentNode = stack.top;
        // Goes through the stack
        while (currentNode != null) {
            // Concatenate the new data to the existing
            // String stackStr
            stackStr = stackStr + currentNode.data + ",";
            // Go to next node
            currentNode = currentNode.next;
        }
        // The String containing all the values will be returned
        // by the function
        return stackStr;
    }

}
