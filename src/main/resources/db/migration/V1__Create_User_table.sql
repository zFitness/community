create table USER
(
    ID           int auto_increment not null,
    NAME         VARCHAR(50),
    ACCOUNT_ID   VARCHAR(100),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);