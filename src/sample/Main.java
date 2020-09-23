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



public class Main extends Application {


        private Scene scene;

        public static Stage primaryStage;

        Board board = new Board();

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

            accept.setOnAction(e -> primaryStage.setScene(board.createContent((int)rowspinner.getValue(), (int)columnspinner.getValue())));

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

    public Scene createContentInfo(String information) {
        GridPane informationForUser = new GridPane();
        informationForUser.setHgap(1);
        informationForUser.setVgap(0);

        Text row = new Text(information);
        informationForUser.add(row, 0, 0);

        Button accept = new Button("Accept");
        informationForUser.add(accept, 1, 0);

        accept.setOnAction(e -> board.restart());

        scene = new Scene(informationForUser);
        return scene;
    }

        @Override
        public void start(Stage stage) throws Exception {
            primaryStage = stage;
            stage = primaryStage;
            primaryStage.setResizable(false);
            scene = new Scene(createContent());

           stage.setScene(scene);
           stage.setTitle("MineSweeper");
            stage.show();


       }


        public static void main(String[] args) {
            launch(args);
        }
    }