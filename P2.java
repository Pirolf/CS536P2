import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the CSX scanner.
 * This version is set up to test all tokens, but more code is needed to test
 * other aspects of the scanner (e.g., input that causes errors, character
 * numbers, values associated with tokens)
 */
public class P2 {
    public static void main(String[] args) throws IOException {
                                           // exception may be thrown by yylex
        // test all tokens
        testAllTokens();
        CharNum.num = 1;

        // ADD CALLS TO OTHER TEST METHODS HERE
    }

    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("allTokens.in");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            outFile.println(tokStr(token));
				token = scanner.next_token();
        } // end while
        outFile.close();
   }

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
