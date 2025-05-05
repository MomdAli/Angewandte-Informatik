---
title: Socketbefehle und Pakete
tags:
  - Rechnernetze
  - Semester-5
  - Informatik
date: 2025-05-05
cssclasses:
  - wideFc
  - noMargin
  - leftAlign
---

```python title="Code Listing" {4,6,10-12,19,26}
import socket
socket.setdefaulttimeout(30)

My_IP = '127.0.0.1'
My_PORT = 50000
Remote_IP='127.0.0.2'
Remote_PORT=56835

def start_task(sock,message):
    sock.send(message.encode('utf-8'))
    msg=sock.recv(1024)
    sock.close()

def start_server():
    sock=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    sock.bind((My_IP, My_PORT))
    sock.listen(1)
    try:
        conn, addr = sock.accept()
        start_task(conn,"Thx for connecting!!!")
    except socket.timeout:
        pass

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    sock.connect((Remote_IP, Remote_PORT))
    start_task(sock,"Thx for accepting!!!");
except socket.error:
    start_server()
```

| Execution A                | Execution B                |
| -------------------------- | -------------------------- |
| `127.0.0.2` -> `127.0.0.1` | `127.0.0.1` -> `127.0.0.2` |
## TCP Flag Cheat Sheet

| Flag          | Stands for           | Meaning in simple terms                          | Typical trigger                                                                                       |
| ------------- | -------------------- | ------------------------------------------------ | ----------------------------------------------------------------------------------------------------- |
| **SYN**       | Synchronize          | “Hey, I want to start a connection.”             | `connect()` (client sends it)                                                                         |
| **ACK**       | Acknowledge          | “I got your message.”                            | Almost every response has this                                                                        |
| **SYN + ACK** | Sync & Ack           | “Okay, let's connect.”                           | `accept()` responds to `connect()`                                                                    |
| **PSH**       | Push                 | “Here’s some data - process it immediately.”     | `send()`                                                                                              |
| **PSH + ACK** | Push + Acknowledge   | “Here’s data AND I got your last message.”       | `send()` (usually with `recv()`<br>waiting on other side)                                             |
| **FIN**       | Finish               | “I’m done. I want to close the connection.”      | `close()`                                                                                             |
| **FIN + ACK** | Finish + Acknowledge | “I’m done too - I got your FIN.”                 | `close()` (on the other side)                                                                         |
| **RST**       | Reset                | “Something went wrong. Kill this connection.”    | Happens if one side tries to connect<br>but the other side isn’t listening                            |
| **RST + ACK** | Reset + Acknowledge  | “Hey, I got your message, but I’m not listening" | Usually when `connect()` is called, but the other<br>side hasn’t called `listen()` or `accept()` yet. |


