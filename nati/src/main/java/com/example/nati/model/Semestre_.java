package com.example.nati.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Semestre.class)
public abstract class Semestre_ {

	public static volatile SingularAttribute<Semestre, Long> codigo;
	public static volatile SingularAttribute<Semestre, Matricula> matricula;
	public static volatile SingularAttribute<Semestre, String> descricao;

}

