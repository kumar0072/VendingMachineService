TRUNCATE TABLE vending_machine RESTART IDENTITY;
INSERT INTO vending_machine (id,capacity, quantity, location,machine_name,model,state) VALUES (1,30, 30, 'Kanpur','candyman','sky123','No_Coin');
INSERT INTO vending_machine (id,capacity, quantity, location,machine_name,model,state) VALUES (2,30, 30, 'Kanpur','candymanboom','sky123','Contains_Coin');
INSERT INTO vending_machine (id,capacity, quantity, location,machine_name,model,state) VALUES (3,30, 30, 'Kanpur','candymanboomToom','sky123','Candy_Dispensed');
INSERT INTO vending_machine (id,capacity, quantity, location,machine_name,model,state) VALUES (4,30, 30, 'Kanpur','candyboomToom','sky123','No_Coin');

