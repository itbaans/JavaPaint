package GradinetWindow;
public class HSVtoRGB {

    public int r;
    public int g;
    public int b;

    public HSVtoRGB(double hue, double saturation) {
             
        double huePrime = hue/60;
        double maxRGB = 1.0;
        
        ///double x2 = Math.abs((huePrime%2)-1);
        double x1 = 1 - Math.abs((huePrime%2)-1);
        double range = saturation*maxRGB;
        double min = maxRGB - range;

        if (huePrime >= 0 && huePrime < 1) {
            r = (int)(maxRGB*255);
            g = (int)((range*x1+min)*255);
            b = (int)(min*255);
            //System.out.println(r+" "+g+" "+b);
                             
        }
        else if (huePrime >= 1 && huePrime < 2) {
            r = (int)((range*x1+min)*255);
            g = (int)(maxRGB*255);
            b = (int)(min*255);
            //System.out.println(r+" "+g+" "+b);
                       
        }
        else if (huePrime >= 2 && huePrime < 3) {
            r = (int)(min*255);
            g = (int)(maxRGB*255);
            b = (int)((range*x1+min)*255);
            //System.out.println(r+" "+g+" "+b);
           
            
        }
        else if (huePrime >= 3 && huePrime < 4) {
            r = (int)(min*255);
            g = (int)((range*x1+min)*255);
            b = (int)maxRGB*255;
            //System.out.println(r+" "+g+" "+b);
            
            
        }
        else if (huePrime >= 4 && huePrime < 5) {
            r = (int)((range*x1+min)*255);
            g = (int)(min*255);
            b = (int)maxRGB*255;
            //System.out.println(r+" "+g+" "+b);
            
            
        }else if (huePrime >= 5 && huePrime < 6) {
            r = (int)(maxRGB*255);
            g = (int)(min*255);
            b = (int)((range*x1+min)*255);
            //System.out.println(r+" "+g+" "+b);           
        }
        else {
            r=0;
            b=0;
            g=0;
        }

        //System.out.println(hue+" "+huePrime+" "+x1+" "+min);
        //System.out.println(min);       
    }
}
