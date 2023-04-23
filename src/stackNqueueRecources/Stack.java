package stackNqueueRecources;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ShapesClasses.*;

public class Stack {

    ShapeNode head;

    public void push(Shape n) {
        if (head == null)  head = new ShapeNode(n);
        else {
            ShapeNode temp = new ShapeNode(n);
            temp.next = head;
            head = temp;
        }
    }
    public Shape pop() {
        if (head == null) return null;
        else {            
            Shape data = head.data;
            head = head.next;
            return data;
        }
    }
    public void drawData(Graphics g) {
        ShapeNode temp = head;      
         if(temp!=null) {
         temp.data.draw(g);
         temp = temp.next;
        }
    }

    public void drawAll(Graphics2D g) {
        ShapeNode temp = head;
        ArrayList<Shape> data = new ArrayList<>();
        
         while(temp!=null) {
         data.add(temp.data);
         temp = temp.next;
        }
        if(data.size() != 0) {
            if(data.get(0)!=null)
            for(int j = data.size()-1; j >= 0; j--) {
                data.get(j).draw2D(g);
            }
        }
    }

    public int stackSize() {
        int i = 0;
        ShapeNode temp = head;
        while(temp != null) {
            i = i + 1;
            temp = temp.next;
        }
        return i;
    }
    
    public String getData() {
        ShapeNode temp = head;
        String info = "";      
         while(temp!=null) {
         info += temp.data.getInfo()+"\n";
         temp = temp.next;
        }
        return info;
    }
    public void clearStack() {
        
        while(head != null) {
            
            head = head.next;
        }
        
    }
}


