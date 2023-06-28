import java.awt.*;
import java.util.ArrayList;

public class MessageReader {
    /**
     * parses a string with an action to perform and relevant data given to perform it
     * @param inp input message that needs to be understood/parsed
     * @param sketch sketch we make changes to
     */
    public static void readMessage(String inp, Sketch sketch){
        String[] inpList = inp.split(" ");
        if(inpList[0].equals("draw")){ //if action is draw
            Shape reqShape = null; // shape to be added to sketch
            if(inpList[1].equals("rectangle")){ // set reqShape to new rectangle with given info
                reqShape = new Rectangle(Integer.parseInt(inpList[2]),Integer.parseInt(inpList[3]),
                        Integer.parseInt(inpList[4]),Integer.parseInt(inpList[5]),new Color(Integer.parseInt(inpList[6])));
            }
            else if(inpList[1].equals("ellipse")){ // set reqShape to new ellipse with given info
                reqShape = new Ellipse(Integer.parseInt(inpList[2]),Integer.parseInt(inpList[3]),
                        Integer.parseInt(inpList[4]),Integer.parseInt(inpList[5]),new Color(Integer.parseInt(inpList[6])));
            }
            else if(inpList[1].equals("segment")){ // set reqShape to new segment with given info
                reqShape = new Segment(Integer.parseInt(inpList[2]),Integer.parseInt(inpList[3]),
                        Integer.parseInt(inpList[4]),Integer.parseInt(inpList[5]),new Color(Integer.parseInt(inpList[6])));
            }
            else if(inpList[1].equals("polyline")){ // set reqShape to new polyline with given info
                ArrayList<Point> points = new ArrayList<>(); // make a list of points
                for (int i = 2; i < inpList.length-1; i+=2) { // iterate via everything given in inp after action and shape
                                                              // second last string (color), all these are points that need to be added
                    Point point = new Point(Integer.parseInt(inpList[i]), Integer.parseInt(inpList[i+1]));
                }
                reqShape = new Polyline(points, new Color(Integer.parseInt(inpList[inpList.length-1])));
            }
            if(reqShape!=null){ // add shape to sketch object's TreeMap
                sketch.addToShapeMap(reqShape);
            }
        }
        else if(inpList[0].equals("delete")){ // calls delete method on given ID
            sketch.deleteFromShapeMap(Integer.parseInt(inpList[1]));
        }
        else if(inpList[0].equals("move")){ // calls move on given ID with given dx and dy
            sketch.moveShape(Integer.parseInt(inpList[1]), Integer.parseInt(inpList[2]), Integer.parseInt(inpList[3]));
        }
        else if(inpList[0].equals("recolor")){ // calls recolor method on given id and with given color
            sketch.recolorShape(Integer.parseInt(inpList[1]), new Color(Integer.parseInt(inpList[2])));
        }
    }
}
