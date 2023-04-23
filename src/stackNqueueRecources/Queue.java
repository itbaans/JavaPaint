package stackNqueueRecources;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ShapesClasses.*;

public class Queue {

    private ShapeNode head;
    private ShapeNode tail;
    private int length = 0;

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(Shape item) {
        ShapeNode newNode = new ShapeNode(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public Shape dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        Shape data = head.data;
        head = head.next;
        if (isEmpty()) {
            tail = null;
        }
        length--;
        return data;
    }

    public void Show() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        ShapeNode temp = head;
        while(temp!=null)
        {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
    
    public void insert(int index, Shape item) {
        if (index < 0 || index > size()) {
            System.out.println("Index is out of bounds");
            return;
        }
        if (index == 0) {
            ShapeNode newNode = new ShapeNode(item);
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = head;
            }
            length++;
            return;
        }
        ShapeNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        ShapeNode newNode = new ShapeNode(item);
        newNode.next = current.next;
        current.next = newNode;
        if (tail == current) {
            tail = newNode;
        }
        length++;
    }
    
    public int size()
    {
        return length;
    }
    
    public void merge(Queue q)
    {
        this.tail.next = q.head;
        this.tail = q.tail;
    }
}
    
