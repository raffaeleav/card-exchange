USE CardExchange;
insert into Utente values(default, 'Admin','admin123!','Francesco','Di Domenico','admin@email.it');
insert into Utente values(default, 'utente','utente123!','Francesco','Di Domenico','utente@email.it');
insert into Utente values(default, 'Admin','admin123!','Francesco','Di Domenico','admin@email.it');
insert into Discussione values(default, "1", "Benvenuti sul forum!");
insert into Discussione values(default, "2", "Grazie di aver...");

insert into Messaggio values(default, "Benvenuti!", "Vi do il benvenuto sul forum di Card eXchange!", 1, 1);
insert into Messaggio values(default, "Card eXchange!", "Sentitevi liberi di contattare il nostro supporto per qualsiasi problema.", 1, 1);

insert into Ordine values(default,'2017-06-15','via roma 15',1,55.20);

INSERT INTO Offerta (condizione, prezzo, idUtente, idCarta) VALUES ('Nuova', 5, 1, 4);


INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Pessime","110","1","67");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Ottime","191","1","118");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Usata","193","2","1");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Pessime","123","3","112");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Buona","8","2","172");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Pessime","182","3","7");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Pessime","45","2","69");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Buona","189","2","15");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Buona","100","3","223");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Buona","181","3","50");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Pessime","182","1","272");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Usata","154","3","344");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Buona","185","2","314");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Buona","156","2","22");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Pessime","67","1","322");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Buona","174","2","335");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Ottime","81","1","6");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Pessime","1","1","266");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Buona","193","2","369");
INSERT INTO Offerta (condizione,prezzo,idUtente,idCarta) VALUES ("Pessime","143","3","372");

