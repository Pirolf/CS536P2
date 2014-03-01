///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            P2.java
// Files:            P2.java CSX.jlex MakeFile allTokens badInput sampleProg //any other input files not yet made...
// Semester:         CS536 Spring 2014
//
// Author:           Stephen Sturdevant
// Email:            sturdevant2@wisc.edu
// CS Login:         sturdeva
// Lecturer's Name:  Hasti Beck
// Lab Section:      001
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Yucheng Tu
// Email:            tu7@wisc.edu
// CS Login:         ytu
// Lecturer's Name:  Hasti Beck
// Lab Section:      001
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the CSX scanner.
 * 
 * It calls testAllTokens for every input file (passed as commandline argument)
 * testAllTokens will recreate the input file, ignoring comments and bad input, but will still
 * skip lines in the output file to account for them and whitespace
 */
public class P2 {
	public static void main(String[] args) throws IOException {
						// exception may be thrown by yylex
		// For each input file, produce an output file with same name, just w/
		// .out at the end, pretty useless though since we'll want to test the error
		// messages for each file individually...
		for (String file : args)
			testAllTokens(file);
	}

	/**
	* testAllTokens
	*
	* Open and read from file allTokens.txt
	* For each token read, write the corresponding string to allTokens.out
	* If the input file contains all tokens, multiple per line, we can verify
	* correctness of the scanner by comparing the input and output files
	* (e.g., using a 'diff' command).
	*/
	private static void testAllTokens(String inFileName) throws IOException {
		// open input and output files
		FileReader inFile = null;
		PrintWriter outFile = null;
		try {
			inFile = new FileReader(inFileName);
			outFile = new PrintWriter(new FileWriter(inFileName+".out"));
		} catch (FileNotFoundException ex) {
			System.err.println("File " + inFileName + " not found.");
			System.exit(-1);
		} catch (IOException ex) {
			System.err.println(inFileName + ".out cannot be opened.");
			System.exit(-1);
		}

		// create and call the scanner
		Yylex scanner = new Yylex(inFile);
		Symbol token = scanner.next_token();
		// initialize line & char position
		int currLine = 1;
		int currChar = 1;
		while (token.sym != sym.EOF) {
			// Adjust position (more accurate recreation of inFile)
			posAdj(outFile, token, currLine, currChar);
			String str = tokStr(token);
			outFile.print(str);
			
			// Set new line & char values
			currLine = ((TokenVal)token.value).linenum;
			currChar = str.length() + ((TokenVal)token.value).charnum;
			token = scanner.next_token();
		} // end while
		outFile.close();
	}

	/**
	* posAdj
	*
	* This tests the linenum & charnum incrementing, since if it wasn't working, the diff's
	* of the files would produce output.
	* 
	* @param outFile output file we're writing to
	* @param token symbol which gives us a target line and char number
	* @param currLine current line the filewriter is on
	* @param currChar current character the filewriter is on
	*/
	
	private static void posAdj (PrintWriter outFile, Symbol token, int currLine, int currChar) {
		int targetLine = ((TokenVal)token.value).linenum;
		int targetChar = ((TokenVal)token.value).charnum;
		
		// Adjust line position
		while (currLine < targetLine) {
			outFile.println();
			// Reset char position on new line
			currChar = 1;
			currLine++;
		}

		// Adjust character position (after line adjustment, obviously...)
		while (currChar < targetChar) {
			outFile.print(" ");
			currChar++;
		}
	}

	/**
	* tokStr
	*
	* Converts symbol into its original string representation, was originally part of testAllTokens
	* method, but this way it's a little neater and hidden away!
	*
	* @param token: input symbol of token to read
	* @return: string representation of token
	*/
	private static String tokStr(Symbol token) {
		switch (token.sym) {
			case sym.BOOL:
				return "bool";

			case sym.INT:
				return "int";

			case sym.VOID:
				return "void";

			case sym.TRUE:
				return "true";

			case sym.FALSE:
				return "false";

			case sym.STRUCT:
				return "struct";

			case sym.CIN:
				return "cin";

			case sym.COUT:
				return "cout";

			case sym.IF:
				return "if";

			case sym.ELSE:
				return "else";

			case sym.WHILE:
				return "while";

			case sym.RETURN:
				return "return";

			case sym.ID:
				return ((IdTokenVal)token.value).idVal;

			case sym.INTLITERAL:
				return String.valueOf(((IntLitTokenVal)token.value).intVal);

			case sym.STRINGLITERAL:
				return ((StrLitTokenVal)token.value).strVal;

			case sym.LCURLY:
				return "{";

			case sym.RCURLY:
				return "}";

			case sym.LPAREN:
				return "(";

			case sym.RPAREN:
				return ")";

			case sym.SEMICOLON:
				return ";";

			case sym.COMMA:
				return ",";

			case sym.DOT:
				return ".";

			case sym.WRITE:
				return "<<";

			case sym.READ:
				return ">>";

			case sym.PLUSPLUS:
				return "++";

			case sym.MINUSMINUS:
				return "--";

			case sym.PLUS:
				return "+";

			case sym.MINUS:
				return "-";

			case sym.TIMES:
				return "*";

			case sym.DIVIDE:
				return "/";

			case sym.NOT:
				return "!";

			case sym.AND:
				return "&&";

			case sym.OR:
				return "||";

			case sym.EQUALS:
				return "==";

			case sym.NOTEQUALS:
				return "!=";

			case sym.LESS:
				return "<";

			case sym.GREATER:
				return ">";

			case sym.LESSEQ:
				return "<=";

			case sym.GREATEREQ:
				return ">=";

			case sym.ASSIGN:
				return "=";

			default:
				return "UNKNOWN TOKEN";
		} // end switch
	}
}
