using namespace std;
#include <list>
#include <string>
#include <iostream>
#include <vector>
#include <unordered_set>


class sorting
{
public:
	list<list<string>> groupAnagram(std::list<std::string>& input);
	string quickSort(string input);
	string mergeSort(string inputStr, int left, int right);
	string merge(string leftStr, std::string rightStr);
	
};

