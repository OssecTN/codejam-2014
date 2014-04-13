package er.dream.codejam.qual;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import er.dream.codejam.helpers.ProblemSolver;

public class DeceitfulWar extends ProblemSolver{
	
	public static void main(String[] args) {
		new DeceitfulWar().execute();
	}

	@Override
	protected List<String> handleInput() {
		List<String> result = new ArrayList<String>();
		int[] settings = fileHandler.readIntArray();
		int games = settings[0];
		for(int i=0;i<games;i++){
			fileHandler.readInt(); // blocks
			result.add(""+warStartsSmall(fileHandler.readDoubleArray(), fileHandler.readDoubleArray()));
		}
		return result;
	}
	
	private String warStartsSmall(double[] naomi, double[] ken){
		Arrays.sort(naomi);
		Arrays.sort(ken);
		int naomiMin = 0;
		int kenMin = 0;
		int wins = 0;

		for(int i=0;i<naomi.length;i++){
			if(naomi[naomiMin] > ken[kenMin]){//Naomi Wins!
				wins++;
				naomiMin++;
				kenMin++;
			} else{ //Naomi lets Ken win
				naomiMin++;
			}
		}
		int reg = getRegularWins(naomi, ken);
		return ""+wins+" "+reg;
	}
	
	private int getRegularWins(double[] naomi, double[] ken){
		int kenWins = 0;
		for(int naomisBlock = 0; naomisBlock < naomi.length; naomisBlock++){
			for(int kensBlock = 0; kensBlock < ken.length; kensBlock++){
				if(ken[kensBlock] > naomi[naomisBlock]){
					kenWins++;
					ken[kensBlock] = -1;
					break;
				}
			}
		}
		return ken.length-kenWins;
	}

}
