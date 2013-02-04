/**
 * 
 */
package app.office.cube.persistence;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Cyril
 * 
 */
@JsonIgnoreProperties({ "officeAddress", "homeAddress", "roles", "password",
		"confirmPassword" })
public class User extends Persistable {
	private static final long serialVersionUID = -6281933613310003133L;
	private String userName;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String corporateID;
	private String authorities;
	private String email;
	private Address officeAddress;
	private Address homeAddress;
	private Set<Role> roles;
	private boolean active = true;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String myPassword) {
		this.password = myPassword;
	}

	public String getCorporateID() {
		return corporateID;
	}

	public void setCorporateID(String corporateID) {
		this.corporateID = corporateID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisplayName() {
		return getLastName() + ", " + getFirstName();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User))
			return false;
		if (((User) obj).getMyKey() == getMyKey())
			return true;
		else
			return false;
	}
}
