/**
 * 
 */
package app.office.cube.formbean;

/**
 * @author cyril
 * 
 */
public class Event {
	public static String LIGHT_CORAL = "#F08080";
	public static String ROYAL_BLUE = "#6A5ACD";
	public static String OLIVE = "olive";
	
	private String id = "";
	private String title;
	private String start = "";
	private boolean allDay = true;
	private boolean editable = false;
	private String color = ROYAL_BLUE;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
