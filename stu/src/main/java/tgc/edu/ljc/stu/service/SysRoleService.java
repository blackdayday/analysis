package tgc.edu.ljc.stu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tgc.edu.ljc.stu.entity.SysRole;
import tgc.edu.ljc.stu.repository.SysRoleRepository;

@Service
public class SysRoleService {
	@Autowired
	private SysRoleRepository sysRoleDAO;

	public <S extends SysRole> S save(S entity) {
		return sysRoleDAO.save(entity);
	}

	public Page<SysRole> findAll(Pageable pageable) {
		return sysRoleDAO.findAll(pageable);
	}

	public List<SysRole> findAll() {
		return sysRoleDAO.findAll();
	}

	public List<SysRole> findAll(Sort sort) {
		return sysRoleDAO.findAll(sort);
	}

	public List<SysRole> findAllById(Iterable<Integer> ids) {
		return sysRoleDAO.findAllById(ids);
	}

	public <S extends SysRole> List<S> saveAll(Iterable<S> entities) {
		return sysRoleDAO.saveAll(entities);
	}

	public <S extends SysRole> S saveAndFlush(S entity) {
		return sysRoleDAO.saveAndFlush(entity);
	}

	public <S extends SysRole> Page<S> findAll(Example<S> example, Pageable pageable) {
		return sysRoleDAO.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		sysRoleDAO.deleteAllInBatch();
	}

	public SysRole getOne(Integer id) {
		return sysRoleDAO.getOne(id);
	}

	public <S extends SysRole> List<S> findAll(Example<S> example) {
		return sysRoleDAO.findAll(example);
	}

	public void deleteById(Integer id) {
		sysRoleDAO.deleteById(id);
	}

	public <S extends SysRole> List<S> findAll(Example<S> example, Sort sort) {
		return sysRoleDAO.findAll(example, sort);
	}

	public void delete(SysRole entity) {
		sysRoleDAO.delete(entity);
	}

	public void deleteAll(Iterable<? extends SysRole> entities) {
		sysRoleDAO.deleteAll(entities);
	}

	public void deleteAll() {
		sysRoleDAO.deleteAll();
	}
	
	public SysRole findById(Integer id) {
		Optional<SysRole> optional = sysRoleDAO.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
}
