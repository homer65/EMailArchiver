CREATE DATABASE email;
\c email
CREATE TABLE public.email (
    send bigint,
    received bigint,
    subject varchar(255),
    typ varchar(255),
    sfrom bigint,
    sto bigint,
    groesse integer
);
CREATE TABLE public.body (
    id bigint,
    wert bytea
);
CREATE TABLE public.sfrom (
    id bigint,
    adresse varchar(255)
);
CREATE TABLE public.sto (
    id bigint,
    adresse varchar(255)
);
CREATE TABLE public.tags (
    email_id bigint,
    tag varchar(255)
);
create user email with password 'email';
grant all on email to email;

