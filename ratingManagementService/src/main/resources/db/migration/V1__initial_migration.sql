CREATE TABLE rating
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    service_id BIGINT NULL,
    rating DOUBLE NOT NULL,
    comment    VARCHAR(255) NULL,
    CONSTRAINT pk_rating PRIMARY KEY (id)
);