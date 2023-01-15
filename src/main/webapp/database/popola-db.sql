USE CardExchange;

insert into Utente values(default, 'Admin','admin123!','Francesco','Di Domenico','admin@email.it');
insert into Utente values(default, 'utente','utente123!','Francesco','Di Domenico','utente@email.it');
insert into Utente values(default, 'Admin','admin123!','Francesco','Di Domenico','admin@email.it');

insert into Discussione values(default, "1", "Benvenuti sul forum!");
insert into Discussione values(default, "2", "Grazie di aver...");

insert into Messaggio values(default, "Benvenuti!", "Vi do il benvenuto sul forum di Card eXchange!", 1, 1);
insert into Messaggio values(default, "Card eXchange!", "Sentitevi liberi di contattare il nostro supporto per qualsiasi problema.", 1, 1);

insert into Ordine values(default,'2017-06-15','via roma 15',1,55.20);