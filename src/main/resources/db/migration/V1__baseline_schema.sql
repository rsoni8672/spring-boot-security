
CREATE TABLE IF NOT EXISTS public_user
(
    id serial primary key,
    created_date timestamp(6) without time zone,
    password character varying(255) NOT NULL,
    username character varying(255) UNIQUE NOT NULL
);


CREATE TABLE IF NOT EXISTS user_role
(
    id SERIAL primary key ,
    role character varying(255) NOT NULL,
    user_id bigint,
    FOREIGN KEY (user_id) REFERENCES public_user(id) on delete cascade
);