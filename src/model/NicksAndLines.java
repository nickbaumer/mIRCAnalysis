package model;

public class NicksAndLines {

	String nick = new String();
	Long lines = new Long(0);
	
	public NicksAndLines() {
		
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Long getLines() {
		return lines;
	}

	public void setLines(Long lines) {
		this.lines = lines;
	}
}
