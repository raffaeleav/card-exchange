DROP database IF EXISTS CardExchange;
CREATE database CardExchange;
USE CardExchange;

create table Utente(
                       idUtente int not null auto_increment,
                       username text not null,
                       passwordhash text not null,
                       nome text not null,
                       cognome text not null,
                       email text not null,
                       primary key(idUtente)
);
insert into Utente values(default, 'Admin','admin123!','Francesco','Di Domenico','admin@email.it');

CREATE table Carta(
                      idCarta int not null AUTO_INCREMENT,
                      nome text not null,
                      categoria text not null,
                      rarità text not null,
                      immagine text not null,

                      PRIMARY KEY(idCarta)
);

/*insert into Carta values(default,'Drago bianco occhi blu','Yu-Gi-Oh','Comune');
insert into Carta values(default,'Mago nero','Yu-Gi-Oh','Rara');
insert into Carta values(default,'Pikachu','Pokemon','Ultra rara');
insert into Carta values(default,'Maga nera','Yu-Gi-Oh','Comune');*/

create table Discussione(
                            idDiscussione int not null auto_increment,
                            idUtente int not null,
                            titolo text not null,

                            primary key(idDiscussione),
                            foreign key (idUtente) references Utente(idUtente)
);

CREATE TABLE Messaggio (
                           idMessaggio int not null primary key auto_increment,
                           oggetto text not null,
                           corpo text not null,
                           idUtente int not null,
                           idDiscussione int not null,

                           foreign key(idUtente) references Utente(idUtente) on delete cascade on update cascade,
                           foreign key(idDiscussione) references Discussione(idDiscussione) on delete cascade on update cascade
);

CREATE table Ordine(
                       idOrdine int not null AUTO_INCREMENT,
                       dataset date not null,
                       indirizzo text not null,
                       idUtente int not null,
                       totale double not null,

                       PRIMARY KEY(idOrdine),
                       FOREIGN KEY (idUtente)  references Utente(idUtente) ON UPDATE CASCADE ON DELETE CASCADE
);
/*insert into Ordine values(default,'2017-06-15','via roma 15',1);*/

CREATE TABLE Offerta (
                         idOfferta int not null primary key auto_increment,
                         condizione text not null,
                         prezzo int not null,
                         idUtente int not null,
                         idCarta int not null,

                         foreign key(idUtente) references Utente(idUtente) on delete cascade on update cascade,
                         foreign key(idCarta) references Carta(idCarta) on delete cascade on update cascade
                         
);

CREATE table Carrello(
                         idCarrello int not null AUTO_INCREMENT,
                         idUtente int not null,

                         PRIMARY KEY(idCarrello),
                         FOREIGN KEY (idUtente)  references Utente(idUtente) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE table CarrelloContieneOfferta(
                                        idCarrello int not null references Carrello(idCarrello) ON UPDATE CASCADE ON DELETE CASCADE,
                                        idOfferta int not null references Offerta(idOfferta) ON UPDATE CASCADE ON DELETE CASCADE,

                                        PRIMARY KEY(idCarrello,idOfferta)
);

create table RichiestaDiScambio(
                                   idRichiestaScambio int not null auto_increment,
                                   idUtenteMittente int not null,
                                   idUtenteDestinatario int null,
                                   idOffertaMittente int not null,
                                   idOffertaDestinatario int not null,
                                   conguaglio double not null,

                                   primary key(idRichiestaScambio) ,
                                   foreign key (idUtenteMittente) references Utente(idUtente),
                                   foreign key (idUtenteDestinatario) references Utente(idUtente),
                                   foreign key (idOffertaMittente) references Offerta(idOfferta),
                                   foreign key (idOffertaDestinatario) references Offerta(idOfferta)
);

create table  Recensione(
                            idRecensione int not null auto_increment,
                            valutazione int not null,
                            testo text not null,
                            idUtente int not null,
                            idOrdine int not null,

                            primary key(idRecensione),
                            foreign key(idUtente) references Utente(idUtente),
                            foreign key (idOrdine) references Ordine(idOrdine)
);

create table OrdineComprendeOfferta(
                                       idOrdine int not null,
                                       idOfferta int not null,
                                       primary key(idOrdine,idOfferta),
                                       foreign key(idOrdine) references Ordine(idOrdine),
                                       foreign key(idOfferta) references Offerta(idOfferta)
);
