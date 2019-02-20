package com.example.nati.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Curso.class)
public abstract class Curso_ {

	public static volatile SingularAttribute<Curso, Long> codigo;
	public static volatile SingularAttribute<Curso, Disciplina> disciplina;
	public static volatile SingularAttribute<Curso, String> nome;

}

