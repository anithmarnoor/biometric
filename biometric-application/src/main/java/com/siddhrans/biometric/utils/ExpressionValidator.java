package com.siddhrans.biometric.utils;

import java.util.ArrayList;
import java.util.Stack;

public class ExpressionValidator {

	private String expression;

	private Stack<Character> expressionStack;

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String isExpressionValid() {
		expressionStack = new Stack<Character>();
		boolean operatorFound = false;
		for(int i=0; i<expression.length(); i++) {
			switch(expression.charAt(i)) {
			case '(' :
				expressionStack.push('(');
				break;
			case ')' :
				if(expressionStack.isEmpty())
					return "Opening and Closing Braces does not match.";
				expressionStack.pop();
				operatorFound = false;
				break;
			case '+' :
			case '-' :
			case '*' :
			case '%' :
			case '/' :
			case '&' :
			case '|' :
				if(i==(expression.length()-1) || i==0 || operatorFound) {
					return "Syntax Error at: "+expression.charAt(i) +" Position: "+(i+1);
				}
				operatorFound = true;
				break;
			default :
				operatorFound = false;
				break;
			}
		}
		if(expressionStack.isEmpty())
			return "Expression is valid";
		else 
			return "Opening and Closing Braces does not match";
	}

	public ArrayList<String> splitFormula() {
		if(isExpressionValid().indexOf("Expression is valid")>0) {
			ArrayList<String> array = new ArrayList<String>();
			ArrayList<Integer> listOfIndexes = new ArrayList<Integer>();

			for(int i=0; i<expression.length(); i++) {
				switch(expression.charAt(i)) {
				case '(' :
				case ')' :
				case '+' :
				case '-' :
				case '*' :
				case '%' :
				case '/' :
				case '&' :
				case '|' :
					array.add(expression.charAt(i)+"");
					listOfIndexes.add(i+1);
					break;
				default:
					if(listOfIndexes.size() >0) {
						array.add(expression.substring(listOfIndexes.get(listOfIndexes.size()-1), i+1));
						listOfIndexes.clear();
					} else {
						array.add(expression.substring(0, i+1));
					}
					break;
				}
			}
			System.out.println("array = "+array);	
			return array;
		}
		return null;
	}
}
