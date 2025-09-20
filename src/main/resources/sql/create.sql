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

-- Table: finance_app.currency

-- DROP TABLE IF EXISTS finance_app.currency;

CREATE TABLE IF NOT EXISTS finance_app.currency
(
    uuid uuid NOT NULL,
    dt_create bigint NOT NULL,
    dt_update bigint NOT NULL,
    title character varying(50) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    CONSTRAINT currency_pkey PRIMARY KEY (uuid)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS finance_app.currency
    OWNER to postgres;

-- Table: finance_app.operation_category

-- DROP TABLE IF EXISTS finance_app.operation_category;

CREATE TABLE IF NOT EXISTS finance_app.operation_category
(
    uuid uuid NOT NULL,
    dt_create bigint NOT NULL,
    dt_update bigint NOT NULL,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT operation_category_pkey PRIMARY KEY (uuid)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS finance_app.operation_category
    OWNER to postgres;

-- Table: finance_app.accounts

-- DROP TABLE IF EXISTS finance_app.accounts;

CREATE TABLE IF NOT EXISTS finance_app.accounts
(
    uuid uuid NOT NULL,
    dt_create bigint NOT NULL,
    dt_update bigint NOT NULL,
    title text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    balance numeric(20,2) NOT NULL DEFAULT 0,
    type character varying(20) COLLATE pg_catalog."default" NOT NULL,
    currency uuid NOT NULL,
    user_uuid uuid NOT NULL,

    CONSTRAINT accounts_pkey PRIMARY KEY (uuid),
    CONSTRAINT accounts_type_check CHECK (type::text = ANY (ARRAY['CASH'::character varying, 'BANK_ACCOUNT'::character varying, 'BANK_DEPOSIT'::character varying]::text[])),
    CONSTRAINT accounts_user_fk FOREIGN KEY (user_uuid) REFERENCES finance_app.users (uuid) ON DELETE CASCADE
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS finance_app.accounts
    OWNER to postgres;

-- Table: finance_app.operations

-- DROP TABLE IF EXISTS finance_app.operations;

CREATE TABLE IF NOT EXISTS finance_app.operations
(
    uuid uuid NOT NULL,
    dt_create bigint NOT NULL,
    dt_update bigint NOT NULL,
    account_uuid uuid NOT NULL,
    date bigint NOT NULL,
    description text COLLATE pg_catalog."default",
    category uuid,
    value numeric(20,2) NOT NULL,
    currency uuid NOT NULL,
    CONSTRAINT operations_pkey PRIMARY KEY (uuid),
    CONSTRAINT operations_account_uuid_fkey FOREIGN KEY (account_uuid)
        REFERENCES finance_app.accounts (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS finance_app.operations
    OWNER to postgres;