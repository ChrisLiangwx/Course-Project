#pragma once
#include <vector>
#include <string>
#include <iostream>
using namespace std;


class task2
{
public:
	bool checkAvailability(int row, int col, vector<int>& board) {
		//checks whether the queens in and before the col will attack each other
		for (int i = 0; i < col; i++) {
			if (board[i] == row || abs(board[i] - row) == abs(i - col)) {
				//check if they are in the same row or if they have same row and column space(digonally)
				return false;
			}
		}
		return true;
	}

	void findSolution(int col, vector<int>& board, vector<vector<int>>& solution) {
		//add all possible solutions to solution
		if (col == 8) {
			solution.push_back(board);
			return;
		}
		for (int i = 0; i < 8; i++) {
			if (checkAvailability(i, col, board)) {
				//if it is safe to move queen to this row(i)
				board[col] = i;
				//the recursion tries all the rows in the next column
				findSolution(col + 1, board, solution);
			}
		}
	}

	int findMinMove(vector<int>& input, vector<vector<int>>& solution) {
		int min_moves = 64;
		for (auto new_solution : solution) {
			//for each solution, count the moves and update the minimum move(min_moves)
			int count = 0;
			for (int i = 0; i < 8; i++) {
				if (new_solution[i] != input[i]) {
					count++;
				}
			}
			min_moves = (count < min_moves) ? count : min_moves;

		}
		return min_moves;
	}
};

int main() {
	string input;
	getline(cin, input); 
	vector<int> initial(8);
	int j = 0;
	for (int i = 0; i < 8; i++)
	{
		initial[i] = (input[j] - '0');
		j += 2;
	}

	vector<int> board(8);
	vector<vector<int>> solutions;

	task2 t2;
	t2.findSolution(0, board, solutions);

	int moves = t2.findMinMove(initial, solutions);
	cout << "Minimum number of moves required: " << moves << endl;
}