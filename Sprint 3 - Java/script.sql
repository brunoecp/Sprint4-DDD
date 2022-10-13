CREATE TABLE dtc_candidato (
    cd_candidato            NUMBER(6) NOT NULL,
    nm_candidato            VARCHAR2(100) NOT NULL,
    ds_email                VARCHAR2(100) NOT NULL,
    nr_telefone             NUMBER(12) NOT NULL,
    nr_cpf                  NUMBER(11) NOT NULL,
    ds_genero               VARCHAR2 (2) NOT NULL,
    ds_senha                VARCHAR2(20) NOT NULL,
    dt_nascimento           DATE NOT NULL,
    ds_area_profissional    VARCHAR2(100),
    ds_nivel_profissional   VARCHAR2(100),
    ds_informacao_adicional VARCHAR2(200),
    ds_endereco             VARCHAR2(100),
    ds_github               VARCHAR2(100)
);

ALTER TABLE dtc_candidato ADD CONSTRAINT pk_dtc_candidato PRIMARY KEY ( cd_candidato );

ALTER TABLE dtc_candidato ADD CONSTRAINT un_dtc_candidato UNIQUE ( nr_cpf,
                                                                   ds_email );

CREATE TABLE dtc_empresa (
    cd_empresa       NUMBER(6) NOT NULL,
    nm_empresa       VARCHAR2(100) NOT NULL,
    nr_cnpj          NUMBER(14) NOT NULL,
    ds_area_trabalho VARCHAR2(200) NOT NULL,
    ds_senha         VARCHAR2(50) NOT NULL,
    nr_telefone      NUMBER(11),
    ds_email         VARCHAR2(100) NOT NULL,
    ds_ceo           VARCHAR2(100),
    ds_site          VARCHAR2(200),
    ds_youtube       VARCHAR2(200)
);

ALTER TABLE dtc_empresa ADD CONSTRAINT pk_dtc_empresa PRIMARY KEY ( cd_empresa );

ALTER TABLE dtc_empresa ADD CONSTRAINT un_dtc_empresa UNIQUE ( nr_cnpj,
                                                               nm_empresa );

CREATE TABLE dtc_exp_profissional (
    id_exp_profissional NUMBER(6) NOT NULL,
    cd_candidato        NUMBER(6) NOT NULL,
    nm_empresa          VARCHAR2(100) NOT NULL,
    dt_inicio           DATE NOT NULL,
    dt_termino          DATE NOT NULL,
    ds_setor_atuacao    VARCHAR2(100) NOT NULL
);

ALTER TABLE dtc_exp_profissional ADD CONSTRAINT ck_exp_profissional_dt CHECK ( dt_termino > dt_inicio );

ALTER TABLE dtc_exp_profissional ADD CONSTRAINT pk_dtc_experiencia_profl PRIMARY KEY ( id_exp_profissional );

CREATE TABLE dtc_form_academica (
    id_curso                NUMBER(6) NOT NULL,
    cd_candidato            NUMBER(6) NOT NULL,
    cd_instituicao          NUMBER(6) NOT NULL,
    ds_escolaridade         VARCHAR2(100) NOT NULL,
    dt_inicio               DATE NOT NULL,
    dt_conclusao            DATE NOT NULL,
    ds_area_estudo          VARCHAR2(200) NOT NULL,
    ds_informacao_adicional VARCHAR2(200)
);

ALTER TABLE dtc_form_academica ADD CONSTRAINT ck_form_academica_dt CHECK ( dt_conclusao > dt_inicio );

ALTER TABLE dtc_form_academica ADD CONSTRAINT pk_dtc_form_academica PRIMARY KEY ( id_curso );

CREATE TABLE dtc_form_complementar (
    id_curso_complemnt NUMBER(6) NOT NULL,
    cd_instituicao     NUMBER(6) NOT NULL,
    cd_candidato       NUMBER(6) NOT NULL,
    nm_curso           VARCHAR2(50) NOT NULL,
    dt_inicio          DATE NOT NULL,
    dt_conclusao       DATE NOT NULL,
    ds_area_de_estudo  VARCHAR2(200) NOT NULL
);

ALTER TABLE dtc_form_complementar ADD CONSTRAINT ck_form_complementar_dt CHECK ( dt_conclusao > dt_inicio );

ALTER TABLE dtc_form_complementar ADD CONSTRAINT pk_dtc_formacao_complemnt PRIMARY KEY ( id_curso_complemnt );

CREATE TABLE dtc_idioma (
    id_curso_lingua NUMBER(6) NOT NULL,
    cd_candidato    NUMBER(6) NOT NULL,
    cd_instituicao  NUMBER(6) NOT NULL,
    nm_idioma       VARCHAR2(50) NOT NULL,
    ds_nivel        VARCHAR2(100) NOT NULL
);

