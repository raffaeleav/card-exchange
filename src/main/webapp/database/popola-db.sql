USE CardExchange;

insert into Utente values(default, 'Admin','admin123!','Francesco','Di Domenico','admin@email.it');
insert into Utente values(default, 'utente','utente123!','Francesco','Di Domenico','utente@email.it');
insert into Utente values(default, 'Admin','admin123!','Francesco','Di Domenico','admin@email.it');

insert into Carta values(default, 'Drago bianco occhi blu', 'Yu-Gi-Oh', 'Comune', '/imgs/cards/yugioh/yugioh.png');
insert into Carta values(default, 'Mago nero', 'Yu-Gi-Oh', 'Rara', '/imgs/cards/yugioh/yugioh.png');
insert into Carta values(default, 'Pikachu', 'Pokemon', 'Ultra rara', '/imgs/cards/pokemon/pokemon.png');
insert into Carta values(default, 'Maga nera', 'Yu-Gi-Oh', 'Comune', '/imgs/cards/yugioh/yugioh.png');
insert into Carta values(default, 'Loto nero', 'Magic', 'Comune', '/imgs/cards/yugioh/magic.png');

insert into Discussione values(default, "1", "Benvenuti sul forum!");
insert into Discussione values(default, "2", "Grazie di aver...");

insert into Messaggio values(default, "Benvenuti!", "Vi do il benvenuto sul forum di Card eXchange!", 1, 1);
insert into Messaggio values(default, "Card eXchange!", "Sentitevi liberi di contattare il nostro supporto per qualsiasi problema.", 1, 1);

insert into Ordine values(default,'2017-06-15','via roma 15',1,55.20);

INSERT INTO Offerta (condizione, prezzo, idUtente, idCarta) VALUES ('Nuova', 5, 1, 4);