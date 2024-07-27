-- Member 생성
INSERT INTO member (username, name)
VALUES ('john_doe', 'John Doe');

-- Board 생성 (2개)
INSERT INTO board (title, content, author_id)
VALUES ('First Board', 'This is the content of the first board.',
        (SELECT id FROM member WHERE username = 'john_doe'));

INSERT INTO board (title, content, author_id)
VALUES ('Second Board', 'This is the content of the second board.',
        (SELECT id FROM member WHERE username = 'john_doe'));

-- First Board에 대한 Comment 생성 (2개)
INSERT INTO comment (content, board_id, author_id)
VALUES ('First comment on first board',
        (SELECT id FROM board WHERE title = 'First Board'),
        (SELECT id FROM member WHERE username = 'john_doe'));

INSERT INTO comment (content, board_id, author_id)
VALUES ('Second comment on first board',
        (SELECT id FROM board WHERE title = 'First Board'),
        (SELECT id FROM member WHERE username = 'john_doe'));

-- Second Board에 대한 Comment 생성 (2개)
INSERT INTO comment (content, board_id, author_id)
VALUES ('First comment on second board',
        (SELECT id FROM board WHERE title = 'Second Board'),
        (SELECT id FROM member WHERE username = 'john_doe'));

INSERT INTO comment (content, board_id, author_id)
VALUES ('Second comment on second board',
        (SELECT id FROM board WHERE title = 'Second Board'),
        (SELECT id FROM member WHERE username = 'john_doe'));