package aoc21;

import java.util.ArrayList;
import java.util.Arrays;

public class Password {
	
	private StringBuilder password = new StringBuilder();
	
	public Password (String password) {
		this.password.append(password);
	}
	
	public void doInstruction (String instruction) {
		ArrayList<String> list = new ArrayList<>(Arrays.asList(instruction.split(" ")));
		ArrayList<String> parameters = new ArrayList<>(list);
		parameters.removeIf(w -> w.length() > 1);
		
		if (list.get(0).equals("rotate")) {
			if (list.get(1).equals("left")) {
				int x = Integer.parseInt(parameters.get(0));
				rotateSteps(x, true);
			}
			else if (list.get(1).equals("right")) {
				int x = Integer.parseInt(parameters.get(0));
				rotateSteps(x);
			}
			else {
				rotatePosition(parameters.get(0));
			}	
		}
		else {
			int x = 0;
			int y = 0;
			try {
				x = Integer.parseInt(parameters.get(0));
				y = Integer.parseInt(parameters.get(1));
			}
			catch (NumberFormatException e) {};
	
			switch (list.get(0)) {
				case "swap": {
					if (list.get(1).equals("position")) {
						swapPosition(x, y);
					}
					else {
						swapLetter(parameters.get(0),parameters.get(1));
					}
					break;
				}
				case "reverse": {
					reverse(x,y);
					break;
				}
				case "move": {
					move(x,y);
					break;
				}
			}
		}
	}
	
	public void doInstructionReverse (String instruction) {
		ArrayList<String> list = new ArrayList<>(Arrays.asList(instruction.split(" ")));
		ArrayList<String> parameters = new ArrayList<>(list);
		parameters.removeIf(w -> w.length() > 1);
		
		if (list.get(0).equals("rotate")) {
			if (list.get(1).equals("left")) {
				int x = Integer.parseInt(parameters.get(0));
				rotateSteps(x);
			}
			else if (list.get(1).equals("right")) {
				int x = Integer.parseInt(parameters.get(0));
				rotateSteps(x, true);
			}
			else {
				rotatePositionReverse(parameters.get(0));
			}	
		}
		else {
			int x = 0;
			int y = 0;
			try {
				x = Integer.parseInt(parameters.get(0));
				y = Integer.parseInt(parameters.get(1));
			}
			catch (NumberFormatException e) {};
	
			switch (list.get(0)) {
				case "swap": {
					if (list.get(1).equals("position")) {
						swapPosition(x, y);
					}
					else {
						swapLetter(parameters.get(0),parameters.get(1));
					}
					break;
				}
				case "reverse": {
					reverse(x,y);
					break;
				}
				case "move": {
					moveReverse(x,y);
					break;
				}
			}
		}
	}
	
	private boolean areIndexesValid (int x, int y) {
		if (Math.max(x,y) < password.length() && x >= 0 && y >= 0)
			return true;
		else
			return false;
	}
	
	public void swapPosition (int x, int y) {
		if (areIndexesValid(x,y)) {
			String helper = password.substring(x, x+1);
			password.replace(x, x+1, password.substring(y, y+1));
			password.replace(y, y + 1, helper);
			
		}
	
	}
	
	public void swapLetter (String a, String b) {
		int x = password.indexOf(a);
		int y = password.indexOf(b);
		if (x*y >= 0)
			swapPosition(x,y);
	}
	
	public void rotateSteps (int steps, boolean left) {
		if (steps <= 0 || steps == password.length())
			return;
		String helper = "";
		if (steps > password.length())
			rotateSteps(steps%password.length(), left);
		else {
			if (left) {
				helper = password.substring(0,steps);
				password.delete(0, steps);
				password.append(helper);
			}
			else {
				int index = password.length()-steps;
				helper = password.substring(index);
				password.delete(index, password.length());
				password.insert(0,helper);
			}
		}
	}
	
	public void rotateSteps (int steps) {
		rotateSteps(steps, false);
	}
	
	public void rotatePosition (String letter) {
		int index = password.indexOf(letter);
		if (index >= 0)
			rotateSteps(index >= 4 ? index + 2 : index + 1);
	}
	
	public void rotatePositionReverse (String letter) {
		int index = password.indexOf(letter);
		if (index >= 0) {
			if (index % 2 == 1)
				rotateSteps((index+1)/2, true);
			else {
				switch (index) {
					case 2: {rotateSteps(6, true); break;}
					case 4: {rotateSteps(7, true); break;}
					case 6: {rotateSteps(8, true); break;}
					case 0: {rotateSteps(9, true); break;}
					default: break;
				}
			}
		}
	}
	
	public void reverse (int x, int y) {
		if (areIndexesValid(x,y)) {
			StringBuilder helper = new StringBuilder(password.substring(x,y+1));
			helper.reverse();
			password.delete(x, y+1);
			password.insert(x, helper.toString());
		}
	}
	
	public void move (int x, int y) {
		if (areIndexesValid(x,y)) {
			char helper = password.charAt(x);
			password.deleteCharAt(x);
			password.insert(y,helper);
		}
	}
	
	public void moveReverse (int x, int y) {
		move(y,x);
	}
	
	public String toString() {
		return password.toString();
	} 

}
