drop table product_filter_options_products cascade;
drop table product_filter_options cascade;
drop table product_filters;

alter table products add column properties jsonb;
