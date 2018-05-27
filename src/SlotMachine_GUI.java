import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.ImageView;



/**
 * Created by #Property Of Ss on 11/9/2017.
 */
public class SlotMachine_GUI extends Application {

    private Stage window;
    static Label winningStatus;
    static Label creditStatus;

    Scene sceneMain; // 2 scenes for static and main

    static Reel reel1; // creating a Reel object
    static Reel reel2;
    static Reel reel3;

    private ImageView image;

    static Button addCredit;// static buttons to get access in Player class
    static Button betOne;
    static Button reset;
    static Button betMax;
    static Button statistics;
    static Button spin;

    static boolean betMaxClicked =false;//check whether betMax is clicked

    static int currentBet = 0; //current bet value

    static boolean reel1SpinningStopped = true; //static booleans for get access from player class
    static boolean reel2SpinningStopped = true;
    static boolean reel3SpinningStopped = true;

    static Thread thread1; //static threads to get access
    static Thread thread2;
    static Thread thread3;

    static Label creditsBet; //Label for displaying current bet credits
    //---------------------------------------------------------------------------------------------
    public static void main(String[] args) { //main method to run the program
        launch(args);
    }

    //---------------------------------------------------------------------------------------------
    @Override
    public void start(Stage primary_Stage) {
        primary_Stage.setTitle("Slot Machine"); //name
        window = primary_Stage;



        GridPane main = new GridPane(); //creating a gridpane and add all components
        main.setAlignment(Pos.CENTER);
        main.setHgap(10);
        main.setVgap(10);
        main.setPadding(new Insets(25, 25, 25, 25));
        main.setMaxSize(5000, 2000);

        Label title = new Label("Slot Machine");
        GridPane.setConstraints(title, 1, 0, 2,1);
        title.setFont(Font.font("Monospaced", FontWeight.BLACK, 58));
        title.setAlignment(Pos.CENTER);
        title.setTextFill(Color.web("#000000"));



        //-------------------------------------------------------------------------------------------
        //Adding default image to reels
        FileInputStream input = null;
       try {
            input = new FileInputStream("raw/preSymbol.jpg");//getting image from the
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            Image img1 = new Image(input);
             image = new ImageView(img1);
            image.setFitHeight(300);
            image.setFitWidth(300);
            image.setPreserveRatio(true);
            GridPane.setConstraints(image, 0, 1);

        try {
            input = new FileInputStream("raw/preSymbol.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            Image img2 = new Image(input);
            ImageView image2 = new ImageView(img2);
            image2.setFitHeight(300);
            image2.setFitWidth(300);
            image2.setPreserveRatio(true);
            GridPane.setConstraints(image2, 1, 1);

        try {
            input = new FileInputStream("raw/preSymbol.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            Image img3 = new Image(input);
            ImageView image3 = new ImageView(img3);
            image3.setFitHeight(300);
            image3.setFitWidth(300);
            image3.setPreserveRatio(true);
            GridPane.setConstraints(image3, 2, 1);

        //---------------------------------------------------------------------------------------------
        // adding buttons to the GUI

        addCredit = new Button("Add Credit");
        GridPane.setConstraints(addCredit, 0,2);
        GridPane.setHalignment(addCredit, HPos.LEFT);
        addCredit.setStyle("-fx-background-color: #CC9900; -fx-font-size: 1.5em");
        addCredit.setMaxWidth(140);

        betOne = new Button("Bet One");
        GridPane.setConstraints(betOne, 0,2);
        GridPane.setHalignment(betOne, HPos.RIGHT);
        betOne.setStyle("-fx-background-color: #CC9900; -fx-font-size: 1.5em");
        betOne.setMaxWidth(140);

        reset = new Button("Reset");
        GridPane.setConstraints(reset, 0,3);
        GridPane.setHalignment(reset, HPos.LEFT);
        reset.setStyle("-fx-background-color: #CC9900; -fx-font-size: 1.5em");
        reset.setMaxWidth(140);

        betMax = new Button("Bet Max");
        GridPane.setConstraints(betMax, 0,3);
        GridPane.setHalignment(betMax, HPos.RIGHT);
        betMax.setStyle("-fx-background-color: #CC9900; -fx-font-size: 1.5em");
        betMax.setMaxWidth(140);

        statistics = new Button("Statistics");
        GridPane.setConstraints(statistics, 0, 4);
        GridPane.setHalignment(statistics, HPos.CENTER);
        statistics.setStyle("-fx-background-color: #CC9900; -fx-font-size: 1.5em");
        statistics.setMaxWidth(140);

        spin = new Button("Spin");
        main.add(spin, 1,2, 1, 3);
        GridPane.setHalignment(spin, HPos.CENTER);
        spin.setStyle(
                "-fx-background-radius: 5em; " + "-fx-min-width: 130px; " + "-fx-min-height: 130px; " +
                        "-fx-max-width: 130px; " +"-fx-max-height: 130px;" + "-fx-background-color: #CC6600; -fx-font-size: 2em"
        );
        //---------------------------------------------------------------------------------------------

        //creating Labels to show game status
        creditStatus = new Label("Credits : 10");
        GridPane.setConstraints(creditStatus, 2,2);
        GridPane.setHalignment(creditStatus, HPos.LEFT);
        creditStatus.setFont(new Font("Arial", 30));
        creditStatus.setTextFill(Color.web("#000000"));

        creditsBet = new Label();
        GridPane.setConstraints(creditsBet, 2,3);
        GridPane.setHalignment(creditsBet, HPos.LEFT);
        creditsBet.setFont(new Font("Arial", 30));
        creditsBet.setTextFill(Color.web("#000000"));

        winningStatus = new Label();
        GridPane.setConstraints(winningStatus, 2,4);
        GridPane.setHalignment(creditStatus, HPos.LEFT);
        winningStatus.setFont(new Font("Arial", 30));
        winningStatus.setTextFill(Color.web("#ff0000"));
        //---------------------------------------------------------------------------------------------

        reel1 = new Reel();//creating Reel objects
        reel2 = new Reel();
        reel3 = new Reel();
        //---------------------------------------------------------------------------------------------

        //OnClick Listener for pressing spin button to start spinning
        spin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentBet != 0) { //first check if the user already have a bet
                    winningStatus.setText("");

                    reel1SpinningStopped = false;//current reel spin status
                    reel2SpinningStopped = false;
                    reel3SpinningStopped = false;

                    thread1 = new Thread(() -> { //running the thread while it is true
                        while (true) {
                            reel1.spin(image);//passing the imageview obj to spinning method
                          }
                    });

                    thread2 = new Thread(() -> {
                        while (true) {
                            reel2.spin(image2);
                        }
                    });

                    thread3 = new Thread(() -> {
                        while (true) {
                            reel3.spin(image3); }
                    });

                    thread1.start();//starting the threads
                    thread2.start();
                    thread3.start();

                    Player.buttonDisable(true); //disable all buttons while spinning

                }else{
                   winningStatus.setText("Please Bet First.");
                }

            }
        });

        //---------------------------------------------------------------------------------------------

        //imageView 1 onCLick listener to stop current spinning
        image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!reel1SpinningStopped) {
                    Player.stopSpin(thread1);//sending thread 1 as paramether to syopspin method
                }
            }
        });

        //imageView 2 onCLick listener to stop current spinning
        image2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (!reel2SpinningStopped) {
                    Player.stopSpin(thread2);
                }
            }
        });

        //imageView 3 onCLick listener to stop current spinning
        image3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
             if(!reel3SpinningStopped) {
                 Player.stopSpin(thread3);
             }
            }
        });
        //---------------------------------------------------------------------------------------------

        addCredit.setOnAction(new EventHandler<ActionEvent>() { //adding credits
            @Override
            public void handle(ActionEvent event) {
                    Player.addCredit();
            }
        });

        //---------------------------------------------------------------------------------------------

        betOne.setOnAction(new EventHandler<ActionEvent>() { //bet 1 credit
            @Override
            public void handle(ActionEvent event) {
                Player.betOne();
            }
        });

        //---------------------------------------------------------------------------------------------

        betMax.setOnAction(new EventHandler<ActionEvent>() { //bet max 3 credits
            @Override
            public void handle(ActionEvent event) {
                Player.betMax();
                betMaxClicked = true;
            }
        });

        //---------------------------------------------------------------------------------------------

        reset.setOnAction(new EventHandler<ActionEvent>() { // reset alredy bet credit value
            @Override
            public void handle(ActionEvent event) {
                Player.reset();
            }
        });


        //---------------------------------------------------------------------------------------------


            statistics.setOnAction(new EventHandler<ActionEvent>() {  //go to staistics window
                @Override
                public void handle(ActionEvent event) {
                    Statistics_GUI.showGUI();

                }
            });




        //---------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------



       main.getChildren().addAll(title, winningStatus, creditsBet,image, image2, image3, addCredit, betOne, betMax, reset, statistics, creditStatus);

        String image = SlotMachine_GUI.class.getResource("background.png").toExternalForm();
        main.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;"  );

        StackPane pane = new StackPane();
        pane.getChildren().add(main);
        pane.autosize();

        sceneMain = new Scene(pane, 1280, 800);
        primary_Stage.setScene(sceneMain);
        primary_Stage.show();

    }


}
