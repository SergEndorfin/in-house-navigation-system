create table base_station
(
    uuid                       varchar(36) not null,
    detection_radius_in_meters double      not null,
    name                       varchar(100),
    x                          double      not null,
    y                          double      not null,
    primary key (uuid)
);

create table mobile_station
(
    id          varchar(36) not null,
    last_knownx double      not null,
    last_knowny double      not null,
    primary key (id)
);

create table mobile_station_distance_report
(
    uuid              varchar(36) not null,
    mobile_station_id varchar(36) not null,
    distance          double      not null,
    timestamp         timestamp,
    primary key (uuid)
);

create table base_st__mobile_st_distance_report
(
    base_station_uuid                   varchar(36),
    mobile_station_distance_report_uuid varchar(36),
    foreign key (base_station_uuid) REFERENCES base_station (uuid),
    foreign key (mobile_station_distance_report_uuid) REFERENCES mobile_station_distance_report (uuid)
);

create table no_connection_ms_report
(
    uuid              varchar(36) not null,
    mobile_station_id varchar(36) not null,
    error_radius      double      not null,
    error_code        integer     not null,
    error_description varchar(150),
    primary key (uuid),
    foreign key (mobile_station_id) REFERENCES mobile_station (id)
);