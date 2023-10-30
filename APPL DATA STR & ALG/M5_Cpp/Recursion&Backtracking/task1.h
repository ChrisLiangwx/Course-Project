#pragma once
#include <string>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;
class task1
{
public:
	bool checkPermutation(string s1, string s2){
		if (s1.size() > s2.size()) {
			return false;
		}

		int len = s1.size();
		map<char, int> map1;
		for (char c1 : s1) {
			map1[c1]++;
		}


		map<char, int> map2;
		for (int i = 0; i < len; i++) {
			map2[s2[i]]++;
		}
		if (map1 == map2){
			return true;
		}

		for (int i = len; i < s2.size(); i++) {
			map2[s2[i - len]]--;
			if (map2[s2[i - len]] == 0) {
				map2.erase(s2[i - len]);
			}
			map2[s2[i]]++;
			if (map1 == map2){
				return true;
			}
		}
		return false;
	}
};

//int main() {
//	task1 t1;
//	
//	cout << t1.checkPermutation("ab", "eidbaooo") << endl;
//	cout << t1.checkPermutation("aoo", "eidboaoo") << endl;
//	cout << t1.checkPermutation("abc", "cbabc") << endl;
//	cout << t1.checkPermutation("hello", "hel") << endl;
//	cout << t1.checkPermutation("xyz", "abxcyz") << endl;
//
//}