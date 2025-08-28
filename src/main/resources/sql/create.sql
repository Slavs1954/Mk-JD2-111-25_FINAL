-- SCHEMA: finance_app

-- DROP SCHEMA IF EXISTS finance_app ;

CREATE SCHEMA IF NOT EXISTS finance_app
    AUTHORIZATION postgres;

-- Table: finance_app.users

-- DROP TABLE IF EXISTS finance_app.users;

CREATE TABLE IF NOT EXISTS finance_app.users
(
    uuid uuid NOT NULL,
    dt_create bigint NOT NULL,
    dt_update bigint NOT NULL,
    mail character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fio character varying(255) COLLATE pg_catalog."default",
    role character varying(50) COLLATE pg_catalog."default" NOT NULL,
    status character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (uuid),
    CONSTRAINT users_mail_key UNIQUE (mail)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS finance_app.users
    OWNER to postgres;