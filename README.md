# EMailArchiver
Eine einfache Möglichkeit einzelne EMails zu archivieren.
Von einem IMAP Server werden EMail abgerufen und können in einer
POSTGRES oder SQLite Datenbank gespeichert werden.
Hinweise zur Installation für SQLite:
(1) Java mindestens Version 11 sollte installiert sein
(2) git clone https://github.com/homer65/EMailArchiver,git
(3) EMailArchiver.ini.sample nach EMailArchiver.ini kopieren und anpassen
    Insbesondere: sqlite=true=
(4) Programm mit Start Skript ausführen
    EMailArchiver.sh für Linux
    EMailArchiver.ps1 für Windows
(5) Wenn Fehler auftreten oder Verbesserungsvorschläge da sind,
    EMail an christian@myoggradio.org
    
Hinweise zur Installation für Postgres:
(1) Java mindestens Version 11 sollte installiert sein
(2) git clone https://github.com/homer65/EMailArchiver,git
(3) EMailArchiver.ini.sample nach EMailArchiver.ini kopieren und anpassen
    Insbesondere: sqlite=false=
(4) email.ddl in Postgres ausführen. Vorher Passwort anpassen.
(5) Programm mit Start Skript ausführen
    EMailArchiver.sh für Linux
    EMailArchiver.ps1 für Windows
(6) Wenn Fehler auftreten oder Verbesserungsvorschläge da sind,
    EMail an christian@myoggradio.org

