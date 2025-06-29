---
title: Page Load Times
tags:
  - Rechnernetze
  - Theorieteil
  - Semester-5
  - Informatik
date: 2025-06-02
---
| Begriff / Abkürzung         | Bedeutung                  | Erklärung                                                                                                                                                                                                 |
| --------------------------- | -------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **RTT**                     | *Round Trip Time*          | Die Zeit, die ein Paket vom Client zum Server braucht **plus** die Zeit für das Antwortpaket vom Server zurück zum Client. In dieser Aufgabe ist **jede Aktion pro Seite (Client/Server) eine RTT lang**. |
| **Tx**                      | *Transmit (Senden)*        | Gibt an, wie viele Segmente (Pakete) ein Gerät – entweder der **Client** (Browser) oder der **Server** – in einer bestimmten RTT gesendet hat.                                                            |
| **Client Tx**               | *Client Transmit*          | Was der Browser in dieser RTT an den Server sendet – z. B. ein HTTP-Request in mehreren Segmenten.                                                                                                        |
| **Server Tx**               | *Server Transmit*          | Was der Server als Antwort auf einen Request zurücksendet – z. B. HTML-Code oder ein Bild in TCP-Segmenten.                                                                                               |
| **cwnd**                    | *Congestion Window*        | Das **Sendefenster** des Servers, das die maximale Datenmenge in Bytes angibt, die er **ohne Bestätigung (ACK)** senden darf. Dieses Fenster wächst mit jedem erfolgreichen Datentransfer.                |
| **MSS**                     | *Maximum Segment Size*     | Die maximale Größe eines einzelnen TCP-Segments. In dieser Aufgabe ist **MSS = 800 Bytes**, d. h. jedes Segment ist max. 800 Bytes groß.                                                                  |
| **IW**                      | *Initial Window*           | Das Startwert von `cwnd`. Zu Beginn der Übertragung darf der Server **2 MSS = 1600 Bytes** gleichzeitig senden, also **2 Segmente à 800 Bytes**.                                                          |
| **HTTP Request / Response** | HTTP-Nachrichten           | Der Client stellt Anfragen an den Server (Requests), z. B. „Gib mir das HTML-Dokument“ oder „Gib mir Bild 5“. Der Server antwortet mit Daten (Response), die in mehrere TCP-Segmente aufgeteilt werden.   |
| **Segment**                 | TCP-Segment                | Ein Datenpaket auf der TCP-Ebene. Besteht aus Nutzdaten (max. MSS) + TCP-Header. Responses werden oft in viele Segmente aufgeteilt, wenn sie groß sind.                                                   |
| **Pipelining**              | HTTP-Technik               | (Nur bei Aufgabe 2): Ermöglicht das parallele Senden mehrerer HTTP-Requests **ohne auf Antwort zu warten**. In Aufgabe 1 ist das **deaktiviert**: jede Anfrage muss einzeln beantwortet werden.           |
| **Persistent HTTP**         | Verbindung bleibt bestehen | Die TCP-Verbindung bleibt für mehrere Requests bestehen, es wird **nicht** für jede Datei eine neue Verbindung aufgebaut (wie bei non-persistent HTTP).                                                   |
