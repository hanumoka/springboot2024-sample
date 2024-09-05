-- 기존 데이터 삭제
TRUNCATE TABLE account CASCADE;

-- Account 생성
INSERT INTO account (id, username, account_uuid, name, age, gender, status)
VALUES (1, 'john_doe@test.com', 'aaaaaaa-bbbbbbb', 'John Doe', 10, 'MALE', 'PENDING');
