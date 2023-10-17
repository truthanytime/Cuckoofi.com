-- Table: public.verification_token

-- DROP TABLE IF EXISTS public.verification_token;

CREATE TABLE IF NOT EXISTS public.verification_token
(
    id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    expiry_date timestamp with time zone NOT NULL,
    user_id character varying COLLATE pg_catalog."default" NOT NULL,
    date_created timestamp with time zone NOT NULL,
    date_updated timestamp with time zone NOT NULL,
    date_deleted timestamp with time zone,
                               is_deleted integer NOT NULL DEFAULT 0,
                               CONSTRAINT verification_token_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_token FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
                           ON UPDATE CASCADE
                           ON DELETE CASCADE
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.verification_token
    OWNER to postgres;

COMMENT ON COLUMN public.verification_token.id
    IS 'uuid';

COMMENT ON COLUMN public.verification_token.expiry_date
    IS 'The token expiration date and time';

COMMENT ON COLUMN public.verification_token.user_id
    IS 'Id of users table.';

COMMENT ON COLUMN public.verification_token.is_deleted
    IS 'Represent whether this user was deleted(1) or not(0)';