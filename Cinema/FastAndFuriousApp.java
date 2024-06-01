package Cinema;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FastAndFuriousApp extends Application {
    private int availableSeats = 200;
    private int totalSeats = 200;
    private int reservedSeats = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Movies FastAndFurious = new Movies("The Fast and the Furious: Tokyo Drift", "Action", 16, 4);
        VBox group = new VBox();

        Label nameLabel4 = new Label("Name: " + FastAndFurious.getName());
        Label genreLabel4 = new Label("Genre: " + FastAndFurious.getGenre());
        Label minAgeLabel4 = new Label("Minimum Age: " + FastAndFurious.getMinAge());
        Label movieIdLabel4 = new Label("Movie ID: " + FastAndFurious.getMovieId());
        ImageView posterImageView = new ImageView(new Image("Cinema/fast and furios.jpeg"));
        posterImageView.setFitHeight(200);
        posterImageView.setPreserveRatio(true);
        Button booknow = new Button("Book Now");
        booknow.setStyle("-fx-background-color: #FFA500 ; -fx-text-fill: white;");

        booknow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Movie Booking");
                    alert.setHeaderText(null);
                    alert.setContentText("You are booking: " + FastAndFurious.getName());
                    alert.showAndWait();

                    Label movieName = new Label("Movie: " + FastAndFurious.getName());
                    Label Id = new Label("ID: " + FastAndFurious.getMovieId());
                    Label seatsAvailable = new Label("Seats Available: " + availableSeats);

                    Label Input = new Label("Input Seats to be Reserved:");
                    TextField inputField = new TextField();

                    Label slots = new Label("Time Slots: ");
                    final ComboBox<String> timeSlots = new ComboBox<>();
                    timeSlots.getItems().addAll("12:30 - 2:30", "2:45 - 4:45", "5:30 - 7:30", "7:45 - 9:45");

                    Label Category = new Label("Category: ");
                    RadioButton Standard = new RadioButton("Standard");
                    RadioButton Gold = new RadioButton("Gold");
                    RadioButton threeDimention = new RadioButton("3D");

                    ToggleGroup categoryGroup = new ToggleGroup();
                    Standard.setToggleGroup(categoryGroup);
                    Gold.setToggleGroup(categoryGroup);
                    threeDimention.setToggleGroup(categoryGroup);

                    Button Confirm = new Button("Confirm");
                    Confirm.setStyle("-fx-background-color: #FFA500 ; -fx-text-fill: white;");
                    Confirm.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                int seatsToReserve = Integer.parseInt(inputField.getText());
                                if (seatsToReserve <= totalSeats - reservedSeats) {
                                    reservedSeats += seatsToReserve;
                                    seatsAvailable.setText("Seats Available: " + (totalSeats - reservedSeats));
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Booking");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Booking Successful\n\nBye Bye");
                                    alert.showAndWait();
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Booking Error");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Cannot reserve more seats than available. Available seats: "
                                            + (totalSeats - reservedSeats));
                                    alert.showAndWait();
                                }
                            } catch (NumberFormatException ex) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Invalid Input");
                                alert.setHeaderText(null);
                                alert.setContentText("Please enter a valid number of seats.");
                                alert.showAndWait();
                            }
                        }
                    });
                    Button deleteBooking = new Button("Delete Booking");
                    deleteBooking.setStyle("-fx-background-color: #FFA500 ; -fx-text-fill: white;");
                    deleteBooking.setMinWidth(50);
                    deleteBooking.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                int seatsToCancel = Integer.parseInt(inputField.getText());
                                if (seatsToCancel <= reservedSeats) {
                                    reservedSeats -= seatsToCancel;
                                    seatsAvailable.setText("Seats Available: " + (totalSeats - reservedSeats));
                                    inputField.clear();
                                    timeSlots.getSelectionModel().clearSelection();
                                    categoryGroup.getSelectedToggle().setSelected(false);
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Cancellation Error");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Cannot cancel more seats than are currently reserved.");
                                    alert.showAndWait();
                                }
                            } catch (NumberFormatException ex) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Invalid Input");
                                alert.setHeaderText(null);
                                alert.setContentText("Please enter a valid number of seats to cancel.");
                                alert.showAndWait();
                            } catch (Exception ex) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText(null);
                                alert.setContentText("An error occurred while deleting the booking.");
                                alert.showAndWait();
                            }
                        }
                    });
                    group.getChildren().addAll(posterImageView, movieName, Id, seatsAvailable, Input, inputField, slots,
                            timeSlots,
                            Category, Standard, Gold, threeDimention, Confirm, deleteBooking);
                    Scene scene = new Scene(group, 600, 600);
                    stage.setScene(scene);
                    stage.setTitle("Booking");
                    stage.show();
                } catch (Exception ex) {
                    System.out.println("Booking Unavailable for this Movie!");
                }
            }
        });

        VBox vbox4 = new VBox(nameLabel4, genreLabel4, minAgeLabel4, movieIdLabel4, booknow);
        vbox4.setSpacing(10);
        vbox4.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox4, 400, 250);
        stage.setScene(scene);
        stage.setTitle("Fast&Furious : Tokyo Drift");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
