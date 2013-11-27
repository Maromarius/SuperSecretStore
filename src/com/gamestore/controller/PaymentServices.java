package com.gamestore.controller;

public class PaymentServices {

	private String creditCardNumber = "";
	
	public PaymentServices(String creditCard)
	{
		creditCardNumber = creditCard;
	}
	
	public boolean VerifyPayment()
	{
		if(creditCardNumber.equals(""))
			return false;
		else
			return true;
	}
}
