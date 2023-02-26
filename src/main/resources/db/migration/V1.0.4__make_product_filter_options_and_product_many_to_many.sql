alter table product_filter_options drop column product_id;

create table product_filter_options_products
(
    id                 bigserial primary key,
    created_date       timestamp with time zone,
    last_modified_date timestamp with time zone,
    version            bigint,

    product_id bigint,
    product_filter_option_id bigint,

    foreign key (product_id) references products(id),
    foreign key (product_filter_option_id) references product_filter_options(id)
);
