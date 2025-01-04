INSERT INTO tb_participantes(nome, email) VALUES ('Jose Silva', 'jose@gmail.com');
INSERT INTO tb_participantes(nome, email) VALUES ('Tiago Faria', 'tiago@gmail.com');
INSERT INTO tb_participantes(nome, email) VALUES ('Maria do Rosario', 'maria@gmail.com');
INSERT INTO tb_participantes(nome, email) VALUES ('Teresa Silva', 'teresa@gmail.com');

INSERT INTO tb_categorias(descricao) VALUES ('Curso');
INSERT INTO tb_categorias(descricao) VALUES ('Oficina');


INSERT INTO tb_atividades(nome, descricao, preco, categoria_id) VALUES('Curso de HTML', 'Aprenda HTML de forma pratica', 80.00, 1);
INSERT INTO tb_atividades(nome, descricao, preco, categoria_id) VALUES('Oficina de Github', 'Controle versoes de seus projetos', 50.00, 2);

INSERT INTO tb_participante_atividade(atividade_id, participante_id) VALUES (1, 1);
INSERT INTO tb_participante_atividade(atividade_id, participante_id) VALUES (2, 1);
INSERT INTO tb_participante_atividade(atividade_id, participante_id) VALUES (1, 2);
INSERT INTO tb_participante_atividade(atividade_id, participante_id) VALUES (1, 3);
INSERT INTO tb_participante_atividade(atividade_id, participante_id) VALUES (2, 3);
INSERT INTO tb_participante_atividade(atividade_id, participante_id) VALUES (2, 4);


INSERT INTO tb_blocos(atividade_id, inicio, fim) VALUES(1, '2017-09-25 08:00:00', '2017-09-25 11:00:00');
INSERT INTO tb_blocos(atividade_id, inicio, fim) VALUES(2, '2017-09-25 14:00:00', '2017-09-25 18:00:00');
INSERT INTO tb_blocos(atividade_id, inicio, fim) VALUES(2, '2017-09-26 08:00:00', '2017-09-26 11:00:00');


