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
CREATE UNIQUE INDEX email_i1
    ON public.email
    (
     send
    ); 
CREATE TABLE public.body (
    id bigint,
    wert bytea
);
CREATE UNIQUE INDEX body_i1
    ON public.body
    (
     id
    );
CREATE TABLE public.sfrom (
    id bigint,
    adresse varchar(255)
);
CREATE INDEX sfrom_i1
    ON public.sfrom
    (
     id
    );
CREATE TABLE public.sto (
    id bigint,
    adresse varchar(255)
);
CREATE INDEX sto_i1
    ON public.sto
    (
     id
    );
CREATE TABLE public.tags (
    email_id bigint,
    tag varchar(255)
);
CREATE INDEX tags_i1
    ON public.tags
    (
     tag
    );
CREATE INDEX tags_i2
    ON public.tags
    (
     email_id
    );
create user email with password 'email';
grant all on email to email;
grant all on body to email;
grant all on sfrom to email;
grant all on sto to email;
grant all on tags to email;

