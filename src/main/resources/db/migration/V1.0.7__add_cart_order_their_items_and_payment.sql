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
