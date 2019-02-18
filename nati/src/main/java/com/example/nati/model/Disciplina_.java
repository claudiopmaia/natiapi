package com.example.nati.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Disciplina.class)
public abstract class Disciplina_ {

	public static volatile SingularAttribute<Disciplina, Long> codigo;
	public static volatile SingularAttribute<Disciplina, Professor> professor;
	public static volatile SingularAttribute<Disciplina, String> nome;

}

