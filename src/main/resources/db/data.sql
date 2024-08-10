-- Account 생성
INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe@test.com', 'aaaaaaa-bbbbbbb' ,'John Doe', 10, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe1@test.com', 'aaaaaaa-bbbbbbb1' ,'John Doe1', 11, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe2@test.com', 'aaaaaaa-bbbbbbb2' ,'John Doe2', 12, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe3@test.com', 'aaaaaaa-bbbbbbb3' ,'John Doe3', 13, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe4@test.com', 'aaaaaaa-bbbbbbb4' ,'John Doe4', 14, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe5@test.com', 'aaaaaaa-bbbbbbb5' ,'John Doe5', 15, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe6@test.com', 'aaaaaaa-bbbbbbb6' ,'John Doe6', 16, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe7@test.com', 'aaaaaaa-bbbbbbb7' ,'John Doe7', 17, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe8@test.com', 'aaaaaaa-bbbbbbb8' ,'John Doe8', 18, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe9@test.com', 'aaaaaaa-bbbbbbb9' ,'John Doe9', 19, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe10@test.com', 'aaaaaaa-bbbbbbb10' ,'John Doe10', 20, 'MALE', 'PENDING');

INSERT INTO account (username, account_uuid, name, age, gender, status)
VALUES ('john_doe11@test.com', 'aaaaaaa-bbbbbbb11' ,'John Doe11', 21, 'MALE', 'PENDING');

-- Board 생성 (2개)
INSERT INTO board (title, content, author_id)
VALUES ('First Board', 'This is the content of the first board.',
        (SELECT id FROM account WHERE username = 'john_doe'));

INSERT INTO board (title, content, author_id)
VALUES ('Second Board', 'This is the content of the second board.',
        (SELECT id FROM account WHERE username = 'john_doe'));

-- First Board에 대한 Comment 생성 (2개)
INSERT INTO comment (content, board_id, author_id)
VALUES ('First comment on first board',
        (SELECT id FROM board WHERE title = 'First Board'),
        (SELECT id FROM account WHERE username = 'john_doe'));

INSERT INTO comment (content, board_id, author_id)
VALUES ('Second comment on first board',
        (SELECT id FROM board WHERE title = 'First Board'),
        (SELECT id FROM account WHERE username = 'john_doe'));

-- Second Board에 대한 Comment 생성 (2개)
INSERT INTO comment (content, board_id, author_id)
VALUES ('First comment on second board',
        (SELECT id FROM board WHERE title = 'Second Board'),
        (SELECT id FROM account WHERE username = 'john_doe'));

INSERT INTO comment (content, board_id, author_id)
VALUES ('Second comment on second board',
        (SELECT id FROM board WHERE title = 'Second Board'),
        (SELECT id FROM account WHERE username = 'john_doe'));