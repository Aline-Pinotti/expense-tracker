INSERT INTO tb_bank(id, name, number, color_label, created_at, updated_at) VALUES('2bf581e7-67a7-428c-a714-3cab1662a79a', 'Banco do Brasil', 1, '#fdb913', '2026-05-07T14:09:14.397374700Z', '2026-05-07T14:09:14.397374700Z');
INSERT INTO tb_bank(id, name, number, color_label, created_at, updated_at) VALUES('6e24d380-6d9e-47d1-8883-b158094c930f', 'Bradesco', 237, '#ff0000', '2026-05-07T14:09:14.397374700Z', '2026-05-07T14:09:14.397374700Z');
INSERT INTO tb_bank(id, name, number, color_label, created_at, updated_at) VALUES('3bef1f96-c5b9-4ce1-a8a3-6c0e2655cc8a', 'Caixa Econômica Federal', 104, '#0033a0', '2026-05-07T14:09:14.397374700Z', '2026-05-07T14:09:14.397374700Z');
INSERT INTO tb_bank(id, name, number, color_label, created_at, updated_at) VALUES('e6b06f71-f7b2-4eef-bd0b-3425a5e00cce', 'Itaú', 341, '#ff6600', '2026-05-07T14:09:14.397374700Z', '2026-05-07T14:09:14.397374700Z');
INSERT INTO tb_bank(id, name, number, color_label, created_at, updated_at) VALUES('b8bca059-1b53-4a4c-a586-f75180260bb2', 'Santander', 33, '#ff0000', '2026-05-07T14:09:14.397374700Z', '2026-05-07T14:09:14.397374700Z');

INSERT INTO tb_role(id, authority) VALUES('49740d77-7753-4bc4-9f75-e235cf56bd75', 'ROLE_USER');
INSERT INTO tb_role(id, authority) VALUES('9c09dcee-1215-4cee-8a38-069f80087570', 'ROLE_ADMIN');

INSERT INTO tb_user(id, username, email, password, created_at, updated_at) VALUES('9b2341d0-6637-404d-bef6-53043d04adcb', 'Mary Red', 'mary@example.com', '123456', '2026-05-07T14:09:14.397374700Z', '2026-05-07T14:09:14.397374700Z');
INSERT INTO tb_user_role(role_id, user_id) VALUES('49740d77-7753-4bc4-9f75-e235cf56bd75', '9b2341d0-6637-404d-bef6-53043d04adcb');

INSERT INTO tb_user(id, username, email, password, created_at, updated_at) VALUES('0f0a97c9-3bf5-4e6c-b90d-953f1b3ce713', 'Jhon Blue', 'jhon@example.com', '123456', '2026-05-07T14:09:14.397374700Z', '2026-05-07T14:09:14.397374700Z');
INSERT INTO tb_user_role(role_id, user_id) VALUES('49740d77-7753-4bc4-9f75-e235cf56bd75', '0f0a97c9-3bf5-4e6c-b90d-953f1b3ce713');

INSERT INTO tb_user(id, username, email, password, created_at, updated_at) VALUES('3f6dabe6-9776-4867-bf20-af3403d3bf2e', 'Ted Brown', 'ted@example.com', '123456', '2026-05-07T14:09:14.397374700Z', '2026-05-07T14:09:14.397374700Z');
INSERT INTO tb_user_role(role_id, user_id) VALUES('49740d77-7753-4bc4-9f75-e235cf56bd75', '3f6dabe6-9776-4867-bf20-af3403d3bf2e');
INSERT INTO tb_user_role(role_id, user_id) VALUES('9c09dcee-1215-4cee-8a38-069f80087570', '3f6dabe6-9776-4867-bf20-af3403d3bf2e');

INSERT INTO tb_account(id, name, agency_no, code, balance, account_limit, special_limit, limit_budget, cash, created_at, updated_at, user_id, bank_id) VALUES('520d77f2-f230-4bf2-91a4-ce649aa420d0', 'My Account', '0001', '123456', 5000.00, 2000.00, 500.00, 2000.00, 1000.00, '2026-05-07T14:09:14.407370900Z', '2026-05-07T14:09:14.407370900Z', '9b2341d0-6637-404d-bef6-53043d04adcb', '2bf581e7-67a7-428c-a714-3cab1662a79a');
INSERT INTO tb_account(id, name, agency_no, code, balance, account_limit, special_limit, limit_budget, cash, created_at, updated_at, user_id, bank_id) VALUES('91c603cc-4224-4552-8bbc-7c7778b4231d', 'My Account', '0001', '123456', 5000.00, 2000.00, 500.00, 2000.00, 1000.00, '2026-05-07T14:09:14.408370700Z', '2026-05-07T14:09:14.408370700Z', '9b2341d0-6637-404d-bef6-53043d04adcb', '6e24d380-6d9e-47d1-8883-b158094c930f');
INSERT INTO tb_account(id, name, agency_no, code, balance, account_limit, special_limit, limit_budget, cash, created_at, updated_at, user_id, bank_id) VALUES('30b0f411-b0c7-4d99-9ef7-88a0acc27a5a', 'My Account', '0001', '123456', 5000.00, 2000.00, 500.00, 2000.00, 1000.00, '2026-05-07T14:09:14.409370900Z', '2026-05-07T14:09:14.409370900Z', '0f0a97c9-3bf5-4e6c-b90d-953f1b3ce713', '3bef1f96-c5b9-4ce1-a8a3-6c0e2655cc8a');

INSERT INTO tb_credit_card(id, name, number, credit_card_limit, closing_day, due_day, expiration_date, is_international, created_at, updated_at, account_id) VALUES('17923f03-87cb-4012-beef-c8b9099fedec', 'Test Credit Card', 1234, 5000.00, 10, 20, 2030-12, true, '2026-05-07T14:09:14.410371100Z', '2026-05-07T14:09:14.410371100Z', '520d77f2-f230-4bf2-91a4-ce649aa420d0');
INSERT INTO tb_credit_card(id, name, number, credit_card_limit, closing_day, due_day, expiration_date, is_international, created_at, updated_at, account_id) VALUES('fa0e1c67-c3a6-4509-a0c1-7f07c45e30db', 'Test Credit Card', 1234, 5000.00, 10, 20, 2030-12, true, '2026-05-07T14:09:14.412371Z', '2026-05-07T14:09:14.412371Z', '91c603cc-4224-4552-8bbc-7c7778b4231d');
INSERT INTO tb_credit_card(id, name, number, credit_card_limit, closing_day, due_day, expiration_date, is_international, created_at, updated_at, account_id) VALUES('b19eeba3-de5f-4739-ba54-811df980c87f', 'Test Credit Card', 1234, 5000.00, 10, 20, 2030-12, true, '2026-05-07T14:09:14.412371Z', '2026-05-07T14:09:14.412371Z', '30b0f411-b0c7-4d99-9ef7-88a0acc27a5a');

-- backup created at 2026-05-07T14:09:14.412371Z --