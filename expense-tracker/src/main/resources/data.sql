INSERT INTO tb_bank(id, name, number, color_label, created_at, updated_at) VALUES('23ae9a4a-f37c-497c-a733-65f64d3389b9', 'Banco do Brasil', 1, '#fdb913', '2026-05-07T18:02:13.311217600Z', '2026-05-07T18:02:13.311217600Z');
INSERT INTO tb_bank(id, name, number, color_label, created_at, updated_at) VALUES('d94b9bf4-037f-493b-adca-b0903635bff1', 'Bradesco', 237, '#ff0000', '2026-05-07T18:02:13.311217600Z', '2026-05-07T18:02:13.311217600Z');
INSERT INTO tb_bank(id, name, number, color_label, created_at, updated_at) VALUES('b8698a9c-3e35-446d-bacf-e1b5fa3c3964', 'Caixa Econômica Federal', 104, '#0033a0', '2026-05-07T18:02:13.311217600Z', '2026-05-07T18:02:13.311217600Z');
INSERT INTO tb_bank(id, name, number, color_label, created_at, updated_at) VALUES('35ca7dd6-8218-4ea5-bc8e-fefcef6251fa', 'Itaú', 341, '#ff6600', '2026-05-07T18:02:13.311217600Z', '2026-05-07T18:02:13.311217600Z');
INSERT INTO tb_bank(id, name, number, color_label, created_at, updated_at) VALUES('19a2940f-ba52-4a6b-801a-d5359e32755e', 'Santander', 33, '#ff0000', '2026-05-07T18:02:13.311217600Z', '2026-05-07T18:02:13.311217600Z');

INSERT INTO tb_role(id, authority) VALUES('8647e7df-3ad7-4796-a383-ca0249c97706', 'ROLE_USER');
INSERT INTO tb_role(id, authority) VALUES('760d6d8e-b1a9-43a5-b919-ac35634fb2d4', 'ROLE_ADMIN');

INSERT INTO tb_user(id, username, email, password, created_at, updated_at) VALUES('a6bd771b-4c2c-47b4-8957-2ae8c1198053', 'Mary Red', 'mary@example.com', '123456', '2026-05-07T18:02:13.311217600Z', '2026-05-07T18:02:13.311217600Z');
INSERT INTO tb_user_role(role_id, user_id) VALUES('8647e7df-3ad7-4796-a383-ca0249c97706', 'a6bd771b-4c2c-47b4-8957-2ae8c1198053');

INSERT INTO tb_user(id, username, email, password, created_at, updated_at) VALUES('82b46e84-0e85-4d69-8909-e77469cfb85b', 'Jhon Blue', 'jhon@example.com', '123456', '2026-05-07T18:02:13.311217600Z', '2026-05-07T18:02:13.311217600Z');
INSERT INTO tb_user_role(role_id, user_id) VALUES('8647e7df-3ad7-4796-a383-ca0249c97706', '82b46e84-0e85-4d69-8909-e77469cfb85b');

INSERT INTO tb_user(id, username, email, password, created_at, updated_at) VALUES('42cd29e7-5b33-4f36-8cf6-2b17c23810e0', 'Ted Brown', 'ted@example.com', '123456', '2026-05-07T18:02:13.311217600Z', '2026-05-07T18:02:13.311217600Z');
INSERT INTO tb_user_role(role_id, user_id) VALUES('8647e7df-3ad7-4796-a383-ca0249c97706', '42cd29e7-5b33-4f36-8cf6-2b17c23810e0');
INSERT INTO tb_user_role(role_id, user_id) VALUES('760d6d8e-b1a9-43a5-b919-ac35634fb2d4', '42cd29e7-5b33-4f36-8cf6-2b17c23810e0');

INSERT INTO tb_account(id, name, agency_no, code, balance, account_limit, special_limit, limit_budget, cash, created_at, updated_at, user_id, bank_id) VALUES('7f09f630-ebf5-4fa2-8a05-5317ff77261a', 'My Account', '0001', '123456', 5000.00, 2000.00, 500.00, 2000.00, 1000.00, '2026-05-07T18:02:13.320348Z', '2026-05-07T18:02:13.320348Z', 'a6bd771b-4c2c-47b4-8957-2ae8c1198053', '23ae9a4a-f37c-497c-a733-65f64d3389b9');
INSERT INTO tb_account(id, name, agency_no, code, balance, account_limit, special_limit, limit_budget, cash, created_at, updated_at, user_id, bank_id) VALUES('6d3a1f06-803e-4206-a042-8186e0b621ca', 'My Account', '0001', '123456', 5000.00, 2000.00, 500.00, 2000.00, 1000.00, '2026-05-07T18:02:13.321347Z', '2026-05-07T18:02:13.321347Z', 'a6bd771b-4c2c-47b4-8957-2ae8c1198053', 'd94b9bf4-037f-493b-adca-b0903635bff1');
INSERT INTO tb_account(id, name, agency_no, code, balance, account_limit, special_limit, limit_budget, cash, created_at, updated_at, user_id, bank_id) VALUES('b1d783cd-adef-442b-ae2a-7d221c024aff', 'My Account', '0001', '123456', 5000.00, 2000.00, 500.00, 2000.00, 1000.00, '2026-05-07T18:02:13.321347Z', '2026-05-07T18:02:13.321347Z', '82b46e84-0e85-4d69-8909-e77469cfb85b', 'b8698a9c-3e35-446d-bacf-e1b5fa3c3964');

INSERT INTO tb_credit_card(id, name, number, credit_card_limit, closing_day, due_day, expiration_date, is_international, created_at, updated_at, account_id) VALUES('96347b3b-530c-4268-870c-fa21baa5eff9', 'Test Credit Card', 1234, 5000.00, 10, 20, 2030-12, true, '2026-05-07T18:02:13.322346800Z', '2026-05-07T18:02:13.322346800Z', '7f09f630-ebf5-4fa2-8a05-5317ff77261a');
INSERT INTO tb_credit_card(id, name, number, credit_card_limit, closing_day, due_day, expiration_date, is_international, created_at, updated_at, account_id) VALUES('fa4d78fd-d160-4b6d-85ff-d032ded3428a', 'Test Credit Card', 1234, 5000.00, 10, 20, 2030-12, true, '2026-05-07T18:02:13.323347700Z', '2026-05-07T18:02:13.323347700Z', '6d3a1f06-803e-4206-a042-8186e0b621ca');
INSERT INTO tb_credit_card(id, name, number, credit_card_limit, closing_day, due_day, expiration_date, is_international, created_at, updated_at, account_id) VALUES('fcdc0c5e-dd3c-4e1e-96be-ae3365bc9681', 'Test Credit Card', 1234, 5000.00, 10, 20, 2030-12, true, '2026-05-07T18:02:13.324346800Z', '2026-05-07T18:02:13.324346800Z', 'b1d783cd-adef-442b-ae2a-7d221c024aff');

-- backup created at 2026-05-07T18:02:13.324346800Z --