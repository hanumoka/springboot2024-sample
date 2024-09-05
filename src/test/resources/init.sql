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

-- Account 생성
INSERT INTO account (id, username, account_uuid, name, age, gender, status)
VALUES (1, 'john_doe@test.com', 'aaaaaaa-bbbbbbb', 'John Doe', 10, 'MALE', 'PENDING');



