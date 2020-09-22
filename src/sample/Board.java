package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Board {



        private static final int TILE_SIZE = 40;
        private int W, H, b;
        private int winNumb;


        private Tile[][] grid;
        public static Scene scene1;
        public static Scene scene2;

    //InformationScene luse_win_scene = new InformationScene();


    public Scene createContent(int w, int h) {
        W = w * TILE_SIZE;
        H = h * TILE_SIZE;
            Pane root = new Pane();
            root.setPrefSize(W, H);
        grid = new Tile[w][h];

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    Tile tile = new Tile(x, y, Math.random() < 0.1);

                    grid[x][y] = tile;
                    root.getChildren().add(tile);
                }
            }

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    Tile tile = grid[x][y];

                    if (tile.hasBomb)
                        continue;

                    long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();

                    if (bombs > 0)
                        tile.text.setText(String.valueOf(bombs));
                }
            }

            scene1 = new Scene(root);
            return scene1;
    }

        private List<Tile> getNeighbors(Tile tile) {
            List<Tile> neighbors = new ArrayList<>();


            int[] points = new int[] {
                    -1, -1,
                    -1, 0,
                    -1, 1,
                    0, -1,
                    0, 1,
                    1, -1,
                    1, 0,
                    1, 1
            };

            for (int i = 0; i < points.length; i++) {
                int dx = points[i];
                int dy = points[++i];

                int newX = tile.x + dx;
                int newY = tile.y + dy;

                if (newX >= 0 && newX < W / 40
                        && newY >= 0 && newY < H / 40) {
                    neighbors.add(grid[newX][newY]);
                }
            }

            return neighbors;
        }

        private class Tile extends StackPane {
            private int x, y;
            private boolean hasBomb;
            private boolean isOpen = false;

            private Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
            private Text text = new Text();

            public Tile(int x, int y, boolean hasBomb) {
                this.x = x;
                this.y = y;
                this.hasBomb = hasBomb;

                border.setStroke(Color.LIGHTGRAY);

                text.setFont(Font.font(18));
                text.setText(hasBomb ? "X" : "");
                text.setVisible(false);

                getChildren().addAll(border, text);

                setTranslateX(x * TILE_SIZE);
                setTranslateY(y * TILE_SIZE);

                setOnMouseClicked(e -> open());
            }

            public void open() {
                if (isOpen) {
                    return;
                }

                if (hasBomb) {
                    Main.primaryStage.close();
                    Main.primaryStage.setScene(createContent("You Luse"));
                    Main.primaryStage.show();
                    return;
                }
                winNumb ++;

                if (winNumb == ((W / 40 * H / 40) * 0.9) - 1 ) {
                    Main.primaryStage.close();
                    Main.primaryStage.setScene(createContent("You Win"));
                    Main.primaryStage.show();
                }
                isOpen = true;
                text.setVisible(true);
                border.setFill(null);


                if (text.getText().isEmpty()) {
                    getNeighbors(this).forEach(Tile::open);
                }
            }
        }

    public  void restart(){
        Main.primaryStage.close();
        Platform.runLater( () -> {
            try {
                new Main().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Scene createContent(String information) {
        GridPane informationForUser = new GridPane();
        informationForUser.setHgap(0);
        informationForUser.setVgap(1);


        Button accept = new Button(information);
        accept.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        informationForUser.add(accept, 0, 1);

        accept.setOnAction(e -> restart());

        scene2 = new Scene(informationForUser);
        return scene2;
    }

}
