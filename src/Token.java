

public class Token {
	
	private String spelling;
	private String type;
	
	public Token(String spelling, String type){
		this.spelling = spelling;
		this.type = type;
	}
	
	public String toString(){
		String s = type + "("+spelling+")";
		return s;
	}
	
}
