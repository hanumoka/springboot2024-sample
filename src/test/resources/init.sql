select 1;

-- Create account table
CREATE TABLE account
(
    id BIGSERIAL PRIMARY KEY,
    username     VARCHAR(255) NOT NULL,
    account_uuid VARCHAR(36)  NOT NULL,
    name         VARCHAR(255),
    age          INTEGER,
    gender       VARCHAR(50),
    status       VARCHAR(50)  NOT NULL,
    CONSTRAINT uk_account_username UNIQUE (username),
    CONSTRAINT uk_account_uuid UNIQUE (account_uuid)
);




