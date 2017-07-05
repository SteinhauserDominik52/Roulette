import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Roulette  {
	
	public static void main (String[] args)throws IOException {
		Roulette r = new Roulette();
		r.askUserToPlay();
		int cash = r.askUserForStartCash();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		do{

			 	int inputNumber = r.askUserForTypeOfChance();
				int stake = r.askUserForStake(cash);
				int randomNumber = r.randomNumber();
				int newcash = r.compareHigh(cash, stake, randomNumber, inputNumber);
				cash = newcash;
				System.out.println("Möchtest du weiterspielen dann drücke (y) ansonsten (q)");
		}
		while(scanner.next().equals("y"));
		System.out.println("Vielen Dank fürs spielen, du gehst mit " + cash + "€ nach hause!");

	}
	
	public boolean askUserToPlay() throws IOException {
			
		String yes = "j";
		String no = "n";
			
		do {
			System.out.print("Möchten Sie das Spiel starten? (j)(n) \n");
			InputStreamReader question = new InputStreamReader(System.in);
			
			BufferedReader play = new BufferedReader(question);
			String wantPlay = play.readLine();
		
			if (wantPlay.equals(yes)) {
				System.out.println("Ok sie wollen spielen.");
			}
				
			else if(wantPlay.equals(no)) {
				System.out.println("Tschüss bis zum nächsten mal");
				System.exit(0);
			}
			
			else {
		    	System.err.println("Fehler");
		    	askUserToPlay();
		    	continue;
			}
	}
		while (false);
	return false;
	}

	public int askUserForStartCash() throws NumberFormatException {
		do{
			System.out.print("Gib dein Kapital an (Nicht mehr als 10000€ und nur 10er Schritte): \n");
			InputStreamReader Capital = new InputStreamReader(System.in);
			
			try{
				BufferedReader cap = new BufferedReader(Capital);
				int capital = Integer.parseInt(cap.readLine());
				if ( capital <= 10000 & capital%10 == 0) {
				System.out.println("Dein Kapital beträgt " + capital + "€.");
			return capital;
				}
				else {
					System.out.println("Dein Kapital ist zu hoch oder nicht in 10er Schritten!");
					continue;
				}
			    }
			catch (NumberFormatException ioe){
				System.err.println("Fehlerhafte Eingabe");
				askUserForStartCash();
			}
			
			catch (IOException e){
				System.err.println("Fehler");
				continue;
			}
		} while(true);
	}
	
	public int askUserForTypeOfChance() throws IOException {
		
		String high = "h";
	    String low = "e";
	    do{
	    	System.out.print("Welche Chance möchtest du nutzen? hohe(h) oder einfach(e) \n ");
	    	InputStreamReader isr2 = new InputStreamReader(System.in);
	    
	    	BufferedReader br2 = new BufferedReader(isr2);
	    	String chance = br2.readLine();
	    	
	    	if (chance.equals(high)) {
	    		System.out.println("Du hast dich für eine hohe Chance entschieden.");
	    		InputStreamReader isr4 = new InputStreamReader(System.in);
	    	    BufferedReader br4 = new BufferedReader(isr4);
	    	    System.out.print("Auf welche Zahl möchten Sie setzen(1-36)?: \n");
	    	    String input = br4.readLine();
	    	    int inputNumber = Integer.parseInt(input);
	    	    if (inputNumber <= 36 & inputNumber >= 1 ) {
	    	    	System.out.println("Du hast die Zahl " + inputNumber + " gewählt.");
	    	    }
	        
	    	    else if(inputNumber >= 36 && inputNumber <= 1) {
	    	    	System.out.println("Die Zahl liegt auserhalb des Wertebereichs!");
	    	    }
	    	    
	    	    else {
	        		System.err.println("Fehler");
	        	}
	        return inputNumber;
	    	}
	    	
	    	else if(chance.equals(low)) {
	    		System.out.println("Du hast dich für eine einfache Chance entschieden.");
	    		InputStreamReader isr5 = new InputStreamReader(System.in);
	    		BufferedReader br5 = new BufferedReader(isr5);
	    		System.out.println("Möchtest du auf gerade(0) oder ungerade(1)Zahlen wetten?.");
		    	String oE = br5.readLine();
		    	int oddEven = Integer.parseInt(oE);
		    return oddEven;
		    }
	    
	    	else {
	    		System.err.println("Fehler");
	    		askUserForTypeOfChance();
	    		continue;
	    	}
	    
	    } while (false);
		return 0;
	}
	
	public int askUserForStake(int cash) throws IOException{
		InputStreamReader isr3 = new InputStreamReader(System.in);
	    BufferedReader br3 = new BufferedReader(isr3);
	    System.out.print("Wieviel möchtest du setzen (10€ Schritte nicht mehr wie 1000€): \n");
	    String putMoney = br3.readLine();
	    int putMo = Integer.parseInt(putMoney);
	    if (putMo%10 == 0 && cash >= putMo && putMo <= 1000 ) {
	    	System.out.println("Du hast " + putMoney + "€ gesetzt.");
	    }
    
	    else if(putMo%10 != 0) {
	    	System.out.println("Falsche Angabe");
	    	askUserForStake(cash);
	    }
	    
	    else {
    		System.err.println("Fehler");
    		askUserForStake(cash);
    	}
		return putMo;
	}
	
	public int randomNumber(){
		 int number = (int) (Math.random()*36);
		 System.out.print("Es wurde die Zahl: " + number + " gezogen");
    return number;
	}

	public int compareHigh(int cash, int stake, int randomNumber, int inputNumber) throws IOException{
		int newcash = 0;
		if(randomNumber == inputNumber){
			System.out.print("\nSie haben gewonnen");
		    newcash= cash + stake*36;
		}	
		
		else if(randomNumber%2 == inputNumber ){
			System.out.print("\nSie haben gewonnen");
		    newcash= cash + stake*2;
		}	
		
		else{
			System.out.print("\nLeider hat es diesmal nicht geklappt");
			newcash = cash - stake;
		}
		
		if (newcash <= 0) {
			System.out.print("\nSie sind pleite\n");
			System.exit(0);
		}
		else {
			System.out.print("\nSie haben noch " + newcash + "€ zum verzocken\n");
		}
		return newcash;
		
	}

}