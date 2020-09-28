package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserInformation {

    public static Scene scene2;


    public static Scene createContent(String information) {
        GridPane informationForUser = new GridPane();
        informationForUser.setHgap(0);
        informationForUser.setVgap(1);


        Button accept = new Button(information);
        accept.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        informationForUser.add(accept, 0, 1);

        accept.setOnAction(e -> Board.restart());

        scene2 = new Scene(informationForUser);
        return scene2;
    }
}