| Nr. | Time  | Source    | Destination | Protocol | Length | Src Port | Dst Port | Info                                                    |
| --: | ----- | --------- | ----------- | :------: | -----: | -------: | -------: | :------------------------------------------------------ |
|   1 | 1,340 | 127.0.0.2 | 127.0.0.1   |   TCP    |     52 |    56835 |    50000 | 56835 > 50000 [SYN] Seq=0                               |
|   2 | 1,340 | 127.0.0.1 | 127.0.0.2   |   TCP    |     40 |    50000 |    56835 | 50000 > 56835 [RST, ACK] Seq=1 Ack=1                    |
|   3 | 1,842 | 127.0.0.2 | 127.0.0.1   |   TCP    |     52 |    56835 |    50000 | [TCP Spurious Retransmission] 56835 > 50000 [SYN] Seq=0 |
|   4 | 1,842 | 127.0.0.1 | 127.0.0.2   |   TCP    |     40 |    50000 |    56835 | 50000 > 56835 [RST, ACK] Seq=1 Ack=1                    |
|   5 | 2,342 | 127.0.0.2 | 127.0.0.1   |   TCP    |     48 |    56835 |    50000 | [TCP Spurious Retransmission] 56835 > 50000 [SYN] Seq=0 |
|   6 | 2,342 | 127.0.0.1 | 127.0.0.2   |   TCP    |     40 |    50000 |    56835 | 50000 > 56835 [RST, ACK] Seq=1 Ack=1                    |
|   7 | 3,646 | 127.0.0.1 | 127.0.0.2   |   TCP    |     52 |    56837 |    50000 | 56837 > 50000 [SYN] Seq=0                               |
|   8 | 3,646 | 127.0.0.2 | 127.0.0.1   |   TCP    |     52 |    50000 |    56837 | 50000 > 56837 [SYN, ACK] Seq=0 Ack=1                    |
|   9 | 3,646 | 127.0.0.1 | 127.0.0.2   |   TCP    |     40 |    56837 |    50000 | 56837 > 50000 [ACK] Seq=1 Ack=1                         |
|  10 | 3,646 | 127.0.0.1 | 127.0.0.2   |   TCP    |     60 |    56837 |    50000 | 56837 > 50000 [PSH, ACK] Seq=1 Ack=1 Len=20             |
|  11 | 3,646 | 127.0.0.2 | 127.0.0.1   |   TCP    |     40 |    50000 |    56837 | 50000 > 56837 [ACK] Seq=1 Ack=21 Len=0                  |
|  12 | 3,646 | 127.0.0.2 | 127.0.0.1   |   TCP    |     61 |    50000 |    56837 | 50000 > 56837 [PSH, ACK] Seq=1 Ack=21 Len=21            |
|  13 | 3,646 | 127.0.0.1 | 127.0.0.2   |   TCP    |     40 |    56837 |    50000 | 56837 > 50000 [ACK] Seq=21 Ack=22 Len=0                 |
|  14 | 3,646 | 127.0.0.1 | 127.0.0.2   |   TCP    |     40 |    56837 |    50000 | 56837 > 50000 [FIN, ACK] Seq=21 Ack=22 Len=0            |
|  15 | 3,646 | 127.0.0.2 | 127.0.0.1   |   TCP    |     40 |    50000 |    56837 | 50000 > 56837 [FIN, ACK] Seq=22 Ack=22 Len=0            |
|  16 | 3,646 | 127.0.0.1 | 127.0.0.2   |   TCP    |     40 |    56837 |    50000 | 56837 > 50000 [ACK] Seq=22 Ack=23 Len=0                 |


| Nr  | Prozess | Flag     | Ausgelöst durch | Grund für Abschluss     | Info                                                                    |
| --- | ------- | -------- | --------------- | ----------------------- | ----------------------------------------------------------------------- |
| 1   | A       | SYN      | A26             | -                       | A startet `connect()` und <br>sendet SYN an B                           |
| 2   | B       | RST, ACK | -               | A26 schlägt fehl        | B hat noch keinen Server laufen <br>→ OS antwortet automatisch mit RST  |
| 3   | A       | SYN      | A26             | -                       | Wiederholung von Paket 1 <br>(SYN wird erneut gesendet)                 |
| 4   | B       | RST, ACK | -               | A26 schlägt fehl        | B lehnt erneut ab <br>→ kein `listen()` aktiv                           |
| 5   | A       | SYN      | A26             | -                       | Letzter Verbindungsversuch <br>von A durch erneuten SYN                 |
| 6   | B       | RST, ACK | -               | A26 schlägt fehl        | Letzte automatische Ablehnung durch B                                   |
| 7   | B       | SYN      | B26             | -                       | B startet nun selbst `connect()` <br>und sendet SYN an A                |
| 8   | A       | SYN, ACK | A19             | B26 abgeschlossen       | A akzeptiert Verbindung über `accept()` <br>und antwortet mit SYN-ACK   |
| 9   | B       | ACK      | automatisch     | A19 abgeschlossen       | B bestätigt mit ACK → Verbindung <br>ist nun vollständig aufgebaut      |
| 10  | B       | PSH, ACK | B10             | A11 abgeschlossen       | B sendet Nachricht → A's `recv()` <br>empfängt diese und kehrt zurück   |
| 11  | A       | ACK      | automatisch     | -                       | A bestätigt den Erhalt der Nachricht von B                              |
| 12  | A       | PSH, ACK | A10             | B11 abgeschlossen       | A sendet Antwort → B's `recv()` <br>erhält Nachricht und kehrt zurück   |
| 13  | B       | ACK      | automatisch     | -                       | B bestätigt den Empfang der Nachricht von A                             |
| 14  | B       | FIN, ACK | B12             | A11 abgeschlossen (EOF) | B schließt Verbindung <br>→ A's `recv()` bemerkt FIN und kehrt zurück   |
| 15  | A       | FIN, ACK | A12             | B11 abgeschlossen (EOF) | A schließt ebenfalls <br>→ B's `recv()` bemerkt FIN und kehrt zurück    |
| 16  | B       | ACK      | automatisch     | -                       | B bestätigt den FIN von A <br>→ Verbindung wird vollständig geschlossen |
