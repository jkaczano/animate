import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.image.BufferedImage;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.WHITE;

public class asyncTask extends Task {

    GraphicsContext g;
    Canvas canvas;
    double d,h;
    public asyncTask(Canvas canvas,double d,double h){
        this.g = canvas.getGraphicsContext2D();
        this.canvas = canvas;
        this.d = d;
        this.h = h;
    }


    @Override
    protected Object call() throws Exception {
        double x,y,xa,ya,v0;
        v0 = Math.sqrt(2*9.81*h);
        BufferedImage img = new BufferedImage((int)(canvas.getWidth()-d-10),(int)canvas.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        climg(img);
        for (int j = 0; j <= h+1; j++) {
            Thread.sleep(100);
            climg(img);
            for (double i = 0.2; i < Math.sqrt(2 * canvas.getHeight() / 2 / 9.81); i += 0.1) {
                x = v0 * (i-0.1);
                y = 9.81 / (2 * v0 * v0) * x * x;
                xa = v0 * i;
                ya = 9.81 / (2 * v0 * v0) * xa * xa;
                g2d.setPaint(java.awt.Color.BLUE);
                g2d.drawLine((int)(x), (int)(canvas.getHeight() / 2 + y), (int) xa,(int)(canvas.getHeight() / 2  + ya));

            }
            g.setFill(WHITE);
            g.fillRect(5,0,d,canvas.getHeight()/2 - h + j);
            g.drawImage(SwingFXUtils.toFXImage(img, null),d+10 ,0);
            if(v0>0.2)
            v0=Math.sqrt(2*9.81*(h-j));
        }
        return null;
    }

    void climg(BufferedImage img){
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                img.setRGB(i,j,java.awt.Color.WHITE.getRGB());
            }
        }
    }
}
