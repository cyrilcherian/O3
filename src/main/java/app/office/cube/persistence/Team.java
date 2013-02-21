/**
 * 
 */
package app.office.cube.persistence;

import java.util.LinkedHashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Cyril
 * 
 */
@JsonIgnoreProperties({ "users", "teams" })
public class Team extends Persistable {
	private static final long serialVersionUID = 6118514674036062798L;
	private String name;
	private String description;
	private Set<User> users;
	private Set<Team> teams;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

}
