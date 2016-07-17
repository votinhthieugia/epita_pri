package fr.epita.pri.rackrepresentator.models;

public class User {
	public static final int Normal = 0;
	public static final int Admin = 1;
	
	private int id;
	private String name;
	private String password;
	private int type;
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public User(String name, String password, int type) {
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	public User(int id, String name, String password, int type) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isAdmin() {
		return type == Admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}