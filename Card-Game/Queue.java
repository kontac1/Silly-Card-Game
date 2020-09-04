package ckonta_P3;

/**
 * thw RenderQueue class is use a doubly linked list to append, copy,
 * dequeue, enqueue, fromString, toString and empty function to complete
 * the L-System program. Each function is explain below in more details
 *
 * @author Cheick
 * @version 1.0
 */
public class Queue<E> {
    /**
     * the doubly linked list class is use to append, dequeue, enqeue list element
     */
    private class Node {
        //store render elements`
        public E Var;
        //connect elements at a specific points
        public Node next, prev;

        /**
         * the doubly linked list constructor is use to assign variables
         *
         * @param Var  elements in the list
         * @param prev connector for previous element
         * @param next connector for next elememt
         */
        public Node(E Var, Node prev, Node next) {
            //assigning render element to render
            this.Var = Var;
            //assigning previous element to prev(Previous)
            this.prev = prev;
            //assigning next element to next
            this.next = next;
        }
    }

    //variable to point to node head
    private Node head;
    //variable to point to node tail
    private Node tail;

    /**
     * renderQueue function is used to assigned node head and tail
     */
    public Queue() {
        //assign head to null
        head = null;
        //assign tail to null
        tail = null;

    }


    /**
     * Enqueue all the elements from another queue onto this queue.
     *
     * @param other the queue with the elements to enqueue
     */
    public void append(Queue other) {
        //create a new render object
        Queue RQ = other.copy();
        //loop throw element to enqueue
        for (Node x = RQ.head; x != null; x = x.next)
            //enqueue element 
            enqueue(x.Var);
    }

    /**
     * this function make a deep copy of the queue.
     *
     * @return the new queue you created.
     */
    public Queue<E> copy() {
        //create a new renderqueue object
        Queue<E> theCopy = new Queue();
        //loop to go through elements
        for (Node p = head; p != null; p = p.next) {
            //copy element to the new renderqueue object
            theCopy.enqueue(p.Var);

        }
        //return the new queue
        return theCopy;
    }

    /**
     * Remove an element from the front of the queue (oldest element in the queue).
     *
     * @return oldest element's payload
     * @throws exception message if queue is empty
     */
    public E dequeue() {
        //checking if head is equal to null
        if (head == null) {
            //if head equal null throw exception
            throw new IllegalArgumentException("cannot dequeue from " + "empty queue");
        }
        //assign RenderCommand to first element
        E Variable = head.Var;
        //check if first and previous element are null
        if (head.next == null && head.prev == null) {
            //assign first element to null
            head = null;
            //assign last element to null
            tail = null;
        } else {
            //else assign first element to the following
            head = head.next;
            //assign previous element to null
            head.prev = null;

        }
        //return oldest element
        return Variable;
    }

    /**
     * Add an element to the end of the queue.
     *
     * @param data new element's payload
     */
    public void enqueue(E data) {
        //check if head is null
        if (head == null) {
            //create a newNode object by passing in data element, prev, and next
            Node newNode = new Node(data, null, null);
            //assign last element to newNode
            tail = newNode;
            //assign first element to newNode
            head = newNode;

        } else {
            //create newNode object with same argument
            Node newNode = new Node(data, null, null);
            //assign new node previous to tail
            newNode.prev = tail;
            //assign tail next to newNode
            tail.next = newNode;
            //taill is equal to new node
            tail = newNode;

        }
    }

    /**
     * String representation of the queue contents.
     * Uses traditional notation for the render commands.
     *
     * @return the string representation
     */
    public String toString() {
        //create a string build object
        StringBuilder s = new StringBuilder();
        //loop through node element to append the right characters
        for (Node p = head; p != null; p = p.next) {
            s.append(p.Var).append(" ");
        }
        //return the string representation
        return s.toString();
    }

    /**
     * Reports if the queue has any elements.
     *
     * @return true if the queue has zero elements, false otherwise
     */
    public boolean empty() {
        //check if the queue is empty and return true
        return head == null && tail == null;
    }

    /**
     * the peek function check the number on the top of queue without removing it
     *
     * @return the first number in queue
     */
    public E peek() {
        //check if queue is empty
        if (empty()) {
            //inform user if queue is empty
            throw new IllegalArgumentException("Cannot peek empty queue");
        }
        //else return first variable
        return head.Var;
    }


}
