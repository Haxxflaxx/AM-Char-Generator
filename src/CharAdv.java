
public class CharAdv {

	/**
	 * Ages a character one year
	 */
	static public void ageing(CharSheet character){
		character.age++;
		
		//If the character is a magus, add 2 warping
		if(character.isMagus) character.addWarpPoints(2);
	}
	
	/**
	 * Returns the characters age total
	 */
	static private int ageTot(CharSheet character){
		int ageTot = 0;
		
		ageTot = AMRules.stressDie();
		ageTot = ageTot == -1 ? 0 : ageTot;
		
		ageTot += Math.ceil(character.age / 10) - character.getAgeMod();
		
		return ageTot;
	}
	
	/**
	 * Returns the characters crisis total
	 */
	static private int crisisTot(CharSheet character){
		return (int)(AMRules.simpleDie() + Math.ceil(character.age / 10) + character.getDecrepScore());
	}
	
	
}

