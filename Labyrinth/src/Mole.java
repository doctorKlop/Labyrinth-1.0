import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Mole {
	static int[][] step(int[][] Lab, int i, int j, int b, int c) { 
		int a = 0;
		a = (int) (1+Math.random()*4);
		switch (a) {
		case 1: // шаг вверх
			if (i-2 < 0) {
				break;
			}
			else if (Lab[i-2][j] == 0) {
				break;
			}
			Lab[i-1][j] = 0;
			Lab [i-2][j] = 0;
			i-=2;
			Lab[0][0] = i;
			Lab[0][1] = j;
			return Lab;
		case 2: // шаг влево
			if (j-2 < 0) {
				break;
			}
			else if (Lab[i][j-2] == 0) {
				break;
			}
			Lab[i][j-1] = 0;
			Lab [i][j-2] = 0;
			j-=2;
			Lab[0][0] = i;
			Lab[0][1] = j;
			return Lab;
		case 3: // шаг вправо
			if (j+2 > c-2) {
				break;
			}
			else if (Lab[i][j+2] == 0) {
				break;
			}
			Lab[i][j+1] = 0;
			Lab [i][j+2] = 0;
			j+=2;
			Lab[0][0] = i;
			Lab[0][1] = j;
			return Lab;
		case 4: // шаг вниз
			if (i+2 > b-2) {
				break;
			}
			else if (Lab[i+2][j] == 0) {
				break;
			}
			Lab[i+1][j] = 0;
			Lab [i+2][j] = 0;
			i+=2;
			Lab[0][0] = i;
			Lab[0][1] = j;
			return Lab;
		}
		Lab[0][0] = i;
		Lab[0][1] = j;
		return Lab;
	} // один шаг
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("textLab");
		PrintWriter pw = new PrintWriter(file);
		int b = 31 , c = 31 ; // количество строк и столбцов
		int[][] Lab = new int[b][c];
		int i = 0, j = 0, di = 0, i2 = 0, i3 = 0;
		int count = 0, count2 = 0;
		int[][] backUpLab = new int[1000][2];
		int [] A = new int [500];
		boolean pat = false;
	    while (i<b) {
	    	 for (j = 0; j<c; j++) {
	    		 Lab[i][j] = 1;
	    	 }
	    	 i++;
	    } // заполнение матрицы 1-ми
	   	Lab[1][1] = 0;
	    i = 1;
	    j = 1;
	    backUpLab [di][0] = i;
		backUpLab [di][1] = j; // стратовое положение
	    
	    while (pat == false) {
	    	Mole.step(Lab, i, j, b, c);
	    	if (i == Lab[0][0]&& j == Lab[0][1]) {
	    		count++;
	    	}
	    	else {
	    		i = Lab[0][0];
	    	    j = Lab[0][1];
	    	    backUpLab [++di][0] = i;
	    		backUpLab [di][1] = j;
	    		count = 0;
	    	}
	    	if (count > 100) {
	    		if (di < 1) {
	    			pat = true;
	    			continue;
	    		}
	    		i = backUpLab [--di][0];
	    		j = backUpLab [di][1];
	    		count = 0;
	    	}
	    	
	    }
	    
	    Lab[1][0] = 0; // вход
	    
	    while (i2 < b-2) {
	    	 if (Lab[i2][c-2] == 0) {
	    		count2++;
	    		A[i3++] = i2; 
	    	 }
	    	 i2++;
	    } //все строки с нулем в предпоследнем столбце
	    
	    Lab[A[(int)(Math.random()*count2)]][c-1] = 0;
	    
	    for (i = 0; i<Lab.length; i++) {
			 for (j = 0; j<Lab[i].length; j++) {
				 if (Lab[i][j] == 0) {
					 pw.print("   ");
				 }
				 else {
				 pw.print(Lab[i][j] + "  ");
				 }
		     }
			 pw.println();
		} // вывод матрицы
	    pw.close(); 
	}

}
