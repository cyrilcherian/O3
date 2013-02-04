/**
 * 
 */
package app.office.cube.persistence;


/**
 * @author Cyril
 *
 */
public class WeekHoliday extends Persistable {
	private static final long serialVersionUID = 4323164990271403428L;
	private Days day;
	private Frequency frequency;

	public Days getDay() {
		return day;
	}
	public void setDay(Days day) {
		this.day = day;
	}
	
	public Frequency getFrequency() {
		return frequency;
	}
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	public Days[] getDays(){
		return Days.values();
	}
	public Frequency[] getFrequencies(){
		return Frequency.values();
	}
	
}
