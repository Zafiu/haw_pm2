import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import simulation_observer.IBeobachtbar;
import simulation_observer.IBeobachter;
import simulation_observer.Zug;

public class Applikation extends Application implements IBeobachter {
    public Path get_path() {
        return _path.get();
    }

    public ObjectProperty<Path> _pathProperty() {
        return _path;
    }

    public void set_path(Path _path) {
        this._path.set(_path);
    }

    private ObjectProperty<Path> _path = new SimpleObjectProperty<>();


    public Applikation() {

        Path path = new Path();
        set_path(path);
        _path.addListener(new ChangeListener<Path>() {
            @Override
            public void changed(ObservableValue<? extends Path> observableValue, Path path, Path t1) {
                System.out.println("Wert hat sich geändert!");

            }
        });

    }

    @Override
    public void start(Stage primaryStage) {
        Simulation simulation = new Simulation();
        simulation.anmelden(this);
        Thread simulationThread = new Thread(simulation);

        StackPane root = new StackPane();
        root.getChildren().add(get_path());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);

        primaryStage.setTitle("Bahnhof Simulation");
        primaryStage.show();
        simulationThread.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void aktualisieren(IBeobachtbar b) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                get_path().getElements().clear();

                Zug[] zug = b.gleisZustand();
                get_path().getElements().clear();
                double yAchse = 0;
                for (int x = 0; x <= zug.length - 1; x++) {

                    if (zug[x] != null) {
                        zeichneMitZug(yAchse);
                    } else {
                        zeichneLeereGleise(yAchse);
                    }

                    yAchse += 50;
                }
            }
        });
    }

    public void zeichneLeereGleise(double yAchse) {
        MoveTo moveTo = new MoveTo();
        moveTo.setX(0.0);
        moveTo.setY(yAchse);

        LineTo lineTo = new LineTo();
        lineTo.setX(200);
        lineTo.setY(yAchse);

        MoveTo moveToTwo = new MoveTo();
        moveToTwo.setX(0.0);
        moveToTwo.setY(yAchse);

        LineTo lineToTwo = new LineTo();
        lineToTwo.setX(0);
        lineToTwo.setY(yAchse + 50);

        MoveTo moveToFour = new MoveTo();
        moveToFour.setX(0.0);
        moveToFour.setY(yAchse + 50);

        LineTo lineToFour = new LineTo();
        lineToFour.setX(200);
        lineToFour.setY(yAchse + 50);


        final Path _path = get_path();

        _path.getElements().add(moveTo);
        _path.getElements().add(lineTo);
        _path.getElements().add(moveToTwo);
        _path.getElements().add(lineToTwo);
        _path.getElements().add(moveToFour);
        _path.getElements().add(lineToFour);
    }

    public void zeichneMitZug(double yAchse) {
        zeichneLeereGleise(yAchse);

        MoveTo moveToTrain = new MoveTo();
        moveToTrain.setX(20);
        moveToTrain.setY(yAchse + 10);

        LineTo lineToTrain = new LineTo();
        lineToTrain.setX(180);
        lineToTrain.setY(yAchse + 10);//x

        MoveTo moveToTrainTwo = new MoveTo();
        moveToTrainTwo.setX(20);
        moveToTrainTwo.setY(yAchse + 10);

        LineTo lineToTrainTwo = new LineTo();
        lineToTrainTwo.setX(20);
        lineToTrainTwo.setY(yAchse + 40);

        MoveTo moveToTrainFour = new MoveTo();
        moveToTrainFour.setX(20);
        moveToTrainFour.setY(yAchse + 40);

        LineTo lineToTrainFour = new LineTo();
        lineToTrainFour.setX(180);
        lineToTrainFour.setY(yAchse + 40);

        MoveTo moveToTrainFive = new MoveTo();
        moveToTrainFive.setX(180);
        moveToTrainFive.setY(yAchse + 10);

        LineTo lineToTrainFive = new LineTo();
        lineToTrainFive.setX(180);
        lineToTrainFive.setY(yAchse + 40);

        final Path _path = get_path();

        _path.getElements().add(moveToTrain);
        _path.getElements().add(lineToTrain);
        _path.getElements().add(moveToTrainTwo);
        _path.getElements().add(lineToTrainTwo);
        _path.getElements().add(moveToTrainFour);
        _path.getElements().add(lineToTrainFour);
        _path.getElements().add(moveToTrainFive);
        _path.getElements().add(lineToTrainFive);
    }

}
