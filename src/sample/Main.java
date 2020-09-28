package sample;

        import javafx.application.Application;
        import javafx.geometry.Insets;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.RadioButton;
        import javafx.scene.control.Spinner;
        import javafx.scene.control.ToggleGroup;
        import javafx.scene.layout.GridPane;
        import javafx.scene.text.Text;
        import javafx.stage.Stage;



public class Main extends Application {


        private Scene scene;

        public static Stage primaryStage;
        public static Scene lobbyScene;

        Board board = new Board();

        double deficultValue;

        final ToggleGroup deficult = new ToggleGroup();

        private Parent createContent() {
            GridPane lobby = new GridPane();
            lobby.setHgap(2);
            lobby.setVgap(2);
           lobby.setPadding(new Insets(10, 20, 10, 20));

            Text row = new Text("Row");
            lobby.add(row, 0, 0);

            Text column = new Text("Column");
            lobby.add(column, 0, 1);

            Spinner rowspinner = new Spinner(10, 50, 1);
            lobby.add(rowspinner, 1, 0);

            Spinner columnspinner = new Spinner(10, 50, 1);
            lobby.add(columnspinner, 1, 1);

            RadioButton normal = new RadioButton("Normal");
            normal.setSelected(true);
            normal.setToggleGroup(deficult);
            lobby.add(normal, 2, 0);

            RadioButton hard = new RadioButton("Hard");
            hard.setToggleGroup(deficult);
            lobby.add(hard, 2, 1);

            Button accept = new Button("Accept");
            lobby.add(accept, 1, 2);

            if (normal.isSelected())
                deficultValue = 0.2;

            if (hard.isSelected())
                deficultValue = 0.4;

            accept.setOnAction(e -> primaryStage.setScene(board.createContent((int)rowspinner.getValue(), (int)columnspinner.getValue(), deficultValue)));

            return lobby;
        }


        @Override
        public void start(Stage stage) throws Exception {
            primaryStage = stage;
            stage = primaryStage;
            primaryStage.setResizable(false);
            scene = new Scene(createContent());
            lobbyScene = scene;

           stage.setScene(scene);
           stage.setTitle("MineSweeper");
            stage.show();


       }


        public static void main(String[] args) {
            launch(args);
        }
    }