

CREATE TABLE refresh_tokens (
    id               VARCHAR(255) NOT NULL PRIMARY KEY,
    token            TEXT         NOT NULL,
    expiration_time  TIMESTAMP WITH TIME ZONE NOT NULL,
    user_id          VARCHAR(255) NOT NULL,

    CONSTRAINT fk_refresh_tokens_user
        FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE
);