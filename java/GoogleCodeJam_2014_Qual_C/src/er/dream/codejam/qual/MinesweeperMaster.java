package er.dream.codejam.qual;

import java.util.ArrayList;
import java.util.List;

import er.dream.codejam.helpers.ProblemSolver;

public class MinesweeperMaster extends ProblemSolver{
	
	public static void main(String[] args) {
		new MinesweeperMaster().execute();
	}

	@Override
	protected List<String> handleInput() {
		List<String> result = new ArrayList<String>();
		int[] settings = fileHandler.readIntArray();
		int lines = settings[0];
		for(int i=0;i<lines;i++){
			int[] game = fileHandler.readIntArray();
			result.add(flip(generateField(game[0], game[1], game[2]), game[0] > game[1]));
		}
		return result;
	}
	
	private String generateField(int xW, int yW, int mines){
		int x = Math.max(xW, yW);
		int y = Math.min(xW,yW);
		int freeFields = x*y-mines;
		
		if(freeFields == 1)
			return generateMap(x,y,1,1,0,0);
		
		if(y == 1 && freeFields > 0){
			return printRow(freeFields, x-freeFields, true);
		}
		if(freeFields < 4)
			return "\nImpossible";
		
		int fullRows;
		for(int i=2;i<=x;i++){ //5
			fullRows = freeFields / i; //1
			if(fullRows <= y){
				int overlap = freeFields - fullRows*i; //1
				if(overlap == 0 && fullRows > 1) return generateMap(x,y,fullRows,i, 0,0); //"Possible with perfect rows";
				if(fullRows > 1 && overlap > 1 && fullRows + 1 <= y) return generateMap(x,y,fullRows,i,1,overlap); //"Possible with one row overlap";
				if(fullRows > 2 && fullRows + 1 <= y && overlap+i >= 4) return generateMap(x,y,fullRows-1,i,2,overlap+i); //"Possible with two rows overlap";
			}
		}

		return "\nImpossible";
	}
	
	private String flip(String orig, boolean doSomething){
		if(!doSomething || orig.startsWith("\nI"))
			return orig;
		
		String[] rows = orig.split("\n");
		char[][] newRows = new char[rows[1].length()][rows.length-1];
		char[] row;
		for(int i=1;i<rows.length;i++){
			row = rows[i].toCharArray();
			for(int j=0;j<row.length;j++){
				newRows[j][i-1] = row[j];
			}
		}
		StringBuffer buff = new StringBuffer();
		for(char[] rowy:newRows){
			buff.append("\n");
			buff.append(rowy);
		}
		return buff.toString();
	}
	
	private String generateMap(int x, int y, int fullRows, int fullRowSize, int partialRows, int partialSize){
		StringBuffer map = new StringBuffer();
		for(int rows = 0; rows < fullRows; rows++)
			map.append(printRow(fullRowSize, x-fullRowSize, rows==0));
		if(partialRows == 1)
			map.append(printRow(partialSize, x-partialSize, false));
		else if(partialRows == 2){
			map.append(printRow(Math.round(partialSize / 2.0f), x));
			map.append(printRow(partialSize / 2, x));
		}
		for(int i=0;i<y-fullRows-partialRows;i++)
			map.append(printRow(0, x));
		return map.toString();
	}
	
	private String printRow(int dots, int length){
		return printRow(dots, length-dots, false);
	}
	
	private String printRow(int dots, int stars, boolean first){
		StringBuffer buff = new StringBuffer(first?"\nc":"\n");
		for(int i=first?1:0;i<dots;i++)
			buff.append(".");
		for(int i=0;i<stars;i++)
			buff.append("*");
		return buff.toString();
	}

}
