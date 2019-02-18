package com.example.nati.repository.matricula;

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

import com.example.nati.model.Matricula;
import com.example.nati.model.Matricula_;
import com.example.nati.repository.filter.MatriculaFilter;

public class MatriculaRepositoryQueryImpl implements MatriculaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	

	@Override
	public Page<Matricula> filtrar(MatriculaFilter matriculaFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Matricula> criteria = builder.createQuery(Matricula.class);
		Root<Matricula> root = criteria.from(Matricula.class);
		
		//criar as restrições
		
		Predicate[] predicates = criarRestricoes(matriculaFilter, builder, root);
		criteria.where(predicates);
		
		
		TypedQuery<Matricula> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
 		return new PageImpl<>(query.getResultList(), pageable, total(matriculaFilter));
	}


	private Predicate[] criarRestricoes(MatriculaFilter matriculaFilter, CriteriaBuilder builder,
			Root<Matricula> root) {
			List<Predicate> predicates = new ArrayList<>();
		
			if (!StringUtils.isEmpty(matriculaFilter.getNome())) {
				predicates.add(builder.like(
						builder.lower(root.get(Matricula_.descricao)),"%" + matriculaFilter.getNome().toLowerCase() + "%"));
				
			}
	
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Matricula> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina  = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}
	
	private Long total(MatriculaFilter matriculaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Matricula> root = criteria.from(Matricula.class);
		
		Predicate[] predicates = criarRestricoes(matriculaFilter, builder, root);
		
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	
}
