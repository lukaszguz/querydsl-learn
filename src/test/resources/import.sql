-- INSERT INTO domain_user (id, name) VALUES (1, 'John');
INSERT INTO domain_user (id, business_id, name) VALUES (1, RANDOM_UUID(), 'John');
INSERT INTO discount (id, business_id, percent) VALUES (2, RANDOM_UUID(), 20);
INSERT INTO discount (id, business_id, percent) VALUES (3, random_uuid(), 50);
INSERT INTO domain_user_discount (user_id, discount_id) VALUES (1, 2);
INSERT INTO domain_user_discount (user_id, discount_id) VALUES (1, 3);