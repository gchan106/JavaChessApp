package ChessProject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerTurnChange
{
    private JLabel label;
    Timer countdownTimer;
    int timeLeft ;
    public TimerTurnChange(JLabel passedLabel)
    {
       countdownTimer = new Timer(1000, new CountdownTimerListener());
       this.label = passedLabel;
       timeLeft = ChessGame.timeRemaining;
    }
    
    //A function that starts and resets the timer
    public void start()
    {
    	countdownTimer.start();
    }
    public void reset()
    {
    	timeLeft = ChessGame.timeRemaining;
    }
    
    //A function that is called after every second. It updates the timer and takes other necessary actions
    class CountdownTimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
       	 int min,sec;
       	 if (timeLeft  > 0)
       	 {
           	min = timeLeft / 60;
           	sec = timeLeft % 60;
            label.setText(String.valueOf(min)+":"+(sec>=10?String.valueOf(sec):"0"+String.valueOf(sec)));
            timeLeft --;
         }
       	 else
       	 {
               label.setText("Time is up, Turn Changed");
               reset();
               start();
               ChessGame.changeTurn();
		 }
    }
 }
}