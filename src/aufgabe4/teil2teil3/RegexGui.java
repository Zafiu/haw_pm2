package teil2teil3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.regex.Pattern;


public class RegexGui extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        primaryStage.setTitle("Regex");


        Label regexLabel = new Label("Regex:");
        TextField regexField = new TextField();
        Label inputLabel = new Label("Input:");
        TextField inputField = new TextField();
        Button submit = new Button("Regex Überprüfen");

        GridPane.setConstraints(regexLabel, 0, 0);
        GridPane.setConstraints(regexField, 1, 0);
        GridPane.setConstraints(inputLabel, 0, 1);
        GridPane.setConstraints(inputField, 1, 1);
        GridPane.setConstraints(submit, 0, 2);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(!regexField.getText().isEmpty() && !inputField.getText().isEmpty()) {
                            if(Pattern.matches(regexField.getText(), inputField.getText())) {
                                regexField.setStyle("-fx-control-inner-background: green");
                            } else {
                                regexField.setStyle("-fx-control-inner-background: red");
                            }
                        }
                    }
                });
            }
        });

        root.getChildren().addAll(
                regexLabel,
                regexField,
                inputLabel,
                inputField,
                submit
        );
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
