
public class LineMessage {
	
	String time;
	String name;
	String step;
	
	public LineMessage(String time) {
		super();
		this.time = time;
	}

	public LineMessage(String time, String name, String step) {
		super();
		this.time = time;
		this.name = name;
		this.step = step;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	
	@Override
	public String toString() {
		return "CurrentTime: "+time+" Name:"+name+" Step: "+step;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LineMessage){
			return ((LineMessage) obj).getName().equals(name);
		}
		return false;
	}
}
