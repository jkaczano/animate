import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.Color.*;
import static javafx.scene.paint.Color.BLUE;

public class controller implements Initializable {

    @FXML
    Canvas canvas;
    @FXML
    TextField height;
    @FXML
    TextField width;
    GraphicsContext g;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        g = canvas.getGraphicsContext2D();
        g.fillRect(0,canvas.getHeight()/2,50,canvas.getHeight()/2);
        g.fillRect(0,0,5,canvas.getHeight()/2);
        g.fillRect(45,0,5,canvas.getHeight()/2-2);
        g.setFill(BLUE);
        g.fillRect(5,0,40,canvas.getHeight()/2);
    }
    @FXML
    public void handleInit(){
        double h = Double.parseDouble(height.getText());
        double d = Double.parseDouble(width.getText());
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.setFill(Color.BLACK);
        g.fillRect(0,canvas.getHeight()/2,d+10,canvas.getHeight()/2);
        g.fillRect(0,canvas.getHeight()/2-h,5,h);
        g.fillRect(d+5,canvas.getHeight()/2-h,5,h-2);
        g.setFill(BLUE);
        g.fillRect(5,canvas.getHeight()/2-h,d,h);
    }

    @FXML
    public void handleStart() {
        double x, y, v0 = 50;
        double d = Double.parseDouble(width.getText());
        double h = Double.parseDouble(height.getText());
        asyncTask task = new asyncTask(canvas,d,h);
        new Thread(task).start();
    }
}
