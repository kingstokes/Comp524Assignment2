import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Comp 524 -- Assignment 2
 * Scanner for modified version of 'Calculator' language.
 * Program will utilize State Machine to verify if tokens are valid based on grammar
 * Invalid tokens will cause an error message and the program will terminate.
 * If all tokens are valid then they will be output (1 per line) along with
 * it's 'type'.
 * 
 * By Robert Stokes
 */

public class Main {

	public static void main(String[] args) {
		FileReader fr = null;
		StateMachine sm = new StateMachine();
		ArrayList<Token> tokenList = new ArrayList();
		String letters = "";
		String digits = "";
		String symbols = "";
		boolean encounteredSentinel = false;
		boolean encounteredDot = false;
		boolean encounteredPlusOrMinus = false;
		boolean encounteredFinalDigit = false;
		// read in the name of file from command line.
		if (args.length <= 0) {
			// print out error and then terminate.
			System.out.println("Error. Enter file at command line.");
			System.exit(4);
		} else {
			try {
				fr = new FileReader(args[0]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		char sourceChar = ' ';
		int num = 0;
		while (true) {

			if (encounteredSentinel)
				break;

			try {
				num = fr.read();
				if (num < 0) {
					// end of file reached
					encounteredSentinel = true;
					// send a space back down to switch statements to form last
					// token.
					num = ' ';
					// break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sourceChar = (char) num;
			// System.out.println("sourceChar: " + sourceChar);
			// System.exit(0);

			if (sm.currentState() == 0) {
				switch (sourceChar) {
				
				//if we see space in state 0 go pull another char off stream.
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					break;

				case ':':
					sm.setState(1);
					break;
				case '+':
					sm.setState(3);
					break;
				case '-':
					sm.setState(4);
					break;
				case '*':
					sm.setState(5);
					break;
				case '/':
					sm.setState(6);
					break;
				case '(':
					sm.setState(7);
					break;
				case ')':
					sm.setState(8);
					break;
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
					letters += sourceChar;
					sm.setState(9);
					break;
					
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					
					digits += sourceChar;
					sm.setState(11);
					break;
					
				case '^':
					sm.setState(10);
					break;
				
				case '.':
					if (encounteredDot){
						System.out.println("Error. Multiple dots detected.");
						System.exit(4);
					}
					encounteredDot = true;
					digits += sourceChar;
					//sm.setState(11);
					break;

				default:
					System.out
							.println("Error. Character is not in Calculator Language.");
					System.exit(4);

				}// end switch

			} else if (sm.currentState() == 1) {
				switch (sourceChar) {
				
				case '=':
					sm.setState(2);
					// create a Token and add it to list
					// Token token = new Token(":=", "assign");
					// tokenList.add(token);
					break;

				default:
					sm.setState(0); // if we didn't find an acceptable token for
									// this state
					System.out.println("Error in state 1.");
					System.exit(4);
					break;
				}// end switch
			} else if (sm.currentState() == 2) {
				switch (sourceChar) {
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					// create Token and add it to token list.
					Token token = new Token(":=", "assign");
					tokenList.add(token);
					sm.setState(0);
					break;
				default:
					sm.setState(0);
					System.out.println("Error in state 2");
					System.exit(4);

				}// end switch
			} else if (sm.currentState() == 3) {
				switch (sourceChar) {
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					Token token = new Token("+", "plus");
					tokenList.add(token);
					sm.setState(0);
					break;
				case '+':
					sm.setState(12);
					break;
				default:
					System.out.println("Error in state 3");
					System.exit(4);
				}
			} else if (sm.currentState() == 4) {
				switch (sourceChar) {
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					Token token = new Token("-", "minus");
					tokenList.add(token);
					sm.setState(0);
					break;
				case '-':
					sm.setState(13);
					break;
				default:
					System.out.println("Error in state 4");
					System.exit(4);
				}
			} else if (sm.currentState() == 5) {
				switch (sourceChar) {
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					Token token = new Token("*", "times");
					tokenList.add(token);
					sm.setState(0);
					break;
				default:
					System.out.println("Error in state 5");
					System.exit(4);
				}
			} else if (sm.currentState() == 6) {
				switch (sourceChar) {
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					Token token = new Token("/", "div");
					tokenList.add(token);
					sm.setState(0);
					break;
				case '/':
					//handle single line comments.
					sm.setState(17);
					break;
				case '*':
					//handle multi-line comments.
					sm.setState(18);
					break;
				
				default:
					System.out.println("Error in state 6");
					System.exit(4);
				}
			} else if (sm.currentState() == 7) {
				switch (sourceChar) {
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					Token token = new Token("(", "lparen");
					tokenList.add(token);
					sm.setState(0);
					break;
				default:
					System.out.println("Error in state 7");
					System.exit(4);
				}
			} else if (sm.currentState() == 8) {
				switch (sourceChar) {
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					Token token = new Token(")", "rparen");
					tokenList.add(token);
					sm.setState(0);
					break;
				default:
					System.out.println("Error in state 8");
					System.exit(4);
				}
			} else if (sm.currentState() == 9) {

				switch (sourceChar) {
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					if (letters.equals("read") || letters.equals("write")){
						Token token = new Token(letters, "keyword");
						tokenList.add(token);
						letters = "";
						break;
					}
					Token token = new Token(letters, "id");
					tokenList.add(token);
					sm.setState(0);
					letters = "";
					break;
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':		
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					letters += sourceChar; // concatenate numbers with any
											// letters seen.
					break;
					
					default: 
						System.out.println("Error detected in state 9.");
						System.exit(4);
				}
			} else if (sm.currentState() == 10) {
				switch(sourceChar){
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					Token token = new Token("^", "exponent");
					tokenList.add(token);
					sm.setState(0);
					break;
				default:
					System.out.println("Error. Invalid characater in State 10.");
					System.exit(4);
				}
			} else if (sm.currentState() == 11) {
				
				switch (sourceChar){
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					
					Token token = new Token(digits, "number");
					tokenList.add(token);
					sm.setState(0);
					digits = "";
					encounteredDot = false;
					break;
					
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					digits += sourceChar;
					break;
					
				case '.':
					if (encounteredDot){
						System.out.println("Error. Multiple dots in number.");
						System.exit(4);
					}
					digits += sourceChar;
					encounteredDot = true;
					break;
					
				case 'e':
				case 'E':
					sm.setState(14);
					digits += sourceChar;
					break;
					
				default:
					System.out.println("Error in state 11");
					System.exit(4);
				}
					
			} else if (sm.currentState() == 12) {
				switch (sourceChar) {
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					Token token = new Token("++", "increment");
					tokenList.add(token);
					sm.setState(0);
					break;
				default:
					System.out.println("Error in state 12.");
					System.exit(4);
				}
			} else if (sm.currentState() == 13) {
				switch (sourceChar) {
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					Token token = new Token("--", "decrement");
					tokenList.add(token);
					sm.setState(0);
					break;
				default:
					System.out.println("Error in state 13.");
					System.exit(4);
				}
			} else if (sm.currentState() == 14){
				switch(sourceChar){
				
							
				case '+':
				case '-':
					sm.setState(15);
					digits += sourceChar;
					break;
				
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					digits += sourceChar;
					sm.setState(16);
					break;
					
				default:
					System.out.println("Error. Expecting + or - or integer.");
					System.exit(4);
				}
			} else if (sm.currentState() == 15){
				switch(sourceChar){
								
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					digits += sourceChar;
					sm.setState(16);
					break;
					
				default:
					System.out.println("Error. Invalid character after + or - in exponent.");
					System.exit(4);
				}
				
			} else if (sm.currentState() == 16){
				switch(sourceChar){
				case ' ':
				case '\n':
				case '\t':
				case '\r':
					Token token = new Token(digits, "number");
					tokenList.add(token);
					sm.setState(0);
					digits = "";
					break;
					
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					digits += sourceChar;
					break;
					
				default: 
					System.out.println("Error. Invalid character at end of the file.");
					System.out.println("sourceChar: " + sourceChar);
					System.exit(4);
				}
			} else if (sm.currentState() == 17){
				//stay in this state unless we see newline or carriage return.
				switch(sourceChar){
				case '\n':
				case '\r':
					sm.setState(0);
					break;					
				}
			} else if (sm.currentState() == 18){
				//stay in this state until we see a star
				switch(sourceChar){
				case '*':
					sm.setState(19);
					break;
				}
			} else if (sm.currentState() == 19){
				//if we see a forward slash go to state zero
				//otherwise go back to state 18.
				switch(sourceChar){
				case '/':
					sm.setState(0);
					break;
				default:
					sm.setState(18);
					break;
				}
			}

		}// end while
		
		
		// print out the contents of the token list.
		for (int i = 0; i < tokenList.size(); i++) {
			System.out.println(tokenList.get(i).toString());
		}
		System.exit(0);

	}// end main method

}// end Main class
