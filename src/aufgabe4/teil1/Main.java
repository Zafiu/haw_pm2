package teil1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Main extends Application {
    public static class TableData {
        SimpleStringProperty filename, path;
        SimpleLongProperty size;

        public TableData(String filename, String path, Long size) {
            this.filename = new SimpleStringProperty(filename);
            this.path = new SimpleStringProperty(path);
            this.size = new SimpleLongProperty(size);
        }

        public String getFilename() {
            return filename.get();
        }

        public void setFilename(String filename) {
            this.filename.set(filename);
        }

        public String getPath() {
            return path.get();
        }

        public void setPath(String path) {
            this.path.set(path);
        }

        public Long getSize() {
            return size.getValue();
        }

        public void setSize(Long size) {
            this.size.setValue(size);
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        primaryStage.setTitle("Dateirecherche");

        TableView table = new TableView<TableData>();
        table.setMinWidth(300);
        TableColumn firstColumn = new TableColumn("Name");
        firstColumn.setCellValueFactory(new PropertyValueFactory<TableData, String>("filename"));

        TableColumn secondColumn = new TableColumn("Pfad");
        secondColumn.setCellValueFactory(new PropertyValueFactory<TableData, String>("path"));

        TableColumn thirdColumn = new TableColumn("Größe (Byte)");
        thirdColumn.setMinWidth(150);
        thirdColumn.setCellValueFactory(new PropertyValueFactory<TableData, String>("size"));

        table.getColumns().addAll(firstColumn, secondColumn, thirdColumn);
        thirdColumn.setSortType(TableColumn.SortType.DESCENDING);
        table.getSortOrder().add(thirdColumn);

        Label dirLabel = new Label("Pfad eingabe:");
        TextField dirField = new TextField();
        Label dirCountLabel = new Label("Anzahl Dateien:");
        TextField dirCountField = new TextField();
        dirCountField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        Button submit = new Button("Suche Starten");

        GridPane.setConstraints(dirLabel, 0, 0);
        GridPane.setConstraints(dirField, 1, 0);
        GridPane.setConstraints(dirCountLabel, 0, 1);
        GridPane.setConstraints(dirCountField, 1, 1);
        GridPane.setConstraints(submit, 0, 2);
        GridPane.setConstraints(table, 1, 6);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Path path = (dirField.getText().equals(".")) ?
                                Paths.get(System.getProperty("user.dir")) :
                                Paths.get(dirField.getText());

                        if (path.isAbsolute() && dirCountField.getText().length() > 0 && Integer.parseInt(dirCountField.getText()) > 0) {
                            table.getItems().clear();
                            int countFiles = Integer.parseInt(dirCountField.getText());
                            FileWalker fileWalker = new FileWalker();
                            fileWalker.walk(path.toString());
                            ArrayList<File> filesList = fileWalker.getfilesList();
                            fileWalker.sortList();
                            for (int x = 0; x < countFiles; x++) {
                                if (x >= filesList.size()) {
                                    break;
                                }

                                table.getItems().addAll(new TableData(
                                        filesList.get(x).getName(),
                                        filesList.get(x).getAbsolutePath(),
                                        filesList.get(x).length())
                                );
                            }
                        }

                    }
                });
            }
        });

        root.getChildren().addAll(
                dirLabel,
                dirField,
                dirCountLabel,
                dirCountField,
                submit,
                table
        );
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
