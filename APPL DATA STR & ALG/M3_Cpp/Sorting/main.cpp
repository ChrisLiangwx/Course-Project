#include "main.h"
#include "sorting.h"
#include <iostream>

int main() {
    sorting sortCase;
    list<string> input1 = { "bucket", "rat", "mango", "tango", "ogtan", "tar" };
    list<string> input2 = { "bat", "tab", "rat", "car", "arc" };
    list<string> input3 = { "apple", "orange", "banana", "peach", "grape", "mango" };
    list<string> input4 = { "listen", "silent", "hello", "world", "listen" };

    sortCase.groupAnagram(input1);
    cout << "\n";
    sortCase.groupAnagram(input2);
    cout << "\n";
    sortCase.groupAnagram(input3);
    cout << "\n";
    sortCase.groupAnagram(input4);
    cout << "\n";

    return 0;
}
