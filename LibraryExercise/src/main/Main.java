package main;

import java.io.IOException;

import logic.*;

public class Main {
	public static void main(String[] args) {

		try {
			Library.fromFile("lib.txt");
			System.out.println("happy");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
