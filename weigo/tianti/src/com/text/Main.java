package com.text;

import java.util.Scanner;

public class Main {
     @SuppressWarnings("resource")
	public static void main(String[] args) {
	     Scanner scanner = new Scanner(System.in);
	     int n = scanner.nextInt();
	     String c = scanner.next();
	     int value;
        int row=0,sum=0,p=-1;
	     while(true) {
	    	 if((sum*2-1)>n){
	    		 value = n-((sum-p)*2-1);
	    		 row--;
	    		 p-=2;
	    		 break;
	    	 }
	    	 row++;
	    	 p+=2;
	    	 sum+=p;
	     }
	     String arr[] = new String[100];
	     StringBuilder a = new StringBuilder();
       for (int i = 0; i < row; i++) {
    	   for (int j2 = 0; j2 < i; j2++) {
    		   a.append(" ");
		   }
		       for (int j = 0; j < p; j++) {
				a.append(c);
			 }
		       p-=2;
		       System.out.println(a.toString());
		       arr[i] = a.toString();
		       a.delete(0, a.length());
       }
        for (int i = row-2; i >=0; i--) {
			System.out.println(arr[i]);
		}
        System.out.println(value);
       
	}
}
