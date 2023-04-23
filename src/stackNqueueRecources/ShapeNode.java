package stackNqueueRecources;
import ShapesClasses.*;

public class ShapeNode {
           
    Shape data;
    ShapeNode next;
    
    public ShapeNode(Shape n) {
        data = n;
        next = null;
    }

}
