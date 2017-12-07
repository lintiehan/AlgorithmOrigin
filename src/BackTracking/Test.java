package BackTracking;

import java.awt.Checkbox;

public class Test {
	int[][] Matri;

	public Test(int[][] sudoku) {
		this.Matri = sudoku;
	}

	public static void main(String[] args) {
		// 待解数独
		int[][] sudoku = { { 8, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 3, 6, 0, 0, 0, 0, 0 }, { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
				{ 0, 5, 0, 0, 0, 7, 0, 0, 0 }, { 0, 0, 0, 0, 4, 5, 7, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
				{ 0, 0, 1, 0, 0, 0, 0, 6, 8 }, { 0, 0, 8, 5, 0, 0, 0, 1, 0 }, { 0, 9, 0, 0, 0, 0, 4, 0, 0 } };
		Test s = new Test(sudoku);
		s.backTrace(0, 0);
	}

	private void backTrace(int i, int j) {
		if (i == 8 && j == 9) {
			printAll(Matri);
			return;
		}
		if (j == 9) {
			i++;
			j = 0;
		}
		if (Matri[i][j] == 0) {

			for (int oo = 1; oo <= 9; oo++) {
				if (Check(i, j, oo)) {
					Matri[i][j] = oo;
					backTrace(i, j + 1);
					Matri[i][j] = 0;
				}
			}

		} else {
			backTrace(i, j + 1);
		}
	}

	private boolean Check(int row, int line, int oo) {
		for (int i = 0; i < 9; i++) {
			if (Matri[row][i] == oo || Matri[i][line] == oo) {
				return false;
			}
		}
		int ix = row / 3;
		int iy = line / 3;

		for (int i1 = 0; i1 < 3; i1++) {
			for (int j1 = 0; j1 < 3; j1++) {
				int temp = Matri[ix * 3 + i1][iy * 3 + j1];
				if (temp == oo) {
					return false;
				}
			}
		}

		return true;
	}

	private void printAll(int[][] nums) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i].length; j++) {
				System.out.print(nums[i][j] + " ");
			}
			System.out.println();
		}
	}

}
