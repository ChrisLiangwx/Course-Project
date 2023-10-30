#include "sorting.h"
#include <iostream>



list<list<string>> sorting::groupAnagram(list<string>& input) {
	list<list<string>> result;
	int bucketCount = 0;

	for (const string& str : input) {
		//using merge sort
		string targetAnagram = sorting::mergeSort(str, 0, str.size());

		//using quick sort
		/*string targetAnagram = sorting::quickSort(str);*/

		bool isFind = false;
		for (list<string>& innerList : result) {
			//using merge sort
			string newAnagram = sorting::mergeSort(innerList.front(), 0, innerList.front().size());

			//useing quick sort
			/*string newAnagram = sorting::quickSort(innerList.front());*/

			if (innerList.size() > 0 && newAnagram == targetAnagram) {
				innerList.push_back(str);
				isFind = true;
				break;
			}
		}
		if (isFind == false) {
			list<string> newList = { str };
			result.push_back(newList);
		}
	}

	//display the result
	for (auto& subList : result) {
		for (auto& elem : subList) {
			cout << elem << " ";
		}
		cout << "\n";
	}

	return result;
}


string sorting::mergeSort(string inputStr, int left, int right) {
	//sort the inputStr by the ascii using Mergesort
	if (inputStr.length() <= 1) {
		return inputStr;
	}
	string sorted = "";
	int middle = left + (right - left) / 2;
	string leftStr = inputStr.substr(0, middle);
	string rightStr = inputStr.substr(middle);
	//recursion
	leftStr = mergeSort(leftStr, 0, leftStr.size());
	rightStr = mergeSort(rightStr, 0, rightStr.size());
	sorted = sorting::merge(leftStr, rightStr);
	return sorted;
}

string sorting::merge(string leftStr, string rightStr) {
	//store all chars in leftStr and rightStr in new vector merged by sequence
	vector<char> merged;
	int l = 0;
	int r = 0;
	while (l < leftStr.size() && r < rightStr.size()) {
		if (leftStr[l] < rightStr[r]) {
			merged.push_back(leftStr[l]);
			l++;
		}
		else if (leftStr[l] > rightStr[r]) {
			merged.push_back(rightStr[r]);
			r++;
		}
		else {
			merged.push_back(leftStr[l]);
			merged.push_back(rightStr[r]);
			l++;
			r++;
		}
	}

	while (l < leftStr.size()) {
		merged.push_back(leftStr[l]);
		l++;
	}

	while (r < rightStr.size()) {
		merged.push_back(rightStr[r]);
		r++;
	}
	string mergedStr(merged.begin(), merged.end());

	return mergedStr;
}


string sorting::quickSort(string input) {
	if (input.size() < 2) {
		//already sorted with only 1 char
		return input;
	}

	char pivot = input[0];
	string lessStr = "";
	string equalStr = "";
	string largerStr = "";
	for (const char& elem : input) {
		if (elem < pivot) {
			lessStr += elem;
		}
		else if (elem == pivot) {
			equalStr += elem;
		}
		else {
			largerStr += elem;
		}
	}
	//recursion
	lessStr = sorting::quickSort(lessStr);
	largerStr = sorting::quickSort(largerStr);

	return lessStr + equalStr + largerStr;
}
