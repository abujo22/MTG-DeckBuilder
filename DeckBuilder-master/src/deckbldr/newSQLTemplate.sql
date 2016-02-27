DROP TABLE IF EXISTS cards;
CREATE TABLE cards (Multiverse_ID VARCHAR(200) NOT NULL, CMC INT, Cost VARCHAR(200), Text VARCHAR(1000), Image_URL VARCHAR(200), Card_Name VARCHAR(200), Power INT, Toughness INT, `Count` INT, PRIMARY KEY (Multiverse_ID));
DROP TABLE IF EXISTS Color;
CREATE TABLE Color (Multiverse_ID VARCHAR(200) NOT NULL, Color VARCHAR(200), PRIMARY KEY (Multiverse_ID));
DROP TABLE IF EXISTS Format;
CREATE TABLE Format (Multiverse_ID VARCHAR(200) NOT NULL, Format VARCHAR(200), PRIMARY KEY (Multiverse_ID));
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (Multiverse_ID VARCHAR(200) NOT NULL, `Type` VARCHAR(200), PRIMARY KEY (Multiverse_ID));
DROP TABLE IF EXISTS CardsInDeck;
CREATE TABLE CardsInDeck (Deck_ID VARCHAR(200), Multiverse_ID VARCHAR(200), `Count` INT, PRIMARY KEY(Deck_ID));
DROP TABLE IF EXISTS DeckInfo;
CREATE TABLE DeckInfo (Deck_ID VARCHAR(200), Deck_Name VARCHAR(200), Date_Created DATE, Total_Card_Limit INT, Type_Card_Limit INT, PRIMARY KEY (Deck_ID));
DROP TABLE IF EXISTS Edition;
CREATE TABLE Edition (Multiverse_ID VARCHAR(200) NOT NULL, st VARCHAR(200), Rarity VARCHAR(200), Artist VARCHAR(200), Flavor VARCHAR(1000), `Number` INT, Layout VARCHAR(200), PRIMARY KEY (Multiverse_ID));


INSERT INTO cards (Multiverse_ID, CMC, Cost, Text, Image_URL, Card_Name, Power, Toughness, Count)
VALUES ('382206', '0', '', 'Start the game with this conspiracy face up in the command zone. Your minimum deck size is reduced by five.', 'https://image.deckbrew.com/mtg/multiverseid/382206.jpg', 'Advantageous Proclamation', '0', '0', '1');
INSERT INTO Color (Multiverse_ID, Color)
VALUES ('382206', '');
INSERT INTO Format (Multiverse_ID, Format)
VALUES ('382206', '');
INSERT INTO `type`(Multiverse_ID, Type)
VALUES ('382206', 'conspiracy');
INSERT INTO Edition (Multiverse_ID, st, Rarity, Artist, Flavor, Number, Layout)
VALUES ('382206', 'Magic: The Gatheringâ€”Conspiracy', 'uncommon', 'Izzy', 'The beneficent council deems you worthy of favor. They hope this doesnt provoke envy from your peers.', '1','');

INSERT INTO cards (Multiverse_ID, CMC, Cost, Text, Image_URL, Card_Name, Power, Toughness, Count)
VALUES ('391846', '3', '{2}{R}', '', 'https://image.deckbrew.com/mtg/multiverseid/391846.jpg', 'Gore Swine', '4', '1', '1');
INSERT INTO Color (Multiverse_ID, Color)
VALUES ('391846', 'Red');
INSERT INTO Format (Multiverse_ID, Format)
VALUES ('391846', 'Legal');
INSERT INTO `type` (Multiverse_ID, Type)
VALUES ('391846', 'creature');
INSERT INTO Edition (Multiverse_ID, st, Rarity, Artist, Flavor, Number, Layout)
VALUES ('391846', 'Fate Reforged', 'Common', 'Jack Wang', 'The Mardu are like the gore swine. We are wild, hunt in packs, and rarely clean the blood from our blades', '103', 'Normal');

INSERT INTO CardsInDeck (Deck_ID, Multiverse_ID, `Count`)
VALUES("Deck 1", '391846', 1);
INSERT INTO DeckInfo (Deck_ID, Deck_Name, Date_Created, Total_Card_Limit, Type_Card_Limit)
VALUES("Deck 1", "Deck 1", "2015-12-10", "1", "1")
