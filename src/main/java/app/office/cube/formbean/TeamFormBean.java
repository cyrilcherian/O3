/**
 * 
 */
package app.office.cube.formbean;

import java.util.LinkedHashSet;

/**
 * @author Cyril
 * 
 */
public class TeamFormBean {

	private String name;
	private String description;
	private Long[] users;
	private Long[] teams;

	public Long[] getUsers() {
		return users;
	}

	public void setUsers(Long[] users) {
		this.users = users;
	}

	public LinkedHashSet<Long> getUserSet() {
		LinkedHashSet<Long> userSet = new LinkedHashSet<Long>();
		if (users == null){
			return userSet;
		}
		for (Long key : users) {
			userSet.add(key);
		}
		return userSet;
	}

	public LinkedHashSet<Long> getTeamSet() {
		LinkedHashSet<Long> teamSet = new LinkedHashSet<Long>();
		if (teams == null){
			return teamSet;
		}
		for (Long key : teams) {
			teamSet.add(key);
		}
		return teamSet;
	}

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

	public Long[] getTeams() {
		return teams;
	}

	public void setTeams(Long[] teams) {
		this.teams = teams;
	}
}
