CREATE DATABASE email;
\c email
CREATE TABLE public.email (
    send bigint,
    received bigint,
    subject varchar(255),
    typ varchar(255),
    from bigint,
    to bigint,
    groesse integer
);
CREATE TABLE public.body (
    id bigint,
    wert blob
);
CREATE TABLE public.from (
    id bigint,
    adresse varchar(255)
);
CREATE TABLE public.to (
    id bigint,
    adresse varchar(255)
);
create user email with password 'angelika';
grant all on email.* to email;

