package exam;

import java.util.Scanner;

public class Yourpotentialfridens {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n=scanner.nextInt();
		int m=scanner.nextInt();
		int[] user = new int[n+1];
		int[] book = new int[m+1]; 
		for(int i=1;i<=n;i++) {
			user[i]=scanner.nextInt();
			book[user[i]]++;
		}
		for(int i=1;i<=n;i++) {
			if(book[user[i]]==1) {
				System.out.println("BeiJu");
			}
			else {
				System.out.println(book[user[i]]-1);
			}
		}
		
	}

}
