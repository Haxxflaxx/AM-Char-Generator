
public class RandomGenerator {

	public static void main(String[] args) {
		
		CharSheet olle = new CharSheet();
		
		for(int i = 0; i < 10; i++){		
			olle.addCharPoints("str", 1);
			System.out.println(olle.getCharScore("str"));
			CharAdv.ageing(olle);
			System.out.println(olle.age);
		}
	}
}
