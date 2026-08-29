import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label textLabel;
    private ImageView displayImage;

    public DialogBox(String s, Image i) {
        textLabel = new Label(s);
        displayImage = new ImageView(i);

        textLabel.setWrapText(true);
        displayImage.setFitWidth(100);
        displayImage.setFitHeight(100);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(textLabel, displayImage);


    }
}
