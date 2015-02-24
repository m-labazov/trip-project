package ua.home.trip.enums;

public enum ERegex {

	ENG_TEXT("^[a-zA-Z0-9-_()\\.\\,]+$", "Only english letters, numbers, dots, comas, dashes and underscores are allowed."),
	DATE("^([0-9]{2}-[0-9]{2}-[0-9]{4})$", "Date format is DD-MM-YYYY"),
	URL("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$","URL is invalid.");

	private String expr;
	private String messageText;

	private ERegex(String expr, String messageText) {
		this.expr = expr;
		this.messageText = messageText;

	}

	public String getExpr() {
		return expr;
	}

	public String getMessageText() {
		return messageText;
	}
}
