package com.samnicholson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	private static double APPLE_PRICE = 0.60;
	private static double APPLE_DISCOUNT = 0.5;
	private static int APPLE_MULITPLE = 2;
	private static double ORANGE_PRICE = 0.25;
	private static double ORANGE_DISCOUNT = 0.666;
	private static int ORANGE_MULITPLE = 3;
	
	public static void main(String[] args) {
		
		if( args.length > 0 ) {
		
			List<String> items = new ArrayList<String>();
			int apples = 0;
			int oranges = 0;
			
			//Organise input
			for( String arg : args ) {
				if( arg.indexOf(",") >= 0 ) {
					Collections.addAll(items, arg.split(",") );
				} else {
					items.add(arg);
				}
			}
			
			//determine the counts of each item type
			for( String item : items ) {
				if( item.trim().equalsIgnoreCase("Apple") ) {
					apples++;
				} else if (item.trim().equalsIgnoreCase("Orange") ) {
					oranges++;
				} else {
					throw new RuntimeException("Unexpected item ("+item+") in bagging area!");
				}
			}
			
			//calculate the cost
			double total_cost = calculate(apples, "apple") + calculate(oranges, "orange");
			
			DecimalFormat twoDP = new DecimalFormat("0.00");
			
			System.out.println( "Total owed: £" + twoDP.format(total_cost) );
		
		} else {
			System.out.println("Your shopping cart is empty.");
		}
		
	}
	
	private static double calculate(int total, String fruit) {
		
		int difference = total % 3;
		int eligibleForOffer = 0;
		int multiple = 0;
		double amountOwed = 0.00;
		double cost = 0.00;
		double discount = 0.0;
		
		if( fruit.equalsIgnoreCase("apple") ) {
			multiple = APPLE_MULITPLE;
			cost = APPLE_PRICE;
			discount = APPLE_DISCOUNT;
		} else {
			multiple = ORANGE_MULITPLE;
			cost = ORANGE_PRICE;
			discount = ORANGE_DISCOUNT;
		}
		
		difference = total % multiple;
		
		eligibleForOffer = total - difference;
		
		amountOwed = ((eligibleForOffer * discount) + difference) * cost;
		
		return amountOwed;
	}

}