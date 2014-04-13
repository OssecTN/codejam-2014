package er.dream.codejam.qual;

import java.util.ArrayList;
import java.util.List;

import er.dream.codejam.helpers.ProblemSolver;

public class CookieClickerAlpha extends ProblemSolver{
	
	public static void main(String[] args) {
		new CookieClickerAlpha().execute();
	}

	@Override
	protected List<String> handleInput() {
		List<String> result = new ArrayList<String>();
		int[] settings = fileHandler.readIntArray();
		int lines = settings[0];
		for(int i=0;i<lines;i++){
			double[] game = fileHandler.readDoubleArray();
			result.add(""+gimmeCOOKIESWithoutOverflow(game[0], game[1], game[2]));
		}
		return result;
	}
	
//	private double gimmeCOOKIES(double myCost, double initialCost, double cookieRate, final double factoryCost, final double cookieBonusRate, final double goal){
//		double newInitial = initialCost + factoryCost / cookieRate;
//		double moreCookies = cookieRate + cookieBonusRate;
//		double withFactory = newInitial + goal / moreCookies;
//		if(myCost <= withFactory)
//			return myCost;
//		else
//			return gimmeCOOKIES(withFactory, newInitial, moreCookies, factoryCost, cookieBonusRate, goal);
//	}

	private double gimmeCOOKIESWithoutOverflow(double factoryCost, double cookieRateAdj, double goal){
		double cookieRate = 2.0;
		double time = goal / cookieRate;
		double initial = 0;
		double newCost;
		while (true){
			initial += factoryCost / cookieRate;
			cookieRate += cookieRateAdj;
			newCost = initial + goal / cookieRate;
			if(newCost >= time)
				return time;
			time = newCost;
		}
	}
}
