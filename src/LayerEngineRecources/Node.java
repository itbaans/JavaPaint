package LayerEngineRecources;

import java.io.Serializable;

import gui.*;
import stackNqueueRecources.*;

public class Node implements Serializable {

    LayerButton button;
    Stack stack;
    Stack undoRedo;
    int index;
    Node up;
    Node down;
    

    public Node(LayerButton button, int index, Stack stack, Stack undoRedo) {

        this.button = button;
        this.index = index;
        this.up = null;
        this.down = null;
        this.stack = stack;
        this.undoRedo = undoRedo;

    }
}
