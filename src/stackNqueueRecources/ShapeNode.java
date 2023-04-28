package stackNqueueRecources;
import java.io.Serializable;

import ShapesClasses.*;

public class ShapeNode implements Serializable {
           
    Shape data;
    ShapeNode next;
    
    public ShapeNode(Shape n) {
        data = n;
        next = null;
    }

}
