
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

create table products
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    category_id bigint,
    name varchar(256),
    measure_unit varchar(32),
    sku varchar(256),
    price numeric(19, 2),
    quantity numeric(19, 2),
    status varchar(32),
    is_active boolean,

    foreign key (category_id) references categories(id)
);

create unique index products_sku_idx on products(sku);
create index products_status_idx on products(status);
create index products_category_id_idx on products(category_id);

create table carts
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    user_id bigint,

    foreign key (user_id) references users(id)
);

create table cart_items
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    cart_id bigint,
    product_id bigint,

    quantity numeric(9, 3),
    price numeric (11, 9),

    foreign key (cart_id) references carts(id),
    foreign key (product_id) references products(id)
);

create table orders
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    user_id bigint,
    status varchar(32),
    details jsonb,

    foreign key (user_id) references users(id)
);

create table order_items
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    product_id bigint,
    order_id bigint,

    quantity numeric(9, 3),

    foreign key (product_id) references products(id),
    foreign key (order_id) references orders(id)
);

create table payments
(
    id bigserial primary key,
    created_date timestamp with time zone,
    last_modified_date timestamp with time zone,
    version bigint,

    user_id bigint,
    order_id bigint,

    status varchar(32),
    type varchar(32),

    amount numeric (11, 9),

    foreign key (user_id) references users(id),
    foreign key (order_id) references orders(id)
)


