-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    verified integer NOT NULL DEFAULT 0,
    social_type integer NOT NULL DEFAULT 0,
    last_login_date timestamp with time zone,
    disabled_by character varying COLLATE pg_catalog."default",
    deleted_by character varying COLLATE pg_catalog."default",
    disabled integer NOT NULL DEFAULT 0,
    first_name character varying COLLATE pg_catalog."default" NOT NULL,
    last_name character varying COLLATE pg_catalog."default" NOT NULL,
    time_zone character varying COLLATE pg_catalog."default" NOT NULL DEFAULT 'UTC'::character varying,
    date_created timestamp with time zone NOT NULL,
    date_updated timestamp with time zone NOT NULL,
    date_deleted timestamp with time zone,
                               is_deleted integer NOT NULL DEFAULT 0,
                               CONSTRAINT users_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

COMMENT ON COLUMN public.users.id
    IS 'uuid';

COMMENT ON COLUMN public.users.email
    IS 'The email address of user';

COMMENT ON COLUMN public.users.password
    IS 'Encrypted password';

COMMENT ON COLUMN public.users.verified
    IS 'The flag represend email verification. 1: verified, 0: other case.';

COMMENT ON COLUMN public.users.social_type
    IS '0: none socail user.
1: from google';

COMMENT ON COLUMN public.users.last_login_date
    IS 'The timestamp represent the last login date.';

COMMENT ON COLUMN public.users.disabled_by
    IS 'uuid of the user who disabled this user';

COMMENT ON COLUMN public.users.deleted_by
    IS 'uuid of the user who deleted this user';

COMMENT ON COLUMN public.users.disabled
    IS 'Indicates whether this user is disabled(1) or not(0).';

COMMENT ON COLUMN public.users.first_name
    IS 'The first name of this user';

COMMENT ON COLUMN public.users.last_name
    IS 'The last name of this user.';

COMMENT ON COLUMN public.users.time_zone
    IS 'The timezone of this user';

COMMENT ON COLUMN public.users.is_deleted
    IS 'Represent whether this user was deleted(1) or not(0)';