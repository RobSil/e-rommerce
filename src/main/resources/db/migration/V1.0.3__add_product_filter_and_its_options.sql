create table product_filters
(
    id                 bigserial primary key,
    created_date       timestamp with time zone,
    last_modified_date timestamp with time zone,
    version            bigint,

    title varchar(128),
    category_id bigint,

    foreign key (category_id) references categories(id)
);

create index product_filters_category_id_idx on product_filters(category_id);

create table product_filter_options
(
    id                 bigserial primary key,
    created_date       timestamp with time zone,
    last_modified_date timestamp with time zone,
    version            bigint,

    product_id bigint,
    product_filter_id bigint,

    foreign key (product_id) references products(id),
    foreign key (product_filter_id) references product_filters(id)
);

create index product_filter_options_product_filter_id_idx on product_filter_options(product_filter_id);
