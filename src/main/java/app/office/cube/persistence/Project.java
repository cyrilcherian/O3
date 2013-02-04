/**
 * 
 */
package app.office.cube.persistence;


/**
 * @author Cyril
 * 
 */
public class Project extends Persistable {
	private static final long serialVersionUID = 4955787093566115548L;
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
