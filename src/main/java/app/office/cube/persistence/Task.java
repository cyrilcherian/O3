/**
 * 
 */
package app.office.cube.persistence;


/**
 * @author Cyril
 * 
 */
public class Task extends Persistable {
	private static final long serialVersionUID = 7976964299585573267L;
	
	private String name;
	private String description;
	
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
	
}
