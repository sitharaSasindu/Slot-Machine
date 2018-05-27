import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by #Property Of Ss on 11/3/2017.
 */
public class Symbol implements iSymbol {

    private int value = 0; //symbol value
    private Image image = null;

    public Symbol(int value, Image image) {//constructor to set image and value
        this.value = value;
        this.image = image;
    }

    //setters and getters
    public void setValue(int v) {
        this.value = v;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getValue() {
        return value;
    }

    public Image getImage() {
        return image;
    }
}
