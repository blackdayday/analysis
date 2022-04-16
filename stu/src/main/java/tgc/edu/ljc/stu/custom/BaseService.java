package tgc.edu.ljc.stu.custom;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class BaseService<T,ID> {
	@Autowired
	private BaseRepository<T, ID> baseDAO;

	public <S extends T> S save(S entity) {
		return baseDAO.save(entity);
	}

	public T findOne(Example<T> example) {
		Optional<T> optional = baseDAO.findOne(example);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public T  findOne(Specification<T> spec) {
		Optional<T> optional = baseDAO.findOne(spec);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Page<T> findAll(Pageable pageable) {
		return baseDAO.findAll(pageable);
	}

	public List<T> findAll() {
		return baseDAO.findAll();
	}

	public List<T> findAll(Sort sort) {
		return baseDAO.findAll(sort);
	}

	public List<T> findAll(Specification<T> spec) {
		return baseDAO.findAll(spec);
	}

	public List<T> findAllById(Iterable<ID> ids) {
		return baseDAO.findAllById(ids);
	}

	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return baseDAO.findAll(spec, pageable);
	}

	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		return baseDAO.saveAll(entities);
	}

	public T findById(ID id) {
		Optional<T> optional = baseDAO.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void flush() {
		baseDAO.flush();
	}

	public List<T> findAll(Specification<T> spec, Sort sort) {
		return baseDAO.findAll(spec, sort);
	}

	public <S extends T> S saveAndFlush(S entity) {
		return baseDAO.saveAndFlush(entity);
	}

	public boolean existsById(ID id) {
		return baseDAO.existsById(id);
	}

	public void deleteInBatch(Iterable<T> entities) {
		baseDAO.deleteInBatch(entities);
	}

	public long count(Specification<T> spec) {
		return baseDAO.count(spec);
	}

	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return baseDAO.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		baseDAO.deleteAllInBatch();
	}

	public T getOne(ID id) {
		return baseDAO.getOne(id);
	}

	public <S extends T> long count(Example<S> example) {
		return baseDAO.count(example);
	}

	public <S extends T> boolean exists(Example<S> example) {
		return baseDAO.exists(example);
	}

	public <S extends T> List<S> findAll(Example<S> example) {
		return baseDAO.findAll(example);
	}

	public long count() {
		return baseDAO.count();
	}

	public void deleteById(ID id) {
		baseDAO.deleteById(id);
	}

	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return baseDAO.findAll(example, sort);
	}

	public void delete(T entity) {
		baseDAO.delete(entity);
	}

	public void deleteAll(Iterable<? extends T> entities) {
		baseDAO.deleteAll(entities);
	}

	public void deleteAll() {
		baseDAO.deleteAll();
	}
	
	
}
