create table comment (id bigint not null auto_increment, date_of_created DATETIME, text TEXT, user varchar(255), primary key (id));
create table contact_person (id bigint not null auto_increment, name_of_contact varchar(255), phone_of_contact varchar(255), primary key (id));
create table description (id bigint not null auto_increment, name varchar(255), primary key (id));
create table fn (id bigint not null auto_increment, date_start date, day_end date, days integer not null, primary key (id));
create table internet (id bigint not null auto_increment, name varchar(255), primary key (id));
create table lk (id bigint not null auto_increment, code_administrator varchar(255), code_employee varchar(255), contact varchar(255), link_address varchar(255), pass varchar(255), primary key (id));
create table lksite (id bigint not null auto_increment, name varchar(255), primary key (id));
create table maintenance (id bigint not null auto_increment, comment varchar(255), date_start date, day_end date, days integer not null, name varchar(255), primary key (id));
create table maintenance_trips (maintenance_id bigint not null, trips_id bigint not null, primary key (maintenance_id, trips_id));
create table maintenance_tariff (id bigint not null auto_increment, mount_trip integer not null, name varchar(255), primary key (id));
create table maintenance_tariff_trips (maintenance_tariff_id bigint not null, trips_id bigint not null, primary key (maintenance_tariff_id, trips_id));
create table ofd (id bigint not null auto_increment, contact varchar(255), date_start date, day_end date, days integer not null, operator varchar(255), pass varchar(255), primary key (id));
create table operator (id bigint not null auto_increment, name varchar(255), primary key (id));
create table organization (id bigint not null auto_increment, name varchar(255), primary key (id));
create table product (id bigint not null auto_increment, address varchar(255), condit varchar(255), egais varchar(255), excise varchar(255), name varchar(255), number varchar(255), taxation varchar(255), vat varchar(255), fn_id bigint, lk_id bigint, maintenance_id bigint, ofd_id bigint, sim_card_id bigint, user_id bigint, primary key (id));
create table product_comments (product_id bigint not null, comments_id bigint not null, primary key (product_id, comments_id));
create table product_product_mark (product_id bigint not null, product_mark varchar(255));
create table product_request (product_id bigint not null, request_id bigint not null, primary key (product_id, request_id));
create table product_tasks (product_id bigint not null, tasks_id bigint not null, primary key (product_id, tasks_id));
create table product_type_of_activities (product_id bigint not null, type_of_activities varchar(255));
create table product_equipment (id bigint not null auto_increment, name varchar(255), primary key (id));
create table product_mark (id bigint not null auto_increment, name varchar(255), primary key (id));
create table request (id bigint not null auto_increment, active bit not null, comment varchar(255), date_of_created DATETIME, date_of_end date, etsp varchar(255), product_condition TEXT, product_description TEXT, topic varchar(255), client_id bigint, product_id bigint, primary key (id));
create table request_comments (request_id bigint not null, comments_id bigint not null, primary key (request_id, comments_id));
create table request_contact_peoples (request_id bigint not null, contact_peoples_id bigint not null, primary key (request_id, contact_peoples_id));
create table request_product_equipments (request_id bigint not null, product_equipments varchar(255));
create table requisite (id bigint not null auto_increment, inn varchar(255), kpp varchar(255), ogrn varchar(255), organization_id bigint, primary key (id));
create table sim_card (id bigint not null auto_increment, name varchar(255), number_card varchar(255), personal_account varchar(255), primary key (id));
create table store (id bigint not null auto_increment, description varchar(255), link varchar(255), login varchar(255), name varchar(255), password varchar(255), primary key (id));
create table tariff (id bigint not null auto_increment, days integer not null, primary key (id));
create table task (id bigint not null auto_increment, active bit not null, comment varchar(255), date_of_created DATETIME, date_of_end date, date_of_reminder date, description varchar(255), name varchar(255), product_id bigint, user_id bigint, primary key (id));
create table task_comments (task_id bigint not null, comments_id bigint not null, primary key (task_id, comments_id));
create table taxation (id bigint not null auto_increment, name varchar(255), primary key (id));
create table topic (id bigint not null auto_increment, name varchar(255), primary key (id));
create table trip (id bigint not null auto_increment, date_trip date, name integer not null, primary key (id));
create table type_of_activity (id bigint not null auto_increment, name varchar(255), primary key (id));
create table user (id bigint not null auto_increment, active bit not null, comment TEXT, email varchar(255), inn varchar(255), kpp varchar(255), last_name varchar(255), name varchar(255), name_of_organization varchar(255), ogrn varchar(255), password varchar(255), patronymic varchar(255), phone_number varchar(255), status varchar(255), type_of_organization varchar(255), primary key (id));
create table user_role (user_id bigint not null, roles varchar(255));
create table vat (id bigint not null auto_increment, name varchar(255), primary key (id));
alter table maintenance_trips add constraint UK_e5g8gu267mb7tbvpwr8epm346 unique (trips_id);
alter table maintenance_tariff_trips add constraint UK_lny27q2h8idfcgy3lg3e5xpmn unique (trips_id);
alter table product_comments add constraint UK_qh0mhsbku34k789m85831cytw unique (comments_id);
alter table product_request add constraint UK_l6sypw9pu63ti5qv3l15stkro unique (request_id);
alter table product_tasks add constraint UK_8tjsiu3fai55m494c4ycff7vu unique (tasks_id);
alter table request_comments add constraint UK_ijf5rhflj60ps381hcomnhxns unique (comments_id);
alter table request_contact_peoples add constraint UK_f83vhkumhqr5ojgca25xyyn81 unique (contact_peoples_id);
alter table task_comments add constraint UK_2ai2rh4v34oftvibvlpfnc74b unique (comments_id);
alter table maintenance_trips add constraint FK3f3pvm4od9c74vwjor0f79hpp foreign key (trips_id) references trip (id);
alter table maintenance_trips add constraint FK7jkvsl184r9g7nmoe02098tr3 foreign key (maintenance_id) references maintenance (id);
alter table maintenance_tariff_trips add constraint FKesd092d0bj28osmlbpssmgat3 foreign key (trips_id) references trip (id);
alter table maintenance_tariff_trips add constraint FKgcqawchw54b9635ollortissq foreign key (maintenance_tariff_id) references maintenance_tariff (id);
alter table product add constraint FKplkaxgyevvjrfwr2hjn0g247h foreign key (fn_id) references fn (id);
alter table product add constraint FKs2nyiweev1txcvh12tbo9218y foreign key (lk_id) references lk (id);
alter table product add constraint FKqeoa10jgqi9wvugssaqi8wmcs foreign key (maintenance_id) references maintenance (id);
alter table product add constraint FKsxebnj67msed3buksd8t2ykqn foreign key (ofd_id) references ofd (id);
alter table product add constraint FKcyneq78fo3dtltl9b4ubod892 foreign key (sim_card_id) references sim_card (id);
alter table product add constraint FK979liw4xk18ncpl87u4tygx2u foreign key (user_id) references user (id);
alter table product_comments add constraint FK1fsfcet5qipm1kt7kralsw2vx foreign key (comments_id) references comment (id);
alter table product_comments add constraint FKh4dk46lpqxf9ctll1py5qpuw7 foreign key (product_id) references product (id);
alter table product_product_mark add constraint FKs6b4g5y24hwakjmg3uknevh5i foreign key (product_id) references product (id);
alter table product_request add constraint FKr99c1d8sqp8v52mkcgy8ndylv foreign key (request_id) references request (id);
alter table product_request add constraint FK1k1mqwqffwotki2qq2s76qf50 foreign key (product_id) references product (id);
alter table product_tasks add constraint FK2rgw5oqojkrysl25xxlud9koc foreign key (tasks_id) references task (id);
alter table product_tasks add constraint FKdu2lwmeh2m84357rjqu9tddpp foreign key (product_id) references product (id);
alter table product_type_of_activities add constraint FK7nk0p8r16vvwfnfogbqorhnuf foreign key (product_id) references product (id);
alter table request add constraint FKex2mlbjmdxtuwbu7tacnehsq0 foreign key (client_id) references user (id);
alter table request add constraint FKbywsb4tohr9bviei9hqvp7r4i foreign key (product_id) references product (id);
alter table request_comments add constraint FKfsyl5kl878tqo9faorfksv0ct foreign key (comments_id) references comment (id);
alter table request_comments add constraint FK9bytn708qhr8ybpguwhybwj0q foreign key (request_id) references request (id);
alter table request_contact_peoples add constraint FKkngh2hxf487re2nfp9cqupxnd foreign key (contact_peoples_id) references contact_person (id);
alter table request_contact_peoples add constraint FKlye3pd4mo5owkegq451sylgvo foreign key (request_id) references request (id);
alter table request_product_equipments add constraint FKq57ky3hw1o2w3hnobqmvqibn foreign key (request_id) references request (id);
alter table requisite add constraint FKr2n1dx4o6cydn31exu84v0btd foreign key (organization_id) references organization (id);
alter table task add constraint FKnx6gbgoxm5v9n0a15n7vetp0f foreign key (product_id) references product (id);
alter table task add constraint FK2hsytmxysatfvt0p1992cw449 foreign key (user_id) references user (id);
alter table task_comments add constraint FK7sybm6byg0d319yp5b0xkvn9b foreign key (comments_id) references comment (id);
alter table task_comments add constraint FK57giy29i5nak139pefvyvhj9h foreign key (task_id) references task (id);
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);