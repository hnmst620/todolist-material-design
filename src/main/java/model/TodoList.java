package model;

public class TodoList {
	private int id;
	private String name;
	private boolean done;
	
	public TodoList() {}
	
	public TodoList(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public TodoList(String name) {
		this.name = name;
	}
	
	public int getId() { return id;}
	public void setId(int id) { this.id = id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public boolean isDone() {return done;}
	public void setDone(boolean done) {this.done = done;}

}
