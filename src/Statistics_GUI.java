import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.stage.Modality;
import javafx.stage.Stage;


public class Statistics_GUI extends SlotMachine_GUI {

    static Label noOfSpin;
    static Label noOfFails;
    static Label noOfWin;

    static Label totalCreditBet;
    static Label totalCreditsWon;
    static Label totalCreditsloss;

    static PieChart pieChartWinStatus;
    static PieChart pieChartCresitStatus;

   static  Label AvgStatus;

    public static void showGUI() {

        Stage window = new Stage();
        window.setTitle("Game Status");
        window.initModality(Modality.APPLICATION_MODAL);

        VBox status = new VBox(); //main VCBOX which holds all the other components

        VBox vbox1 = new VBox(); //to store the title
        Label titleStatus = new Label("Game Status");
        titleStatus.setFont(Font.font("Verdana",FontWeight.BOLD,48));
        titleStatus.setAlignment(Pos.CENTER);
        titleStatus.setTextFill(Color.web("#0033CC"));
        vbox1.setAlignment(Pos.BASELINE_CENTER);
        vbox1.getChildren().addAll(titleStatus);
//---------------------------------------------------------------------------------

        HBox hbox2 = new HBox(); //3 vboxes inside one hbox to view labels
        VBox statusByText1 = new VBox();
        noOfSpin =new Label( "Total Credits Bet  : "+Player.getNoOfSpins());
        noOfWin =new Label(  "Total Credits Won  : "+Player.getNoOfWins());
        noOfFails =new Label("Total Credits Lose : "+Player.getNoOfLoses());

        noOfSpin.setFont(Font.font("Verdana",FontWeight.BOLD,15));
        noOfSpin.setTextFill(Color.web("#996600"));
        noOfWin.setFont(Font.font("Verdana",FontWeight.BOLD,15));
        noOfWin.setTextFill(Color.web("#996600"));
        noOfFails.setFont(Font.font("Verdana",FontWeight.BOLD,15));
        noOfFails.setTextFill(Color.web("#996600"));

        statusByText1.getChildren().addAll(noOfSpin, noOfWin, noOfFails);
        hbox2.setSpacing(420);

        VBox statusByText2 = new VBox();
        totalCreditBet =new Label(  "Total Credits Bet  : "+Player.getNoOfSpins());
        totalCreditsWon =new Label( "Total Credits Won  : "+Player.getNoOfWins());
        totalCreditsloss =new Label("Total Credits Lose : "+Player.getNoOfLoses());

        totalCreditBet.setFont(Font.font("Verdana",FontWeight.BOLD,15));
        totalCreditBet.setTextFill(Color.web("#996600"));
        totalCreditsWon.setFont(Font.font("Verdana",FontWeight.BOLD,15));
        totalCreditsWon.setTextFill(Color.web("#996600"));
        totalCreditsloss.setFont(Font.font("Verdana",FontWeight.BOLD,15));
        totalCreditsloss.setTextFill(Color.web("#996600"));

        AvgStatus = new Label("");
        AvgStatus.setFont(Font.font("Verdana",FontWeight.BOLD,15));
        AvgStatus.setTextFill(Color.web("#996600"));

        statusByText2.getChildren().addAll(totalCreditBet, totalCreditsWon, totalCreditsloss);

        hbox2.getChildren().addAll(statusByText1, statusByText2, AvgStatus); //adding 3vboxes to one hbox

        //----------------------------------------------------------------------------
        //adding pie charts
        VBox vbox3 = new VBox();
        HBox charts = new HBox();
        pieChartWinStatus = new PieChart();
        pieChartCresitStatus = new PieChart();
        charts.getChildren().addAll(pieChartWinStatus, pieChartCresitStatus);
        vbox3.getChildren().addAll(charts);
        vbox3.setSpacing(15);

        PieChart.Data slice1WinStatus = new PieChart.Data("No of Spins", Player.getNoOfSpins());
        PieChart.Data slice2WinStatus = new PieChart.Data("No of Wins"  , Player.getNoOfWins());
        PieChart.Data slice3WinStatus = new PieChart.Data("No of Losses" , Player.getNoOfLoses());

        pieChartWinStatus.getData().add(slice1WinStatus);
        pieChartWinStatus.getData().add(slice2WinStatus);
        pieChartWinStatus.getData().add(slice3WinStatus);


        PieChart.Data slice1CreditStatus = new PieChart.Data("Total Credits Bet  : ", Player.getTotalCreditsBet());
        PieChart.Data slice2CreditStatus = new PieChart.Data("Total Credits Won  : "  , Player.getNoOfWins());
        PieChart.Data slice3CreditStatus = new PieChart.Data("Total Credits Loss : " , Player.getTotalCreditsBet() - Player.getCreditsWon());

        pieChartCresitStatus.getData().add(slice1CreditStatus);
        pieChartCresitStatus.getData().add(slice2CreditStatus);
        pieChartCresitStatus.getData().add(slice3CreditStatus);
        //--------------------------------------------------------------------------
        //adding buttons
        VBox vbox4 = new VBox();
        HBox buttonsHbox = new HBox();
        Button back = new Button("Back to Game");
        back.setStyle("-fx-background-color: #CC00FF; -fx-font-size: 1.5em");
        back.setMaxWidth(200);

        Button save = new Button("Save");
        save.setStyle("-fx-background-color: #CC00FF; -fx-font-size: 1.5em");
        save.setPrefWidth(200);

        buttonsHbox.getChildren().addAll(back, save);
        vbox4.getChildren().addAll(buttonsHbox);
        buttonsHbox.setSpacing(640);
//--------------------------------------------------------
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Statis_Control.save();
            }
        });

//--------------------------------------------------------
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               window.close();
            }
        });

//--------------------------------------------------------------------------------
        Statis_Control.statistics();

        String image = SlotMachine_GUI.class.getResource("statBack.png").toExternalForm();
        status.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;"  );



        status.getChildren().addAll(vbox1, hbox2, vbox3, vbox4);
        Scene sceneStatistics =new Scene(status, 1350,600);
        window.setScene(sceneStatistics);
        window.showAndWait();

    }

}
