
public class AMRules {
	
	static int pointRes;
	static int scoreRes;

	/**Returns the result of a simple die roll**/
	public static int simpleDie(){	
		int dieResult = (int)(Math.random() * 10 + 1);
		
		return dieResult;
	}//End of simpleDie
	
	/**Returns the result of a stress die roll, -1 if the roll is a botch.**/
	public static int stressDie(){		
		//If the result is a zero or a one a new result is generated.
		//A result of 1 doubles the resultMultipier
		
		int dieResult = 0;
		int resultMultiplier = 1;
		boolean botchCheck = false;
		boolean botch = false;
		
		do{
			dieResult = resultMultiplier > 1 ? (int)(Math.random() * 10) : (int)(Math.random() * 10 + 1);
			botch = botchCheck ? true : false;
			resultMultiplier = dieResult == 1 && !botchCheck ? resultMultiplier * 2 : resultMultiplier;
		}while(dieResult <= 1);
		dieResult *= resultMultiplier;
		dieResult = botch ? -1 : dieResult;
		
		return dieResult;
	}//End of stressDie
	
	/**Returns the scoreRes variable**/
	public static int getScoreRes(){
		return scoreRes;
	}
	
	/**Returns the pointRes variable**/
	public static int getPointRes(){
		return pointRes;
	}
	
	/**Updates characteristics score from points. Returns true if a change has been made**/
	public static boolean updateCharacteristics(int score, int points){
		boolean change = false;
		boolean loop;
		do{
			loop = false;
			
			if(points > Math.abs(score) && score > -5){
				points = 0;
				score--;
				change = true;
				loop = true;
			}			
		}while(loop);
		
		pointRes = points;
		scoreRes = score;
		return change;
	}//End of updateCharacteristics()

	/**Updates statistics score based on points and base. Returns true if a change has been made**/
	public static boolean updateStatistics(int score, int points, int base){
		boolean change = false;
		boolean loop;
		do{
			loop = false;
			
			if(points >= (score + 1) * base){
				points -= (score + 1) * base;
				score++;
				change = true;
				loop = true;
			}
		}while(loop);
		
		pointRes = points;
		scoreRes = score;
		return change;
	}//End of updateStatistics()
}//End of class
