/**
 * 
 */
package app.office.cube.persistence;

/**
 * @author Cyril
 * 
 */
public enum HourlyLeave {
	QUARTER_OF_DAY(0.25F), HALF_OF_DAY(0.5F), THREE_QUARTER_OF_DAY(0.75F), FULL_DAY(1F);
	
	private float day;
	private HourlyLeave(float day) {
		this.day = day;
	}
	public String getName() {
		return this.toString();
	}

	public String getCode() {
		return this.toString();
	}
	public float getDay(){
		return this.day;
	}
}
