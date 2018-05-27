import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;

/**
 * Created by #Property Of Ss on 11/18/2017.
 */
public class Player extends SlotMachine_GUI{


    static int credits = 10; //initial credit balance of the game
    static int bonusCredits = 0; // current bonus credit value
    static int noOfWins = 0; //no of wins from 1 game
    static int noOfSpins = 0; //no of times spinning used
    static int totalCreditsBet = 0;
    static int creditsWon = 0;
    static int noOfLoses = 0;

    public static int getNoOfFreeSpins() {
        return noOfFreeSpins;
    }

    static int noOfFreeSpins = 0;

    public static int getTotalCreditsBet() {
        return totalCreditsBet;
    }

    public static int getCreditsWon() {
        return creditsWon;
    }



    //---------------------------------------------------------------------------------------------

    //spinning stop method
    //passing the thread number to stop spinning
    public static void stopSpin(Thread thread){

            thread.stop();// get the thread no from the parameter
            if (thread == thread1) { //if it equals to thread one
                reel1SpinningStopped = true;
            } else if (thread == thread2) {
                reel2SpinningStopped = true;
            } else if (thread == thread3) {
                reel3SpinningStopped = true;
            }


            if (reel1SpinningStopped && reel2SpinningStopped && reel3SpinningStopped) {
                noOfSpins++;
                winStatus();
                buttonDisable(false);
            }

        }

    //---------------------------------------------------------------------------------------------

    public static void winStatus(){

        int FirstImageNo  = reel1.getRandomNo();//getting related random no of stopped reel
        int SecondImageNo = reel2.getRandomNo();      //and comapare them
        int ThirdImageNo  = reel3.getRandomNo();

        if(((FirstImageNo == SecondImageNo) && (SecondImageNo == ThirdImageNo)) || FirstImageNo == SecondImageNo || SecondImageNo == ThirdImageNo || FirstImageNo == ThirdImageNo){
            if ((FirstImageNo == SecondImageNo) && (SecondImageNo == ThirdImageNo)) {
                winningStatus.setText("You Won !!!");
                bonusCredits = Reel.symbols[FirstImageNo].getValue()*currentBet; //calculating bonus credits
                credits = credits + bonusCredits; //adding bonus credits to credits
                creditsWon = creditsWon + bonusCredits;
                noOfWins++;

            }else if(ThirdImageNo == SecondImageNo){
                winningStatus.setText("You Won a Free Spin!!");
                bonusCredits = Reel.symbols[ThirdImageNo].getValue()*currentBet;
                credits = credits + bonusCredits;
                creditsWon = creditsWon + bonusCredits;
                noOfWins++;
                noOfFreeSpins++;

            } else if (FirstImageNo == SecondImageNo ){
                winningStatus.setText("You Won a Free Spin!!");
                bonusCredits = Reel.symbols[FirstImageNo].getValue()*currentBet;
                credits = bonusCredits + credits;
                creditsWon = creditsWon + bonusCredits;
                noOfWins++;
                noOfFreeSpins++;

            } else if(FirstImageNo == ThirdImageNo){
                winningStatus.setText("You Won a Free Spin!!");
                bonusCredits = Reel.symbols[FirstImageNo].getValue()*currentBet;
                credits = credits + bonusCredits;
                noOfWins++;
                noOfFreeSpins++;
                creditsWon = creditsWon + bonusCredits;

            }
        }else{
            winningStatus.setText("You Loss");
            bonusCredits = 0;
            System.out.println(bonusCredits);

        }
        System.out.println(getNoOfWins());
        creditStatus.setText("Available Credits : " + credits );
        currentBet = 0; //after adding bonus credits set it to 0 again
    }
    //---------------------------------------------------------------------------------------------


    public static void addCredit(){ //credit addd method
        credits++;
        creditStatus.setText("Available Credits : " + credits );
    }

    //---------------------------------------------------------------------------------------------

    public static void betOne(){ // bet one credit

        if(currentBet >= 2) {
            winningStatus.setText("You can't bet more !");
        }else {
            if (credits > 0) {
                credits = credits - 1;
                creditStatus.setText("Available Credits : " + credits);


                if (credits == 0) {
                    creditsBet.setText("");
                } else {
                    currentBet++;
                    creditsBet.setText("Credits Bet : " + currentBet);
                }

                totalCreditsBet++;
                winningStatus.setText("");
            } else {
                spin.setDisable(true); //disable spin button if not having enough credits

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Not Enough Credits");
                alert.setContentText("Please add credits and Bet again");
                alert.showAndWait();

                creditStatus.setText("Not enough Credits.");
            }
        }
    }
    //---------------------------------------------------------------------------------------------

    public static int getNoOfWins() {
        return noOfWins;
    } // get no of wins

    //---------------------------------------------------------------------------------------------


    public static int getNoOfSpins() {
        return noOfSpins;
    } //get no of spins used

    //---------------------------------------------------------------------------------------------

    public static int getNoOfLoses() { //returns no of loses spins
        noOfLoses = getNoOfSpins()-getNoOfWins();
        return noOfLoses;
    }

    //---------------------------------------------------------------------------------------------

    public static void betMax(){ //bet max method

        if (credits >= 3) {
            betMax.setDisable(true);
            credits = credits - 3;
            creditStatus.setText("Available Credits : " + credits );
            if(credits == 0){
                creditsBet.setText("");
            }else {
                creditsBet.setText("Credits Bet : " + 3);
            }
            noOfSpins++;
            totalCreditsBet = totalCreditsBet + 3;
            currentBet = 3;

        } else {
            spin.setDisable(true);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Not Enough Credits");
            alert.setContentText("Please add credits and bet again");
            alert.showAndWait();

            creditStatus.setText("Not enough Credits.");

        }
    }
    //---------------------------------------------------------------------------------------------

    public static void reset(){ //reset method

        credits = credits + currentBet;
        if(currentBet == 3){
            betMax.setDisable(false);
            totalCreditsBet = totalCreditsBet - 3;
        }else {
            totalCreditsBet = totalCreditsBet - 1;
        }
        currentBet = 0;
        creditStatus.setText("Available Credits : " + credits );
        creditsBet.setText("Credits Bet : " + currentBet);
    }
    //---------------------------------------------------------------------------------------------



    public static void buttonDisable(Boolean buttonStatus){ //method to disable all buttons
        addCredit.setDisable(buttonStatus);
        betOne.setDisable(buttonStatus);
        statistics.setDisable(buttonStatus);
        reset.setDisable(buttonStatus);
        spin.setDisable(buttonStatus);

        if(betMaxClicked){ //if betMax button clicked once then disable it
            betMax.setDisable(true);
        } else{
            betMax.setDisable(buttonStatus);
        }
    }


}
