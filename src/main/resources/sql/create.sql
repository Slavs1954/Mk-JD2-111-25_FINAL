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

-- Table: finance_app.mail_verification

-- DROP TABLE IF EXISTS finance_app.mail_verification;

CREATE TABLE IF NOT EXISTS finance_app.mail_verification
(
    uuid uuid NOT NULL,
    user_id uuid NOT NULL,
    code character varying(100) NOT NULL,
    verified boolean NOT NULL DEFAULT false,
    email_count integer NOT NULL DEFAULT 1,
    dt_create bigint NOT NULL,
    dt_verified bigint NULL,
    CONSTRAINT mail_verification_pkey PRIMARY KEY (uuid),
    CONSTRAINT mail_verification_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES finance_app.users (uuid)
        ON DELETE CASCADE
)

    TABLESPACE pg_default;


ALTER TABLE IF EXISTS finance_app.mail_verification
    OWNER to postgres;