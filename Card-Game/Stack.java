package ckonta_P3;

/**
 * this contained the following function constructor, push, pop, empty, peek
 * and use a array to store element
 *
 * @author cheick
 * @version 1.0
 */
public class Stack<E> {

    // array to hold stack element
    private E[] cards;
    //point to the top of the array
    private int top;
    //array size
    final int SIZE = 52;

    /**
     * Constructor.
     */
    public Stack() {
        //assigning array
        cards = (E[]) new Object[SIZE];
        //assigning top value to zero
        top = 0;

    }

    /**
     * The empty method checks for an empty stack.
     *
     * @return true if stack is empty.
     */
    public boolean isEmpty() {
        //return top value
        return top == 0;
    }

    /**
     * The push method pushes a value onto the stack.
     *
     * @param x The value to push onto the stack.
     * @throws IllegalArgumentException When the
     *                                  stack is full.
     */
    public void push(E x) {
        //check if stack is empty
        if (top == cards.length)
            //throw exception if stack is empty
            throw new IllegalArgumentException("some text here explaining what is wrong");
        //else assign array value at top to x
        else {
            cards[top] = x;
            //increment top
            top++;
        }

    }

    /**
     * The pop method pops a value off the stack.
     *
     * @return The value popped.
     * @throws IllegalArgumentException When the
     *                                  stack is empty.
     */
    public E pop() {
        //check if stack is empty
        if (isEmpty()) {
            //throw exception if stack is empty
            throw new IllegalArgumentException("stack is empty");
        } else {
            //decrement top value
            top--;
            //return array value at top
            return cards[top];
        }
    }

    /**
     * The peek method returns the value at the
     * top of the stack.
     *
     * @return value at top of the stack.
     * @throws IllegalArgumentException When the
     *                                  stack is empty.
     */
    public E peek() {
        //check if stack is empty
        if (isEmpty()) {
            //throw exception to inform user
            throw new IllegalArgumentException("stack is empty");
        } else {
            //else return array value at top - 1
            return cards[top - 1];
        }
    }

    /**
     * build a string from input
     * @return string
     */
    public String toString(){
        //string builder object
        StringBuilder st = new StringBuilder();
        //loop through element
        for(int i = 0; i < top; i++){
            st.append(cards[i] + " ");
        }
        //return string
        return st.toString();
    }

    /**
     * copy stack to another stack
     *
     * @return copy stack
     */
    public Stack<E> copy(){
        //create a new stack object
        Stack<E> sCopy = new Stack<E>();
        //loop trough element
        for(int i = 0; i < top; i++){
            //make copy
            sCopy.push(cards[i]);
        }
        //return copy
        return sCopy;
    }
}
