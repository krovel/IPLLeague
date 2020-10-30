package opencsv;

public class CSVException extends Exception{
	public enum ExceptionType{
		UNABLE_TO_PARSE,CSV_ERROR};
	
	ExceptionType type;
	
	CSVException(String message,ExceptionType type){
		super(message);
		this.type=type;
	}
	
	public ExceptionType getExceptionType() {
		return this.type;
	}
}