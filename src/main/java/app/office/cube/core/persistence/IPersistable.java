/**
 * 
 */
package app.office.cube.core.persistence;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cyril
 *
 */
public interface IPersistable extends Serializable{
	long getMyKey();
	void setMyKey(long pkey);
	void setUpdatedDate(Date updatedDate);
	Date getUpdatedDate();
	void setCreatedDate(Date createdDate);
	Date getCreatedDate();
}
