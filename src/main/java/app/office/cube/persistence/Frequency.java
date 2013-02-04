/**
 * 
 */
package app.office.cube.persistence;




/**
 * @author Cyril
 *
 */
public enum Frequency {
	EVERY,FIRST,LAST,SECOND,THIRD,FOURTH,FIFTH;
	public String getName(){
		return this.toString();
	}
	public String getCode(){
		return this.toString();
	}
}
