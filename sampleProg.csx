// This is to test recognition of tokens in a sample CSX program
// int type, id, assignment and intlit and semicolon
int a = 123;

// tests bool type
bool b = true; # Trailing comment
bool c = false; // Other type of trailing comment

// If structure
if (b) {
   cout >> "b";
} else {
   cout >> "not b";
}

# While structure
while (c) {
   int x = 15;
}

// Structs!
struct test {
   int d;
}

// Key words in comments
// bool int void true false struct cin cout if else while return
// { } ( ) ; , . << >> ++ -- + - * / ! && || == != < > <= >= =
// string and int lits in comments
// "Hello" 1234
