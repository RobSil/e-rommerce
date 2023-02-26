
create table users
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    first_name varchar(64) not null,
    last_name varchar(64) not null,
    date_of_birth timestamp with time zone,
    gender varchar(32) not null,
    email varchar(255) not null,
    email_confirmed boolean,
    password varchar(255),
    is_enabled boolean,
    roles jsonb
);

create unique index users_email_idx on users(email);

create table categories
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    parent_id bigint,
    title varchar(64),

    foreign key (parent_id) references categories(id)
);

create index categories_parent_id_idx on categories(parent_id);

create table groups
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    title varchar(64),
    name varchar(64)
);

create table products
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    category_id bigint,
--     group_id bigint,
    sku varchar(256),
    price numeric(19, 2),
    quantity integer,
    status varchar(32),
    is_active boolean,

    foreign key (category_id) references categories(id)
);

create unique index products_sku_idx on products(sku);
create index products_status_idx on products(status);
create index products_category_id_idx on products(category_id);

create table group_options
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    group_id bigint,
    product_id bigint,
    value varchar(64),
    is_active boolean,
    status varchar(32),

    foreign key (group_id) references groups(id),
    foreign key (product_id) references products(id)
);

create index group_options_group_id_idx on group_options(group_id);
create index group_options_product_id_idx on group_options(product_id);
create index group_options_is_active_idx on group_options(is_active);

