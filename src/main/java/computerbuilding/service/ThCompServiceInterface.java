package computerbuilding.service;

import java.util.List;

import org.springframework.data.domain.Page;

import computerbuilding.beans.ThComp;

public interface ThCompServiceInterface {
	List<ThComp> getAllThComp();
	void updateThComp(ThComp thcomp);
	ThComp getThCompById(long id);
	void deleteThCompById(long id);
	Page<ThComp> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
