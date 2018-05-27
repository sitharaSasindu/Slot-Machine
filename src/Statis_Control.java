import javafx.scene.control.Alert;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;


public class Statis_Control extends Statistics_GUI {

    static int totalCreditLose;
    static double average;

    public static void statistics(){//updating labels with game details
        average();// calling average method to calculate abverage

        noOfSpin.setText( "No of Spins : " + Player.getNoOfSpins());
        noOfWin.setText(  "No of Wins  : " + Player.getNoOfWins());
        noOfFails.setText("No of Loses : " + Player.getNoOfLoses());

        totalCreditLose = Player.getTotalCreditsBet();

        totalCreditBet.setText(  "Total Credits Bet  : " + Player.getTotalCreditsBet());
        totalCreditsWon.setText( "Total Credits Won  : " + Player.getCreditsWon());
        totalCreditsloss.setText("Total Credits Loss : " + totalCreditLose );

    }

        public static void save(){ //saving the details to project path

            try {
                FileWriter fw = new FileWriter(LocalDate.now() + ".txt", true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("No of Spins: " + Player.getNoOfSpins() );
                pw.println("Total Wins : " + Player.getNoOfWins());
                pw.println("Total Loses: " +  Player.getNoOfLoses());
                pw.println();
                pw.println();
                pw.println("Total Credits Bet  : " + Player.getTotalCreditsBet());
                pw.println("Total Credits Won  : " + Player.getCreditsWon());
                pw.println("Total Credits Loss : " + totalCreditLose);

                pw.println("Average Bet Amount (coins) : " + average);
                pw.println();

                pw.println("___________________________________________________");
                pw.println();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save Game");
            alert.setHeaderText("Status Successfully Saved.");
            alert.showAndWait();
        }

    public static void average(){ //CALCULATING THE AVERAGE
        //AvgStatus.setText("Average : " + average + "\nPositive");
        if(Player.getTotalCreditsBet()!=0) {
            average = Player.getCreditsWon() - Player.getTotalCreditsBet() / Player.getNoOfSpins() - Player.getNoOfFreeSpins();

            if (average >= 0) {
                AvgStatus.setText("Average : " + average + "\nPositive");
            } else {
                AvgStatus.setText("Average : " + average + "\nNegative");
            }
        }
    }
}
