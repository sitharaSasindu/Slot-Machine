import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Created by #Property Of Ss on 11/3/2017.
 */
public class Reel extends Thread{

    static Symbol[] symbols = new Symbol[6]; //creating array to store 6 symbols
    int randomNo;


    public int getRandomNo() {//accessing the random number
        return randomNo;
    }

    public static Symbol[] spin(){ //spin method to return symbol array
        symbols[0] = new Symbol(6, new Image("bell.png"));
        symbols[1] = new Symbol(2, new Image("cherry.png"));
        symbols[2] = new Symbol(3, new Image("lemon.png"));
        symbols[3] = new Symbol(4, new Image("plum.png"));
        symbols[4] = new Symbol(7, new Image("redseven.png"));
        symbols[5] = new Symbol(5, new Image("watermelon.png"));

        return symbols;
    }

    //---------------------------------------------------------------------------------------------

   public void spin(ImageView symbol) {//method to spin
       spin();                              //use the parameter passed from GUI class

       Platform.runLater(() -> {
           randomNo = (int) Math.floor(Math.random() * 6); //genarating a random number
           Symbol randomSymbol = symbols[randomNo]; //setting a random symbol
           symbol.setImage(randomSymbol.getImage());// setting that random symbol to image view

       });
       try {
           Thread.sleep(25);
       }  catch (InterruptedException e) {
           e.printStackTrace();
       }
    }

}

