ALTER TABLE domain_transaction ADD COLUMN transactionType VARCHAR ;
ALTER TABLE commission ADD COLUMN COMMISSIONTYPE VARCHAR ;



INSERT INTO domain_transaction (id, transactionType, amount) VALUES (1, 'FUNDING', 10.0);
INSERT INTO commission (id, commissionType, transaction_id, amount) VALUES (1, 'MY', 1, 5.0);
INSERT INTO commission (id, commissionType, transaction_id, amount) VALUES (2, 'PARTNER', 1, 1.0);

INSERT INTO domain_transaction (id, transactionType, amount) VALUES (2, 'PAYMENT', 20.0);


INSERT INTO domain_transaction (id, transactionType, amount) VALUES (3, 'PAYMENT', 30.0);
INSERT INTO commission (id, commissionType, transaction_id, amount) VALUES (3, 'MY', 3, 15.0);
