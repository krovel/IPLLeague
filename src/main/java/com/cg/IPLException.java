package com.cg;

public class IPLException extends Exception{
	public enum ExceptionType{
		FILE_INCORRECT,FILE_TYPE_INCORRECT,DELIMITER_INCORRECT,HEADER_INCORRECT };
	
	ExceptionType type;
	
	public IPLException(String message,ExceptionType type){
		super(message);
		this.type=type;
	}
	
	public ExceptionType getExceptionType() {
		return this.type;
	}
}