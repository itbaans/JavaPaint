package LayerEngineRecources;

import gui.*;
import stackNqueueRecources.*;

public class Node {

    LayerButton button;
    Stack stack;
    Queue queue;
    int index;
    Node up;
    Node down;
    

    public Node(LayerButton button, int index, Stack stack, Queue queue) {

        this.button = button;
        this.index = index;
        this.up = null;
        this.down = null;
        this.stack = stack;
        this.queue = queue;

    }
}
