
package Cinema;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DisplayApp extends Application {
    Image backgroundImage = new Image("Cinema/movies_background.jpg");
    BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

    @Override
    public void start(Stage stage) {
        VBox group = new VBox(20);

        Label moviesLabel = new Label("Movies");
        moviesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        moviesLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

        Button jumanji = new Button("Jumanji");
        jumanji.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openNewStage(JumanjiApp.class);
            }
        });

        Button barbie = new Button("Barbie");
        barbie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openNewStage(BarbieApp.class);
            }
        });

        Button it = new Button("IT");
        it.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openNewStage(ItApp.class);
            }
        });

        Button fastAndFurious = new Button("Fast & Furious: Tokyo Drift");
        fastAndFurious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openNewStage(FastAndFuriousApp.class);
            }
        });

        Button theFaultInOurStars = new Button("The Fault In Our Stars");
        theFaultInOurStars.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openNewStage(TheFaultInOurStarsApp.class);
            }
        });

        Button divergent = new Button("Divergent");
        divergent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openNewStage(DivergentApp.class);
            }
        });

        Button brave = new Button("Brave");
        brave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openNewStage(BraveApp.class);
            }
        });

        Scene scene = new Scene(group, 1000, 600);
        jumanji.setPrefSize(200, 20);
        barbie.setPrefSize(200, 20);
        it.setPrefSize(200, 20);
        fastAndFurious.setPrefSize(200, 20);
        theFaultInOurStars.setPrefSize(200, 20);
        divergent.setPrefSize(200, 20);
        brave.setPrefSize(200, 20);

        group.getChildren().addAll(moviesLabel, jumanji, barbie, it, fastAndFurious, theFaultInOurStars, divergent,
                brave);
        group.setAlignment(Pos.CENTER);

        // Apply the background to the VBox
        group.setBackground(new Background(background));

        stage.setScene(scene);
        stage.setTitle("Movies");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void openNewStage(Class<? extends Application> appClass) {
        try {
            Application appInstance = appClass.getDeclaredConstructor().newInstance();
            Stage newStage = new Stage();
            newStage.setTitle(appInstance.toString());
            appInstance.start(newStage);
        } catch (Exception ex) {
            System.err.println("Error opening new stage: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
