package sample;

        import javafx.application.Application;
        import javafx.application.Platform;
        import javafx.geometry.Insets;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Spinner;
        import javafx.scene.layout.GridPane;
        import javafx.scene.text.Text;
        import javafx.stage.Stage;

        import java.util.Scanner;


public class Main extends Application {


        private Scene scene;
        //private Scene secoundscene;

        public static Stage primaryStage;

        Board board = new Board();
        public static int x;
        public static int y;

        private Parent createContent() {
            GridPane lobby = new GridPane();
            lobby.setHgap(2);
            lobby.setVgap(2);
           lobby.setPadding(new Insets(10, 20, 10, 20));

            Text row = new Text("Row");
            lobby.add(row, 0, 0);

            Text column = new Text("Column");
            lobby.add(column, 0, 1);

            Spinner rowspinner = new Spinner(10, 100, 1);
            lobby.add(rowspinner, 1, 0);

            Spinner columnspinner = new Spinner(10, 100, 1);
            lobby.add(columnspinner, 1, 1);

            Button accept = new Button("Accept");
            lobby.add(accept, 1, 2);

            accept.setOnAction(__ ->
            {
                scene = board.createContent(primaryStage);
                primaryStage.setScene(scene);
                //restart(primaryStage);
                primaryStage.show();
                restart();
                System.out.println("jestem tu");
            });

            return lobby;
        }



        public  void restart(){

            Platform.runLater( () -> {
                try {
                    new Main().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        public void start(Stage stage) throws Exception {
            primaryStage = stage;
            primaryStage.setResizable(false);
            scene = new Scene(createContent());

           stage.setScene(scene);
            stage.show();


       }


        public static void main(String[] args) {
            launch(args);
        }
    }