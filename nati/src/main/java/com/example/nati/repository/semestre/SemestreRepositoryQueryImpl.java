package com.example.nati.repository.semestre;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.nati.model.Curso;
import com.example.nati.model.Semestre;
import com.example.nati.model.Semestre_;
import com.example.nati.repository.filter.CursoFilter;
import com.example.nati.repository.filter.SemestreFilter;

public class SemestreRepositoryQueryImpl implements SemestreRepositoryQuery{
	
	@Autowired
	private EntityManager manager;

	@Override
	public Page<Semestre> filtrar(SemestreFilter semestreFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Semestre> criteria = builder.createQuery(Semestre.class);
		Root<Semestre> root = criteria.from(Semestre.class);
		
		//criar as restrições
		
		Predicate[] predicates = criarRestricoes(semestreFilter, builder, root);
		criteria.where(predicates);
		
		
		TypedQuery<Semestre> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
 		return new PageImpl<>(query.getResultList(), pageable, total(semestreFilter));
	}

	private Long total(SemestreFilter semestreFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Semestre> root = criteria.from(Semestre.class);
		
		Predicate[] predicates = criarRestricoes(semestreFilter, builder, root);
		
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(SemestreFilter semestreFilter, CriteriaBuilder builder,
		Root<Semestre> root) {
		List<Predicate> predicates = new ArrayList<>();
	
		if (!StringUtils.isEmpty(semestreFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Semestre_.descricao)),"%" + semestreFilter.getDescricao().toLowerCase() + "%"));
			
		}

	return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Semestre> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina  = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}
}
