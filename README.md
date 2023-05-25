# EMailArchiver

Eine einfache Möglichkeit einzelne EMails zu archivieren.
Von einem IMAP Server werden EMail abgerufen und können in einer
POSTGRES oder SQLite Datenbank gespeichert werden.

Hinweise zur Installation für SQLite:

(1) Java mindestens Version 11 sollte installiert sein

(2) git clone https://github.com/homer65/EMailArchiver.git

(3) EMailArchiver.ini.sample nach EMailArchiver.ini kopieren und anpassen
    Insbesondere: sqlite=true=
    
(4) Programm mit Start Skript ausführen
    EMailArchiver.sh für Linux
    EMailArchiver.ps1 für Windows
    
(5) Wenn Fehler auftreten oder Verbesserungsvorschläge da sind,
    EMail an christian@myoggradio.org
    
Hinweise zur Installation für Postgres:

(1) Java mindestens Version 11 sollte installiert sein

(2) git clone https://github.com/homer65/EMailArchiver.git

(3) EMailArchiver.ini.sample nach EMailArchiver.ini kopieren und anpassen
    Insbesondere: sqlite=false=
    
(4) email.ddl in Postgres ausführen. Vorher Passwort anpassen.

(5) Programm mit Start Skript ausführen
    EMailArchiver.sh für Linux
    EMailArchiver.ps1 für Windows
    
(6) Wenn Fehler auftreten oder Verbesserungsvorschläge da sind,
    EMail an christian@myoggradio.org


Und für Alle: Regelmäßige Backups nicht vergessen.


Hinweise zur Konfiguration EMailArchiver.ini. Dort gibt es folgende Parameter:

mail_server - Die Adresse des IMAP Servers

mail_user - User mit dem man sich am IMAP Server anmelden kann

mail_passwort - Passwort obigen Users

mail_folder - Normalerweise INBOX, es sei denn der IMAP Server hat eine andere Ordnerstruktur

mail_programmm - Pfad zum Programm mit dem EMails angezeigt werden sollen

mail_temp - Ordner in dem EMail zwischengespeichert wird

mail_delete - Soll eine EMail nach dem Archivieren auf dem IMAP Server gelöscht werden (true oder false)

sqlite - Soll SQLite genutzt werden (true oder false). Wenn SQLite genutzt wird, dann kein Postgres

sqlite_url - Nur falls SQLite genutzt wird. Normalerweise sollte die URL nicht geändert werden, da eine SQLite Datenbank mitgeliefert ist

pg_url - Nur für Postgres. URL zum Postgres Server und zur Datenbank

pg_driver - Nur für Postgres, sollte normalerweise nicht geändert werden

pg_user - Nur für Postgres. User für die Postgres Datenbank. Default: email

pg_passwort - Nur für Postgres. Passwort obigen Users. Default: email

export_folder - Ordner in den EMail beim Export geschrieben werden

import_folder - Ordner aus dem EMail beim Import gelesen werden. Achtung: es werden nur *.eml  eingelesen und um *.tag ergänzt!

import_delete - Löscht die EMail nach einem Import Versuch aus dem Filesystem wenn true

protokol - Pfad zur Log Datei
