/**
 * 
 */
package app.office.cube.persistence;

import java.util.Date;

import app.office.cube.core.persistence.IPersistable;

/**
 * @author Cyril
 * 
 */
public abstract class Persistable implements IPersistable {
	private static final long serialVersionUID = 5720895704773155567L;
	private long myKey;
	private Date updatedDate;
	private Date createdDate;

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getCreatedDate() {
		return new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cyril.core.persistence.IPersistable#getMyPkey()
	 */
	public long getMyKey() {
		return myKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cyril.core.persistence.IPersistable#setMyPkey(long)
	 */
	public void setMyKey(long pkey) {
		this.myKey = pkey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cyril.core.persistence.IPersistable#setUpdatedDate(java.util.Date)
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cyril.core.persistence.IPersistable#getUpdatedDate()
	 */
	public Date getUpdatedDate() {
		return new Date();
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null || !(arg0 instanceof Persistable)) {
			return false;
		}
		return getMyKey() == ((Persistable) arg0).getMyKey();
	}

}
