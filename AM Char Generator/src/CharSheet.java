import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CharSheet {
	
	public boolean isMagus = false;
	public boolean isAlive = true;
	public int size = 0;
	public int age = 0;
	public int spellLevels = 0;
	
	private int decrepScore, decrepPoints = 0;
	private int warpScore, warpPoints = 0;
	private int livingCondition = 0;
	private int lLRitual = 0;
	
	
	//Statistics lists
	//Second value separates between Score and Points
	private int[][] characteristics = new int [8][2];
	private int[][] abilities = new int [30][2];
	private int[][] arts = new int [15][2];
	
	
	private Map<String, Integer> charIndex = new HashMap<String, Integer>();
	private Map<String, Integer> abilIndex = new HashMap<String, Integer>();
	private Map<String, Integer> artIndex = new HashMap<String, Integer>();
	private int nextAbilSlot = 0;
	
	
	public CharSheet(){
		charIndex.put("int", 0);
		charIndex.put("per", 1);
		charIndex.put("str", 2);
		charIndex.put("sta", 3);
		charIndex.put("pre", 4);
		charIndex.put("com", 5);
		charIndex.put("dex", 6);
		charIndex.put("qik", 7);
		
		artIndex.put("cr", 0);
		artIndex.put("in", 1);
		artIndex.put("mu", 2);
		artIndex.put("pe", 3);
		artIndex.put("re", 4);
		artIndex.put("an", 5);
		artIndex.put("aq", 6);
		artIndex.put("au", 7);
		artIndex.put("co", 8);
		artIndex.put("he", 9);
		artIndex.put("ig", 10);
		artIndex.put("im", 11);
		artIndex.put("me", 12);
		artIndex.put("te", 13);
		artIndex.put("vi", 14);
		
		for(int i = 0; i < 30; i++){
			if(i < characteristics.length) Arrays.fill(characteristics[i], 0);
			if(i < abilities.length) Arrays.fill(abilities[i], 0);
			if(i < arts.length) Arrays.fill(arts[i], 0);
		}
	}//End of constructor
	
	//General Statistics
	//Functions for setting score and value for general statistics
	public void setLivingCondition(int value){
		livingCondition = value;
	}
	public void setLLRitual(int value){
		lLRitual = value;
	}
	public int getAgeMod(){
		return livingCondition + lLRitual;
	}
	public int getWarpScore(){
		return warpScore;
	}
	public void addWarpPoints(int value){
		warpPoints += value;
		updateStat();
	}
	public int getDecrepScore(){
		return decrepScore;
	}
	public void addDecrepPoints(int value){
		decrepPoints += value;
		updateStat();
	}
	
	//Characteristics management
	//Functions for Setting the score value, Returning the score value & Adding age points
	/**Sets the indexed characteristic to value.
	 * Index: int, per, str, sta, pre, com, dex, qik**/
	public void setCharScore(String index, int value){
		characteristics[charIndex.get(index)][0] = value;
	}
	
	/**Returns the indexed characteristics value.
	 * Index: int, per, str, sta, pre, com, dex, qik**/
	public int getCharScore(String index){
		return characteristics[charIndex.get(index)][0];
	}
	
	/**Adds value to the indexed characteristic.
	 * Index: int, per, str, sta, pre, com, dex, qik**/
	public void addCharPoints(String index, int value){
		characteristics[charIndex.get(index)][1] += value;
		updateStat();
	}
	
	//Arts management
	//Functions for returning score & adding XP
	/**Returns the indexed art score.
	 * Index: cr, in, mu, pe, re, an, aq, au, co, he, ig, im, me, te, vi**/
	public int getArtScore(String index){
		return arts[artIndex.get(index)][0];
	}
	
	/**Adds value to the indexed arts.
	 * Index: cr, in, mu, pe, re, an, aq, au, co, he, ig, im, me, te, vi**/
	public void addArtXp(String index, int value){
		arts[artIndex.get(index)][1] += value;
		updateStat();
	}
	
	//Ability management
	//Functions for adding abilities, scores & XP
	/**Adds the index value to list of abilities**/
	public void addAbility(String index){
		abilIndex.put(index, nextAbilSlot);
		nextAbilSlot++;
	}
	
	/**Returns the indexed ability score.
	 * Abilities must be added before it can be returned**/
	public int getAbilityScore(String index){
		return abilities[abilIndex.get(index)][0];
	}
	
	/**Adds value to indexed ability XP**/
	public void addAbilityXp(String index, int value){
		abilities[abilIndex.get(index)][1] += value;
		updateStat();
	}
	/**Returns true if the given ability is in the characters list of abilities**/
	public boolean hasAbility(String index){
		return abilIndex.containsKey(index);
	}
	
	//Statistics update loop
	/**Converts points into score**/
	private void updateStat(){
		//Iterates through and updates characteristics
		for(int i = 0; i < characteristics.length - 1; i++){
			if(AMRules.updateCharacteristics(characteristics[i][0], characteristics[i][1])){
				characteristics[i][0] = AMRules.scoreRes;
				characteristics[i][1] = AMRules.pointRes;
			}
		}
		//Iterates through and updates abilities
		for(int i = 0; i < abilities.length - 1; i++){
			if(AMRules.updateStatistics(abilities[i][0], abilities[i][1], 5)){
				abilities[i][0] = AMRules.scoreRes;
				abilities[i][1] = AMRules.pointRes;
			}
		}
		//Iterates through and updates arts
		for(int i = 0; i < arts.length - 1; i++){
			if(AMRules.updateStatistics(arts[i][0], arts[i][1], 1)){
				arts[i][0] = AMRules.scoreRes;
				arts[i][1] = AMRules.pointRes;
			}
		}
		//Updates Decrepitude score
		if(AMRules.updateStatistics(decrepScore, decrepPoints, 5)){
			decrepScore = AMRules.scoreRes;
			decrepPoints = AMRules.pointRes;
		}
		//Updates Warp score
		if(AMRules.updateStatistics(warpScore, warpPoints, 5)){
			warpScore = AMRules.scoreRes;
			warpPoints = AMRules.pointRes;
		}
	}//End of updateStats()
}//End of class
