DROP database IF EXISTS CardExchange;
CREATE database CardExchange;
USE CardExchange;

CREATE TABLE Utente(
    idUtente int not null auto_increment,
    username text not null,
    passwordhash text not null,
    nome text not null,
    cognome text not null,
    email text not null,

    PRIMARY KEY(idUtente)
);

CREATE TABLE Carta(
    idCarta int not null AUTO_INCREMENT,
    nome text not null,
    categoria text not null,
    rarità text not null,

    PRIMARY KEY(idCarta)
);

insert into Carta values(default,'Drago bianco occhi blu','Yu-Gi-Oh','Comune');
insert into Carta values(default,'Mago nero','Yu-Gi-Oh','Rara');
insert into Carta values(default,'Pikachu','Pokemon','Ultra rara');
insert into Carta values(default,'Maga nera','Yu-Gi-Oh','Comune');

CREATE TABLE Discussione(
    idDiscussione int not null auto_increment,
    idUtente int not null,
    titolo text not null,

    PRIMARY KEY(idDiscussione),
    FOREIGN KEY(idUtente) references Utente(idUtente)
);

CREATE TABLE Messaggio(
    idMessaggio int not null primary key auto_increment,
    oggetto text not null,
    corpo text not null,
    idUtente int not null,
    idDiscussione int not null,

    FOREIGN KEY(idUtente) references Utente(idUtente) on delete cascade on update cascade,
    FOREIGN KEY(idDiscussione) references Discussione(idDiscussione) on delete cascade on update cascade
);

CREATE TABLE Ordine(
    idOrdine int not null AUTO_INCREMENT,
    data date not null,
    indirizzo text not null,
    idUtente int not null,

    PRIMARY KEY(idOrdine),
    FOREIGN KEY(idUtente)  references Utente(idUtente) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Offerta (
    idOfferta int not null primary key auto_increment,
    condizione text not null,
    prezzo int not null,
    idUtente int not null,
    idCarta int not null,
    idOrdine int not null,

    FOREIGN KEY(idUtente) references Utente(idUtente) on delete cascade on update cascade,
    FOREIGN KEY(idCarta) references Carta(idCarta) on delete cascade on update cascade,
    FOREIGN KEY(idOrdine) references Ordine(idOrdine) on delete cascade on update cascade
);

CREATE TABLE Carrello(
    idCarrello int not null AUTO_INCREMENT,
    idUtente int not null,

    PRIMARY KEY(idCarrello),
    FOREIGN KEY(idUtente)  references Utente(idUtente) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE CarrelloContieneOfferta(
    idCarrello int not null references Carrello(idCarrello) ON UPDATE CASCADE ON DELETE CASCADE,
    idOfferta int not null references Offerta(idOfferta) ON UPDATE CASCADE ON DELETE CASCADE,

    PRIMARY KEY(idCarrello,idOfferta)
);

CREATE TABLE RichiestaDiScambio(
    idRichiestaScambio int not null auto_increment,
    idUtenteMittente int not null,
    idUtenteDestinatario int null,
    idOfferta int not null,

    PRIMARY KEY(idRichiestaScambio) ,
    FOREIGN KEY(idUtenteMittente) references Utente(idUtente),
    FOREIGN KEY(idUtenteDestinatario) references Utente(idUtente),
    FOREIGN KEY(idOfferta) references Offerta(idOfferta)
);

CREATE TABLE Recensione(
    idRecensione int not null auto_increment,
    valutazione int not null,
    testo text not null,
    idUtente int not null,
    idOrdine int not null,

    PRIMARY KEY(idRecensione),
    FOREIGN KEY(idUtente) references Utente(idUtente),
    FOREIGN KEY(idOrdine) references Ordine(idOrdine)
);

CREATE TABLE OrdineComprendeOfferta(
    idOrdine int not null,
    idOfferta int not null,

    PRIMARY KEY(idOrdine,idOfferta),
    FOREIGN KEY(idOrdine) references Ordine(idOrdine),
    FOREIGN KEY(idOfferta) references Offerta(idOfferta)
);