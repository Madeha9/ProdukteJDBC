# ProdukteJDBC – Produkt-Importer & Produkt-Lister 

(Java + JDBC + javaFx für GUI) mit Maven
 Die Klasse APP ist für JavaFx-GUI geeignet
 Die Klasse ProductImporter ist Main klasse für Produkte

 Ein kleines,  Java-Konsolenprojekt zum Importieren, Aktualisieren und Filtern von Produkten über JDBC.
 Unterstützt JSON-Import, Datenbankzugriff, CLI-Parameter, properties datei und Umgebungsvariablen.
---

 1-Projekt starten: mvn clean package   Das erzeugt die Datei:  target/ProdukteJDBC-1.0-SNAPSHOT.jar

2-Anwendung starten:-
Programmargument setzen - CLI
Beispiel
DB_URL=jdbc:postgresql://java-aufbau-db1.cts48ysrivsg.eu-central-1.rds.amazonaws.com:5432/postgresDB_USER=postgres
DB_PASSWORD=postgres
inputFile=products.json
mode=list

Mode: Produkte listen von DB
java -jar target/ProdukteJDBC-1.0-SNAPSHOT.jar \
  --mode=list \
  filter :-
  --only-active=true \
  --min-price= 20 usw
Mode: Produkte importieren  von Json datein zu DB
java -jar target/ProdukteJDBC-1.0-SNAPSHOT.jar \
  --mode=import \
  --input=products.json

⚙️ Konfiguration

Die App lädt Konfigurationen in folgender Reihenfolge:

CLI > Environment Variables > app.properties > Defaults

🔧 Environment Variables
export DB_URL=jdbc:postgresql://host:5432/postgres z.b
export DB_USER=postgres
export DB_PASSWORD=postgres
oder sehen  app.properties im resources

🧪 Beispielprodukt-Datei  sehen (products.json) im resources


🔍 CLI-Filter
Parameter	Bedeutung
--only-active=true	Zeigt nur aktive Produkte an
--min-price=50	Zeigt nur Produkte mit Preis ≥ 50
--input=products.json	Pfad zur JSON-Datei beim Import
--mode=list	Produkte anzeigen
--mode=import	Produkte importieren
🛠 Services
Service	Beschreibung
ImportService	Liest JSON ein, führt INSERT/UPSERT aus
ListService	Listet Produkte mit Filtern
ProductRepository	Reiner JDBC-Code (SQL + PreparedStatements)
ConfigurationManager	Regelt Priorität: CLI > Env > Properties
MakeDbConnection	Baut die DB-Verbindung sicher auf

⚠️ Bekannte Einschränkungen / Hinweise

JSON muss gültig sein und dem Product-Schema entsprechen
PostgreSQL muss erreichbar sein
Fehler beim Insert (z. B. falscher SQL-Syntax) werden im Terminal angezeigt
Bei fehlenden Credentials wird die Anwendung abgebrochen

