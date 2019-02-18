package com.example.nati.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Matricula.class)
public abstract class Matricula_ {

	public static volatile SingularAttribute<Matricula, Long> codigo;
	public static volatile SingularAttribute<Matricula, Aluno> aluno;
	public static volatile SingularAttribute<Matricula, Curso> curso;
	public static volatile SingularAttribute<Matricula, String> descricao;

}

