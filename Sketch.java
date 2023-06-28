import java.awt.*;
import java.util.NavigableSet;
import java.util.TreeMap;

public class Sketch {
    private static TreeMap<Integer, Shape> idShapeMap;
    private static int id = 0;

    /**
     * Constructor creates a new map of shapes
     */
    public Sketch() {
        idShapeMap = new TreeMap<>();
    }

    /**
     * adds the shape to maps of sketches and assigns it a unique id
     * @param shape
     */
    public synchronized void addToShapeMap(Shape shape) {
        idShapeMap.put(id,shape);
        id += 1;
    }

    /**
     * deletes from maps of sketches at ID
     * @param ID
     * @return
     */
    public synchronized Shape deleteFromShapeMap(int ID){
        return idShapeMap.remove(ID); // if ID not a key in idShapeMap, returns null
    }

    /**
     * helper method for Editor gets shape at ID
     * @param ID
     * @return
     */
    public synchronized Shape getShape(int ID){
        return idShapeMap.get(ID); // if ID not a key in idShapeMap, returns null
    }

    /**
     * moves shape in idShapeMap at ID by dx and dy
     * @param ID
     * @param dx
     * @param dy
     */
    public synchronized void moveShape(int ID, int dx, int dy) {
        if(idShapeMap.containsKey(ID)){
            idShapeMap.get(ID).moveBy(dx,dy);
        }
    }

    /**
     * Sets the color of the shape in idShapeMap at ID to newColor
     * @param ID
     * @param newColor
     */
    public synchronized void recolorShape(int ID, Color newColor){
        if(idShapeMap.containsKey(ID)){
            idShapeMap.get(ID).setColor(newColor);
        }
    }

    /**
     * helper method; gets ids sorted by recency of addition while drawing everything
     * @return
     */
    public synchronized NavigableSet<Integer> getSortedIds(){
        return idShapeMap.navigableKeySet();
    }

    /**
     * helper method for Editor; checks if point exists in any shape in idShapeMap and then returns the id of
     * the topmost shape to performs actions on
     * @param point
     * @return
     */
    public synchronized int getIdOfTopShape(Point point){
        for(int ID:idShapeMap.descendingKeySet()){
            if(idShapeMap.get(ID).contains((int)point.getX(),(int)point.getY())){
                return ID;
            }
        }
        return -1;
    }

}
