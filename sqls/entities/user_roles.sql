-- Table: public.user_roles

-- DROP TABLE IF EXISTS public.user_roles;

CREATE TABLE IF NOT EXISTS public.user_roles
(
    id integer NOT NULL DEFAULT nextval('user_roles_id_seq'::regclass),
    user_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    role_id integer NOT NULL,
    date_created timestamp with time zone NOT NULL,
    date_updated timestamp with time zone NOT NULL,
    date_deleted timestamp with time zone,
                               is_deleted integer NOT NULL DEFAULT 0,
                               CONSTRAINT user_roles_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_role_role FOREIGN KEY (role_id)
    REFERENCES public.roles (id) MATCH SIMPLE
                           ON UPDATE CASCADE
                           ON DELETE CASCADE,
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
                           ON UPDATE CASCADE
                           ON DELETE CASCADE
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_roles
    OWNER to postgres;

COMMENT ON COLUMN public.user_roles.id
    IS 'Primary key for user_roles table';

COMMENT ON COLUMN public.user_roles.user_id
    IS 'Foreign key referencing the users table (user id)';

COMMENT ON COLUMN public.user_roles.role_id
    IS 'Foreign key referencing the roles table (role id)';

COMMENT ON COLUMN public.user_roles.date_created
    IS 'Timestamp of creation';

COMMENT ON COLUMN public.user_roles.date_updated
    IS 'Timestamp of last update';

COMMENT ON COLUMN public.user_roles.date_deleted
    IS 'Timestamp of deletion';

COMMENT ON COLUMN public.user_roles.is_deleted
    IS 'Flag indicating if the record is deleted (1) or not (0)';