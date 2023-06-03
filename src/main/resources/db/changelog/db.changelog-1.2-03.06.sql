
-- changeset sergeev:1
ALTER TABLE user
ADD COLUMN balance DECIMAL;
-- rollback DROP TABLE `user` CASCADE;


