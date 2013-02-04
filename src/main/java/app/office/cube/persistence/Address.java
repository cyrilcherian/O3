/**
 * 
 */
package app.office.cube.persistence;

/**
 * @author Cyril
 * 
 */
public class Address extends Persistable {
	private static final long serialVersionUID = 4240531763476274304L;
	private String houseDets = "";
	private String street = "";
	private String lane = "";
	private String city = "";
	private String country = "";
	private String pin = "";
	private String phone = "";

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getHouseDets() {
		return houseDets;
	}

	public void setHouseDets(String houseDets) {
		this.houseDets = houseDets;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
