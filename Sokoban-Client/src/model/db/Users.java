package model.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Sets table of users
 *
 */
@Entity(name = "Users")
public class Users implements Serializable {
	@Id
	@Column(name="userName")
	private String userName;

	

	public Users(String name) {
		setName(name);
	}

	public Users() {
	}


	public String getName() {
		return this.userName;
	}

	public void setName(String name) {
		this.userName=name;
	}


@Override
	public String toString() {
		return userName;
	}
}