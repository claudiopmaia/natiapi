package com.example.nati.repository.disciplina;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.nati.model.Disciplina;
import com.example.nati.model.Disciplina_;
import com.example.nati.repository.filter.DisciplinaFilter;

public class DisciplinaRepositoryQueryImpl implements DisciplinaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	

	@Override
	public Page<Disciplina> filtrar(DisciplinaFilter disciplinaFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Disciplina> criteria = builder.createQuery(Disciplina.class);
		Root<Disciplina> root = criteria.from(Disciplina.class);
		
		//criar as restrições
		
		Predicate[] predicates = criarRestricoes(disciplinaFilter, builder, root);
		criteria.where(predicates);
		
		
		TypedQuery<Disciplina> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
 		return new PageImpl<>(query.getResultList(), pageable, total(disciplinaFilter));
	}


	private Predicate[] criarRestricoes(DisciplinaFilter disciplinaFilter, CriteriaBuilder builder,
			Root<Disciplina> root) {
			List<Predicate> predicates = new ArrayList<>();
		
			if (!StringUtils.isEmpty(disciplinaFilter.getNome())) {
				predicates.add(builder.like(
						builder.lower(root.get(Disciplina_.nome)),"%" + disciplinaFilter.getNome().toLowerCase() + "%"));
				
			}
	
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Disciplina> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina  = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}
	
	private Long total(DisciplinaFilter disciplinaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Disciplina> root = criteria.from(Disciplina.class);
		
		Predicate[] predicates = criarRestricoes(disciplinaFilter, builder, root);
		
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	
}
