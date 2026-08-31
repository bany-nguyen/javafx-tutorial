import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;

import java.util.Objects;

public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/DaUser.png")));
    private Image dukeImage =  new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/DaDuke.png")));
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane(); //Scrollable viewport
        dialogContainer = new VBox(); //VBox stacks its children vertically
        scrollPane.setContent(dialogContainer); //

        userInput = new TextField();
        sendButton = new Button("Send");

        // Event-driven style
        sendButton.setOnMouseClicked(event -> {
            handleUserInput();
        });

        userInput.setOnAction(event -> {
            handleUserInput();
        });

        AnchorPane mainLayout = new AnchorPane();

        Scene scene = new Scene(mainLayout); // Setting the scene to be our Label

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);

        mainLayout.setPrefSize(400, 600);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1);
        scrollPane.setFitToWidth(true);

        // Dialog container
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //...


        userInput.setPrefWidth(325);

        sendButton.setPrefWidth(55);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setScene(scene); // Setting the stage to show our scene
        stage.show(); // Render the stage.
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), userImage),
                DialogBox.getBotDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }

}
