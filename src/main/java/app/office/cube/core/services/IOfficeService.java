/**
 * 
 */
package app.office.cube.core.services;

import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;

/**
 * @author cyril
 *
 */
public interface IOfficeService<T> {
	public Set<T> find(DetachedCriteria crit);
	public T get(DetachedCriteria crit);
	public T get(Long id);
	public Set<T> findAll();
	public T create(T o);
	public T update(T o);
	public T delete(T o);
	public long getCount();
}
