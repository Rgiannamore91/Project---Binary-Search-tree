package edu.howardcc.cmsy167.simplemap;
public class Line{
	private int LineNumber;
	private String content;
	public Line(int lineNumber, String content) {
		super();
		LineNumber = lineNumber;
		this.content = content;
	}
	public int getLineNumber() {
		return LineNumber;
	}
	public void setLineNumber(int lineNumber) {
		LineNumber = lineNumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}