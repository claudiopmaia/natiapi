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
	
	
	
-- Tabelas de USUARIO E PERMISSAO 
	
	CREATE TABLE usuario (
		codigo BIGINT(20) PRIMARY KEY,
		nome VARCHAR(50) NOT NULL,
		email VARCHAR(50) NOT NULL,
		senha VARCHAR(150) NOT NULL
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
		codigo BIGINT(20) PRIMARY KEY,
		descricao VARCHAR(50) NOT NULL
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
CREATE TABLE usuario_permissao (
		codigo_usuario BIGINT(20),
		codigo_permissao BIGINT(20),
		PRIMARY KEY (codigo_usuario, codigo_permissao),
		FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
		FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;


	INSERT INTO usuario (codigo, nome, email, senha) VALUES(1, 'Administrador', 'admin@natiapi.com', '$2a$10$VPDLgJc0nhxI5lUVBZxe.ulW3IOZ8Ps9jNUmh9P0/5IfOecpYEoWi');
	INSERT INTO usuario (codigo, nome, email, senha) VALUES(2, 'Aluno', 'aluno@natiapi.com', '$2a$10$VPDLgJc0nhxI5lUVBZxe.ulW3IOZ8Ps9jNUmh9P0/5IfOecpYEoWi');
	INSERT INTO usuario (codigo, nome, email, senha) VALUES(3, 'Professor', 'professor@natiapi.com', '$2a$10$VPDLgJc0nhxI5lUVBZxe.ulW3IOZ8Ps9jNUmh9P0/5IfOecpYEoWi');
	INSERT INTO usuario (codigo, nome, email, senha) VALUES(4, 'Cordenador', 'cordenador@natiapi.com', '$2a$10$VPDLgJc0nhxI5lUVBZxe.ulW3IOZ8Ps9jNUmh9P0/5IfOecpYEoWi');

	
	INSERT INTO permissao (codigo, descricao) VALUES(1,'ROLE_CADASTRAR_ALUNO');
	INSERT INTO permissao (codigo, descricao) VALUES(2,'ROLE_PESQUISAR_ALUNO');
	INSERT INTO permissao (codigo, descricao) VALUES(3,'ROLE_REMOVER_ALUNO');
	INSERT INTO permissao (codigo, descricao) VALUES(4,'ROLE_ATUALIZAR_ALUNO');
	
	INSERT INTO permissao (codigo, descricao) VALUES(5,'ROLE_CADASTRAR_PROFESSOR');
	INSERT INTO permissao (codigo, descricao) VALUES(6,'ROLE_PESQUISAR_PROFESSOR');
	INSERT INTO permissao (codigo, descricao) VALUES(7,'ROLE_REMOVER_PROFESSOR');
	INSERT INTO permissao (codigo, descricao) VALUES(8,'ROLE_ATUALIZAR_PROFESSOR');
	
	INSERT INTO permissao (codigo, descricao) VALUES(9,'ROLE_CADASTRAR_DISCIPLINA');
	INSERT INTO permissao (codigo, descricao) VALUES(10,'ROLE_PESQUISAR_DISCIPLINA');
	INSERT INTO permissao (codigo, descricao) VALUES(11,'ROLE_REMOVER_DISCIPLINA');
	INSERT INTO permissao (codigo, descricao) VALUES(12,'ROLE_ATUALIZAR_DISCIPLINA');
	
	INSERT INTO permissao (codigo, descricao) VALUES(13,'ROLE_CADASTRAR_CURSO');
	INSERT INTO permissao (codigo, descricao) VALUES(14,'ROLE_PESQUISAR_CURSO');
	INSERT INTO permissao (codigo, descricao) VALUES(15,'ROLE_REMOVER_CURSO');
	INSERT INTO permissao (codigo, descricao) VALUES(16,'ROLE_ATUALIZAR_CURSO');
	
	INSERT INTO permissao (codigo, descricao) VALUES(17,'ROLE_CADASTRAR_MATRICULA');
	INSERT INTO permissao (codigo, descricao) VALUES(18,'ROLE_PESQUISAR_MATRICULA');
	INSERT INTO permissao (codigo, descricao) VALUES(19,'ROLE_REMOVER_MATRICULA');
	INSERT INTO permissao (codigo, descricao) VALUES(20,'ROLE_ATUALIZAR_MATRICULA');
	

	-- Permissoes para o admin
	
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 1);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 2);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 3);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 4);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 5);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 6);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 7);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 8);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 9);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 10);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 11);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 12);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 13);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 14);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 15);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 16);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 17);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 18);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 19);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(1, 20);
	
	-- Permissoes para o COORDENADOR
	
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 1);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 2);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 3);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 4);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 5);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 6);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 7);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 8);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 9);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 10);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 11);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 12);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 13);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 14);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 15);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 16);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 17);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 18);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 19);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(4, 20);
	
	-- Permissoes para aluno
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(2, 1);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(2, 9);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(2, 14);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(2, 17);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(2, 20);
	
	-- Permissoes para Professor
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(3, 5);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(3, 6);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(3, 8);
	INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES(3, 14);
	
	
	
	
	
	
	
	
	
	
	