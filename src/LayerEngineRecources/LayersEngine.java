package LayerEngineRecources;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import gui.LayerButton;
import stackNqueueRecources.*;

public class LayersEngine {
    
    private Node head;
    private int count = 0;
    private int x;
    private int y;
    private int height;
    private int width;
    private int store;

    public LayersEngine(int x, int y, int height, int width) {

        this.x = x;
        this.y =y;
        this.height = height;
        this.width = width;
        store = y;
        
    }

    public void append () {
       
        if(count < 14) {
            
            LayerButton newButton = new LayerButton("Layer: "+count, x, y-=height, height, width, Color.WHITE);
            if(count==0) newButton.SetPressed(true);
            Stack stack = new Stack();
            Stack undoRedo = new Stack();
            Node temp = new Node(newButton, count, stack, undoRedo);
            if ( head == null ) {         
                head = temp;
                //System.out.println(length());
                count++;
                return ;
            }
            else {
                head.up = temp;
                temp.down = head;
                head = temp;
                //System.out.println(length());
                count++;
            }
        }
        
    }

    public void drawData(Graphics g) {

        Node temp = head;
        while(temp != null) {
            temp.button.drawButtonShapeWithText(g);
            temp = temp.down;
        }       
    }

    public void moveUp() {

        Node node = clickedNode();
        //System.out.println(node.index);

        if(node.up == null) return;
        Node temp = node.up;  

        int tempY = node.button.y;
        node.button.y = temp.button.y;
        temp.button.y = tempY;
        

        LayerButton tempButton = node.button;
        node.button = temp.button;
        temp.button = tempButton;

        Stack tempStack = node.stack;
        node.stack = temp.stack;
        temp.stack = tempStack;

        Stack tempQ = node.undoRedo;
        node.undoRedo = temp.undoRedo;
        temp.undoRedo = tempQ;
               
        reIndexing();

    }

    public void moveDown() {

        Node node = clickedNode();

        if(node.down == null) return;
        Node temp = node.down;
        
        int tempY = node.button.y;
        node.button.y = temp.button.y;
        temp.button.y = tempY;
        
        LayerButton tempButton = node.button;
        node.button = temp.button;
        temp.button = tempButton;

        Stack tempStack = node.stack;
        node.stack = temp.stack;
        temp.stack = tempStack;

        Stack tempQ = node.undoRedo;
        node.undoRedo = temp.undoRedo;
        temp.undoRedo = tempQ;
        
        reIndexing();
        
                 
    }

    private Node clickedNode() {
        if(head != null) {

            if(head.button.IsPressed()) {
                
                return head;
            }

            Node current = head;

            while(current != null) {
                if(current.button.IsPressed()) {
                return current;
                }
                current = current.down;
            }
        }
        return null;
    }


    public void remove() {

        if(head != null) {

            if(head.button.IsPressed() && head.down!=null) {
                head = head.down;
                head.up = null;
                head.button.SetPressed(true);
                reIndexingWithY();
                y+=height;
                count--;
                return;
            }
        
            Node current = head;
            while(current.down != null) {
                
                if(current.button.IsPressed()) {

                    Node tempUp = current.up;
                    Node tempDown = current.down;
                    tempUp.button.SetPressed(true);
                    tempUp.down = current.down;
                    tempDown.up = current.up;
                    y+=height;
                    reIndexingWithY();
                    count--;

                }

                current = current.down;
            }
        }

    }


    private void reIndexing() {

        int i = length()-1;

        if(head != null) {

            Node current = head;

            while(current != null) {

                current.button.setTitle("Layer: "+i);
                //current.index = i;
                //System.out.println(current.button.getTitle());
                //System.out.println("index: "+i);
                i--;
                current = current.down;
            }
        }
    }


    private void reIndexingWithY() {

        int i = length()-1;
        store = store-((i+1)*height);

        if(head != null) {

            Node current = head;

            while(current != null) {

                current.index = i;
                current.button.setTitle("Layer: "+i);
                //System.out.println("index: "+i);
                //if(current.up != null) System.out.println("up: "+ current.up.index);
                //if(current.down != null) System.out.println("down :" +current.down.index);                
               
                current.button.y = store;
                store+=height;
                i--;
                current = current.down;
            }

        }
        
    }

    public int length() {

        int length = 0;

        if(head != null) {

            Node current = head;
            while(current != null) {
                length++;
                current = current.down;
            }
        }

        return length;
    }

    public void buttonClicked(int x, int y) {

        if(head != null) {

            if(head.button.IsClicked(x, y)) {
                int indexClicked = head.index;
                //System.out.println("clicked: "+indexClicked);
                releaseRest(indexClicked);
                return;
            }

            Node current = head;

            while(current != null) {
                if(current.button.IsClicked(x, y)) {
                int indexClicked = current.index;
                //System.out.println("clicked: "+indexClicked);
                releaseRest(indexClicked);
                break;
                }
                current = current.down;
            }


        }
    }

    private void releaseRest(int index) {
        Node current = head;
        //reIndexing();
        while(current != null) {
            //System.out.println("clicked: "+ index);
            if(current.index != index){
                current.button.SetPressed(false);
                //System.out.println("not clikced: "+current.index);
            } 
            current = current.down;
        }

    }
    
    public Stack getStack() {
        Node node = clickedNode();
        if(node != null)
        return node.stack;
        else return null;
    }

    public Stack getUndoRedo() {
        Node node = clickedNode();
        if(node != null) return node.undoRedo;
        else return null;
    }

    public void drawLayerElements(Graphics2D g) {

        Node temp = head;
        ArrayList<Stack> data = new ArrayList<>();
        while(temp!=null) {
            if(temp.stack != null)
            data.add(temp.stack);
            temp = temp.down;
        }

        if(data.size() != 0) {
            for(int j = data.size()-1; j >= 0; j--) {
                if(data.get(j)!=null)
                data.get(j).drawAll(g);
            }
        }


    }

}
