import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private Button sendButton;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage;
    private Image dukeImage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userImage = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/images/DaUser.png")));
        dukeImage = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/images/DaDuke.png")));
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
