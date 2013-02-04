/**
 * 
 */
package app.office.cube.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.office.cube.core.services.IExcelService;
import app.office.cube.excel.WorkLayouter;
import app.office.cube.excel.WorkReporter;
import app.office.cube.persistence.Work;

/**
 * @author cyril
 * 
 */
@Service("worksService")
@Repository
public class WorkService implements IExcelService<Work> {
	private SessionFactory sf;
	private static final String COUNT = "select count(w) from Work w";

	@Autowired
	public WorkService(@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(readOnly = true)
	public Set<Work> find(DetachedCriteria crit) {
		return new HashSet<Work>(crit.getExecutableCriteria(
				sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<Work> findAll() {
		return new HashSet<Work>(sf.getCurrentSession()
				.getNamedQuery("listWorks").list());
	}

	@Transactional
	public Work create(Work u) {
		long id = (Long) sf.getCurrentSession().save(u);
		return (Work) sf.getCurrentSession().get(Work.class, id);
	}

	@Transactional
	public Work update(Work o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (Work) sf.getCurrentSession().get(Work.class, o.getMyKey());
	}

	@Transactional(readOnly = true)
	public Work get(DetachedCriteria crit) {
		return (Work) crit.getExecutableCriteria(sf.getCurrentSession())
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	public Work get(Long id) {
		return (Work) sf.getCurrentSession().get(Work.class, id);
	}

	@Transactional
	public Work delete(Work w) {
		sf.getCurrentSession().delete(w);
		return w;
	}
	
	@Transactional(readOnly = true)
	public Workbook generateReport(Set<Long> users, Date startDate, Date endDate) {
		HSSFWorkbook wb = new HSSFWorkbook();
		Sheet worksheet =  wb.createSheet("Work Worksheet");
		WorkLayouter layout = new WorkLayouter();
		layout.buildTitle(worksheet);
		layout.buildHeaders(worksheet, 0, 0);
		WorkReporter reporter = new WorkReporter();
		for (Long u : users){
			DetachedCriteria dc = DetachedCriteria.forClass(Work.class);
			dc.createAlias("user", "u");
			dc.add(Restrictions.ge("date", startDate));
			dc.add(Restrictions.le("date", endDate));
			dc.add(Restrictions.eq("u.myKey", u));
			Set<Work> workList = find(dc);
			for (Work work : workList) {
				reporter.fillReport(worksheet, work);
			}
		}
		return wb;
	}

	@Transactional(readOnly = true)
	public long getCount() {
		Long count =
				(Long) sf.getCurrentSession().createQuery(COUNT)
				.uniqueResult();

		return count;
	}

}
