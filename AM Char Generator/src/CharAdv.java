
public class CharAdv {

	/**Ages a character one year**/
	static public void ageing(CharSheet character){
		character.age++;
		
		//If the character is a magus, add 2 warping
		if(character.isMagus) character.addWarpPoints(2);
	}
	/**Returns the characters age total*/
	static private int ageTot(CharSheet character){
		return (int) Math.ceil(character.age / 10);
	}
}