ALTER TABLE dtc_idioma ADD CONSTRAINT pk_dtc_idioma PRIMARY KEY ( id_curso_lingua );

CREATE TABLE dtc_inscricao (
    cd_empresa   NUMBER NOT NULL,
    id_vaga      NUMBER(6) NOT NULL,
    cd_candidato NUMBER(6) NOT NULL,
    dt_envio     DATE NOT NULL
);

ALTER TABLE dtc_inscricao
    ADD CONSTRAINT pk_dtc_inscricao PRIMARY KEY ( cd_empresa,
                                                  id_vaga,
                                                  cd_candidato );

CREATE TABLE dtc_instituicao (
    cd_instituicao NUMBER(6) NOT NULL,
    nr_cnpj        NUMBER(14) NOT NULL,
    nm_instituicao VARCHAR2(100) NOT NULL,
    ds_endereco    VARCHAR2(200) NOT NULL
);

ALTER TABLE dtc_instituicao ADD CONSTRAINT pk_dtc_instituicao PRIMARY KEY ( cd_instituicao );

ALTER TABLE dtc_instituicao ADD CONSTRAINT un_dtc_instituicao UNIQUE ( nr_cnpj );

CREATE TABLE dtc_vaga (
    id_vaga            NUMBER(6) NOT NULL,
    cd_empresa         NUMBER(6) NOT NULL,
    ds_cargo           VARCHAR2(200) NOT NULL,
    ds_nivel           VARCHAR2(100) NOT NULL,
    ds_localizacao     VARCHAR2(100) NOT NULL,
    vl_salario         NUMBER(7, 2) NOT NULL,
    ds_requisitos_vaga VARCHAR2(200) NOT NULL,
    ds_tipo            VARCHAR2(100) NOT NULL
);

ALTER TABLE dtc_vaga ADD CONSTRAINT pk_dtc_vagas PRIMARY KEY ( id_vaga );

ALTER TABLE dtc_inscricao
    ADD CONSTRAINT fk_dtc_candidato FOREIGN KEY ( cd_candidato )
        REFERENCES dtc_candidato ( cd_candidato );

ALTER TABLE dtc_vaga
    ADD CONSTRAINT fk_dtc_empresa FOREIGN KEY ( cd_empresa )
        REFERENCES dtc_empresa ( cd_empresa );

ALTER TABLE dtc_form_academica
    ADD CONSTRAINT fk_dtc_instituicao FOREIGN KEY ( cd_instituicao )
        REFERENCES dtc_instituicao ( cd_instituicao );

ALTER TABLE dtc_inscricao
    ADD CONSTRAINT fk_dtc_vagas FOREIGN KEY ( id_vaga )
        REFERENCES dtc_vaga ( id_vaga );

ALTER TABLE dtc_form_academica
    ADD CONSTRAINT fkv1_dtc_candidato FOREIGN KEY ( cd_candidato )
        REFERENCES dtc_candidato ( cd_candidato );

ALTER TABLE dtc_idioma
    ADD CONSTRAINT fkv1_dtc_instituicao FOREIGN KEY ( cd_instituicao )
        REFERENCES dtc_instituicao ( cd_instituicao );

ALTER TABLE dtc_idioma
    ADD CONSTRAINT fkv2_dtc_candidato FOREIGN KEY ( cd_candidato )
        REFERENCES dtc_candidato ( cd_candidato );

ALTER TABLE dtc_form_complementar
    ADD CONSTRAINT fkv3_dtc_instituicao FOREIGN KEY ( cd_instituicao )
        REFERENCES dtc_instituicao ( cd_instituicao );

ALTER TABLE dtc_exp_profissional
    ADD CONSTRAINT fkv4_dtc_candidato FOREIGN KEY ( cd_candidato )
        REFERENCES dtc_candidato ( cd_candidato );

ALTER TABLE dtc_form_complementar
    ADD CONSTRAINT fkv5_dtc_candidato FOREIGN KEY ( cd_candidato )
        REFERENCES dtc_candidato ( cd_candidato );
        
CREATE SEQUENCE sq_dtc_candidato START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE sq_dtc_empresa START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE sq_dtc_candidato START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE sq_dtc_empresa START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE sq_dtc_vaga START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE sq_dtc_exp_profissional START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE sq_dtc_form_academica START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE sq_dtc_form_complementar START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE sq_dtc_idioma START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE sq_dtc_inscricao START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE SEQUENCE sq_dtc_instituicao START WITH 1 INCREMENT BY 1 NOCACHE;