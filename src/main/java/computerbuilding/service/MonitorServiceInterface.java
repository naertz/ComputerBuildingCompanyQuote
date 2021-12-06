/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 6, 2021
 */
package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.Monitor;

public interface MonitorServiceInterface {
	List<Monitor> getAllMonitors();

	void updateMonitor(Monitor monitor);

	Monitor getMonitorById(long id);

	void deleteMonitorById(long id);

	Page<Monitor> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
