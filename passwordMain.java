package passwordCrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class passwordMain {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter answer file: ");
		String ans = sc.nextLine();
		System.out.println("Enter users file: ");
		String user = sc.nextLine();
		System.out.println("Enter dictionary file: ");
		String dict = sc.nextLine();
		List<String> answer = readFile(ans);
		List<String> dictionary = addNames(readFile(dict),readFile(user));
		List<String> password = new ArrayList<>();
		long start = System.currentTimeMillis();

			for (String tempAnswer: answer) {
				for(String tempWord : dictionary) {
					for (String tempPrepend : prependMangle(tempWord)) {
						if (tempPrepend.equals(tempAnswer)) {
							if (!password.contains(tempAnswer)) {
								password.add(tempAnswer);
							}
						}
					}
					for (String tempAppend : appendMangle(tempWord)) {
						if (tempAppend.equals(tempAnswer)) {
							if (!password.contains(tempAnswer)) {
								password.add(tempAnswer);
							}
						}
					}
					String toggle = toggleMangle(tempWord);
					String deleteFirst = deleteFirstMangle(tempWord);
					String deleteLast = deleteLastMangle(tempWord);
					String reverse = reverseMangle(tempWord);
					String duplicate = duplicateMangle(tempWord);
					String reflect = reflectMangle(tempWord);
					String upper = uppercaseMangle(tempWord);
					String lower = lowercaseMangle(tempWord);
					String capital = capitalizeMangle(tempWord);
					String nCapital = nCapitalizeMangle(tempWord);
				
					if (tempAnswer.equals(toggle) || tempAnswer.equals(deleteFirst) || tempAnswer.equals(deleteLast) || tempAnswer.equals(reverse)
							|| tempAnswer.equals(duplicate) || tempAnswer.equals(reflect) || tempAnswer.equals(upper) 
							|| tempAnswer.equals(lower) || tempAnswer.equals(capital) || tempAnswer.equals(nCapital)) {
						if (!password.contains(tempAnswer)) {
							password.add(tempAnswer);
						}
					}

					}
				}		
		System.out.println("Cracked Passwords: " + password);	
		long end = System.currentTimeMillis();
		System.out.println("Run time: " + (end - start));
	}
	
	
	
	public static List<String> prependMangle(String word) {
		List<String> mangle = new ArrayList<>();
		for (char i = 32; i <= 126; i++) {
			mangle.add(word + i);
		}
		return mangle;
	}
	public static List<String> appendMangle(String word) {
		List<String> mangle = new ArrayList<>();
		for (char i = 32; i <= 126; i++) {
			mangle.add(i + word);
		}
		return mangle;
	}
	public static String toggleMangle(String word) {
		char[] array = new char[] {};
		array = word.toCharArray();
		for (int i = 0; i <array.length; i += 2) {
			array[i] = Character.toUpperCase(array[i]);
		}
		word = new String(array);
		return word;
	}
	public static String deleteFirstMangle(String word) {
		return word.substring(1);
	}
	public static String deleteLastMangle(String word) {
		return word.substring(0, word.length()-1);
	}
	public static String reverseMangle(String word) {
		return new StringBuilder(word).reverse().toString();
	}
	public static String duplicateMangle(String word) {
		return word + word;
	}
	public static String reflectMangle(String word) {
		return word + reverseMangle(word);
	}
	public static String uppercaseMangle(String word) {
		return word.toUpperCase();
	}
	public static String lowercaseMangle(String word) {
		return word.toLowerCase();
	}
	public static String capitalizeMangle(String word) {
		return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
	}
	public static String nCapitalizeMangle(String word) {
		return word.substring(0,1).toLowerCase() + word.substring(1).toUpperCase();
	}

	
	
	public static List<String> readFile(String filename) throws FileNotFoundException {
		List<String> fileList = new ArrayList<>();
		Scanner scan = new Scanner(new File(filename));
		while (scan.hasNext()) {
			fileList.add(scan.next());
		}
		return fileList; 
	}

	public static List<String> addNames(List<String> list, List<String> names) {
		for (var i = 0; i < names.size(); i += 2) {
			String raw = names.get(i);
			int colon = raw.indexOf(':');
			list.add(raw.substring(0, colon));
		}
		return list;
	}
	
	/**List<String> encryptedPasswords = readEncryptedPassword(readFile("passwd1.txt"));
	public static List<String> readEncryptedPassword(List<String> rawPassword) {
		List<String> temp = new ArrayList<>();
		for (var i = 0; i < rawPassword.size(); i += 2) {
			String raw = rawPassword.get(i);
			int first = raw.indexOf(':');
			int second = raw.indexOf(':', first + 1);
			temp.add(raw.substring(first+1,second));
		}
		return temp;
		
	}**/

}
