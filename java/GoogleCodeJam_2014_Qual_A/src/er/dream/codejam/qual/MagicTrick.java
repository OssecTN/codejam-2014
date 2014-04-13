package er.dream.codejam.qual;

import java.util.ArrayList;
import java.util.List;

import er.dream.codejam.helpers.ProblemSolver;

public class MagicTrick extends ProblemSolver{
	
	public static void main(String[] args) {
		new MagicTrick().execute();
	}

	@Override
	protected List<String> handleInput() {
		List<String> result = new ArrayList<String>();
		int[] settings = fileHandler.readIntArray();
		int games = settings[0];
		int[][] fieldOne = new int[4][];
		int[][] fieldTwo = new int[4][];
		int guessOne;
		int guessTwo;
		for(int i=0;i<games;i++){
			guessOne = fileHandler.readInt();
			for(int line=0;line<4;line++)
				fieldOne[line] = fileHandler.readIntArray();
			guessTwo = fileHandler.readInt();
			for(int line=0;line<4;line++)
				fieldTwo[line] = fileHandler.readIntArray();
				
			result.add(gameToResult(fieldOne, fieldTwo, guessOne, guessTwo));
		}
		return result;
	}
	
	private String gameToResult(int[][] fieldOne, int[][] fieldTwo, int firstAnswer, int secondAnswer){
		int matches = 0;
		int[] firstRow = fieldOne[firstAnswer-1];
		int[] secondRow = fieldTwo[secondAnswer-1];
		int match = 0;
		
		// Yep, n squared complexity and no break after 2 matches.
		// Who cares for performance with such small input sets? :)
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				if(firstRow[i] == secondRow[j]){
					matches++;
					match = firstRow[i];
				}
		
		if(matches == 0)
			return "Volunteer cheated!";
		else if(matches > 1)
			return "Bad magician!";
		else return ""+match;
	}
}
