CREATE TABLE aluno (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo TINYINT(1) NOT NULL,
	email VARCHAR(50)

	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	INSERT INTO aluno (nome, ativo) VALUES('Mirela Campos',1);
	INSERT INTO aluno (nome, ativo) VALUES('Paulo Silva', 1);
	INSERT INTO aluno (nome, ativo) VALUES('Chico Anizio', 1);
	INSERT INTO aluno (nome, ativo) VALUES('Medeiros', 1);
	INSERT INTO aluno (nome, ativo) VALUES('Felipe', 1);
	
	
CREATE TABLE professor (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL

	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	
	INSERT INTO professor (nome) VALUES('Roberio Patricio');
	INSERT INTO professor (nome) VALUES('Carlos Gomes');
	INSERT INTO professor (nome) VALUES('Eurico Souza');
	INSERT INTO professor (nome) VALUES('Ze Maria');
	INSERT INTO professor (nome) VALUES('Vania');
	

CREATE TABLE disciplina (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	codigo_professor BIGINT(20) NOT NULL,
	CONSTRAINT fk_disciplina_professor FOREIGN KEY (codigo_professor) REFERENCES professor(codigo)


	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	
	INSERT INTO disciplina (nome, codigo_professor) VALUES('Matematica', 1);
	INSERT INTO disciplina (nome, codigo_professor) VALUES('Banco de dados', 2);
	INSERT INTO disciplina (nome, codigo_professor) VALUES('Moral e Civica', 3);
	INSERT INTO disciplina (nome, codigo_professor) VALUES('Logica', 4);
	INSERT INTO disciplina (nome, codigo_professor) VALUES('Estatitisca1', 5);
	
	
	
CREATE TABLE curso (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	codigo_disciplina BIGINT(20) NOT NULL,
	CONSTRAINT fk_curso_disciplina FOREIGN KEY (codigo_disciplina) REFERENCES disciplina(codigo)


	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	

	INSERT INTO curso (nome,codigo_disciplina) VALUES('Fisica', 2);
	INSERT INTO curso (nome,codigo_disciplina) VALUES('Informatica', 5);
	INSERT INTO curso (nome,codigo_disciplina) VALUES('Historia', 3);
	INSERT INTO curso (nome,codigo_disciplina) VALUES('Ciencia da coputacao', 2);
	INSERT INTO curso (nome,codigo_disciplina) VALUES('Sistema da informacao', 4);
	INSERT INTO curso (nome,codigo_disciplina) VALUES('Estatitisca', 1);



CREATE TABLE matricula (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	codigo_aluno BIGINT(20) NOT NULL,
	codigo_curso BIGINT(20) NOT NULL,
	CONSTRAINT fk_matricula_aluno FOREIGN KEY (codigo_aluno) REFERENCES aluno(codigo),
	CONSTRAINT fk_matricula_curso FOREIGN KEY (codigo_curso) REFERENCES curso(codigo)


	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	
	
	INSERT INTO matricula (descricao, codigo_aluno, codigo_curso) VALUES("Matricula primeiro simestre",1, 2);
	INSERT INTO matricula (descricao, codigo_aluno, codigo_curso) VALUES("Matricula segundo simestre",2, 1);
	INSERT INTO matricula (descricao, codigo_aluno, codigo_curso) VALUES("Matricula terceiro simestre",4, 3);
	INSERT INTO matricula (descricao, codigo_aluno, codigo_curso) VALUES("Matricula quarto simestre",2, 2);
	INSERT INTO matricula (descricao,  codigo_aluno, codigo_curso) VALUES("Matricula quinta simestre",1, 4);
	

CREATE TABLE semestre (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	codigo_matricula BIGINT(20) NOT NULL,
	CONSTRAINT fk_semestre_matricula FOREIGN KEY (codigo_matricula) REFERENCES matricula(codigo)


	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	INSERT INTO semestre (descricao,  codigo_matricula) VALUES("Primeiro Semestre", 2);
	INSERT INTO semestre (descricao,  codigo_matricula) VALUES("Segundo Semestre", 1);
	INSERT INTO semestre (descricao,  codigo_matricula) VALUES("Quarto Semestre", 5);
	INSERT INTO semestre (descricao,  codigo_matricula) VALUES("Terceriro Semestre", 2);
	INSERT INTO semestre (descricao,  codigo_matricula) VALUES("Quinte Semestre", 5);
	
	
	
	
	
	
	
	
	
	
	
	
	