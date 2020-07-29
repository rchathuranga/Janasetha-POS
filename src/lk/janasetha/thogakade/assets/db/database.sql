create schema if not exists janasethav1 collate utf8_general_ci;

create table if not exists batch
(
    batch_id   int auto_increment
        primary key,
    supplier   varchar(50)    null,
    date       date           null,
    time       time           null,
    status     varchar(10)    not null,
    invoice_no text           null,
    bill_total decimal(12, 2) null
);

create table if not exists brand
(
    brand_id    int auto_increment
        primary key,
    description varchar(20)              not null,
    status      varchar(5) default 'ACT' not null,
    constraint brand_description_uindex
        unique (description)
);

create table if not exists category
(
    cate_id     int auto_increment
        primary key,
    description varchar(20)              not null,
    status      varchar(5) default 'ACT' null,
    constraint category_description_uindex
        unique (description)
);

create table if not exists item
(
    item_code        int auto_increment
        primary key,
    description      varchar(40)               not null,
    bill_description varchar(40) default ''    null,
    cate_id          int                       null,
    regular_price    decimal(12, 2)            null,
    measure_unit     varchar(5)                not null,
    status           varchar(5)  default 'ACT' not null,
    barcode          varchar(50)               null,
    constraint item_barcode_uindex
        unique (barcode),
    constraint item_category_cate_id_fk
        foreign key (cate_id) references category (cate_id),
    constraint FKjmj1p06xxk9x190taxpoblppd
        foreign key (cate_id) references category (cate_id)
);

create table if not exists batch_details
(
    bid_id          int auto_increment
        primary key,
    batch_id        int            null,
    item_code       int            null,
    qty             decimal(11, 3) not null,
    current_stock   decimal(11, 3) null,
    retail_price    decimal(12, 2) not null,
    mid_price       decimal(12, 2) null,
    wholesale_price decimal(12, 2) not null,
    manu_date       date           null,
    exp_date        date           null,
    buying_price    decimal(12, 2) null,
    constraint batch_details_batch_batch_id_fk
        foreign key (batch_id) references batch (batch_id),
    constraint FKljyp9v06le7d850gb3a1jw7tl
        foreign key (batch_id) references batch (batch_id),
    constraint batch_details_item_item_code_fk
        foreign key (item_code) references item (item_code)
);

create table if not exists `order`
(
    order_id         int auto_increment
        primary key,
    bill_no          varchar(20)          null,
    date             date                 not null,
    time             time                 null,
    payment_complete tinyint(1) default 0 not null,
    sales_type       varchar(15)          not null,
    total            decimal(12, 2)       not null
);

create table if not exists order_details
(
    id            int auto_increment
        primary key,
    order_id      int            null,
    batch_item_id int            null,
    qty           int            null,
    unit_price    decimal(12, 2) null,
    total         decimal(12, 2) null,
    constraint order_details_batch_item_details_batch_id_fk
        foreign key (batch_item_id) references batch_details (bid_id),
    constraint order_details_order_order_id_fk
        foreign key (order_id) references `order` (order_id)
            on update cascade on delete cascade
);

create table if not exists payment
(
    payment_id  int auto_increment
        primary key,
    date        date           not null,
    time        time           not null,
    amount      decimal(12, 2) not null,
    balance     decimal(12, 2) null,
    order_id    int            null,
    paid_amount decimal(12, 2) null,
    constraint payment_order_order_id_fk
        foreign key (order_id) references `order` (order_id)
);

create table if not exists supplier
(
    supplier_id             int auto_increment
        primary key,
    supplier                varchar(50)   null,
    description             varchar(100)  null,
    buying_price_percentage decimal(5, 2) null
);

create table if not exists wastage
(
    id               int auto_increment
        primary key,
    batch_details_id int null,
    qty              int null
);

