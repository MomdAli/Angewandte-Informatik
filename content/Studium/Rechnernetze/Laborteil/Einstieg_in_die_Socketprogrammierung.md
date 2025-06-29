---
title: Einstieg in die Socketprogrammierung
tags:
  - Rechnernetze
  - Laborteil
  - Semester-5
  - Informatik
date: 2025-05-12
---
## Rechenserver

Die Nachricht vom Client an den Server besteht aus:

| Teil        | Typ                           | Beschreibung                |
| ----------- | ----------------------------- | --------------------------- |
| `ID`        | Unsigned Int (4 Bytes)        | Einzigartige Aufgaben-ID    |
| `Operation` | UTF-8 kodiert (z. B. "SUM")   | "SUM", "PRO", "MIN", "MAX"  |
| `N`         | Unsigned Char (1 Byte)        | Anzahl der folgenden Zahlen |
| `z1...zN`   | Signed Ints (4 Bytes je Zahl) | Die eigentlichen Zahlen     |
Der Server antwortet mit:

| Teil       | Typ                    | Beschreibung                |
| ---------- | ---------------------- | --------------------------- |
| `ID`       | Unsigned Int (4 Bytes) | Die gleiche Aufgaben-ID     |
| `Ergebnis` | Signed Int (4 Bytes)   | Das Ergebnis der Berechnung |


| Format | Meaning                         | Bytes | Python Type                         |
| ------ | ------------------------------- | ----- | ----------------------------------- |
| `!I`   | Unsigned int (big-endian)       | 4     | For `ID`                            |
| `!i`   | Signed int (big-endian)         | 4     | For each number                     |
| `!B`   | Unsigned char (big-endian)      | 1     | For `N` (number of values)          |
| `!Ii`  | Combination: Unsigned int + int | 8     | For server response: `(ID, result)` |

```python title="TCP Client" {8-13}
import socket, struct

server_ip = '127.0.0.1'
server_port = 50000
operation = 'SUM'
zahlen = [1, 2, 3, 4, 5]

# Nachricht bauen
id = 1235
n = len(zahlen)
msg = struct.pack('!I', id) + operation.encode('utf-8') + struct.pack('!B', n)
for z in zahlen:
    msg += struct.pack('!i', z)

# TCP verbindung
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((server_ip, server_port))
sock.send(msg)

# Antwort empfangen
antwort = sock.recv(8)
antwort_id, ergebnis = struct.unpack('!Ii', antwort)
print(f"Antwort von Server: ID={antwort_id}, Ergebnis={ergebnis}")
```

```python title="TCP Server" {25-28}
import socket, struct

def berechne(op, zahlen):
    if op == 'SUM':
        return sum(zahlen)
    elif op == 'PRO':
        result = 1
        for z in zahlen:
            result *= z
        return result
    elif op == 'MIN':
        return min(zahlen)
    elif op == 'MAX':
        return max(zahlen)

server_ip = '127.0.0.1'
server_port = 50000

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.bind((server_ip, server_port))
sock.listen(1)
conn, addr = sock.accept()
data = conn.recv(1024)

id = struct.unpack('!I', data[:4])[0] # ID
op = data[4:7].decode('utf-8')  # SUM, PRO, ...
n = struct.unpack('!B', data[7:8])[0] # Anzahl der Zahlen
zahlen = [struct.unpack('!i', data[8 + i*4:12 + i*4])[0] for i in range(n)] # Zahlen in Format <z1><z2><z3>...

result = berechne(op, zahlen)
antwort = struct.pack('!Ii', id, result)
conn.send(antwort)
```

#### Für jedes gesendete Paket bestimmen, welcher Befehl in welchem Skript (Client/Server) dafür verantwortlich ist, dass das Paket gesendet wird

|Pakettyp|Befehl im Code|Datei|
|---|---|---|
|**Client → Server** (Daten senden)|`sock.send(msg)`|`echo_client_tcp.py`|
|**Server → Client** (Antwort senden)|`conn.send(antwort)`|dein Rechenserver|
|**TCP-Handshake (SYN/SYN-ACK/ACK)**|`sock.connect(...)` beim Client + `accept()` beim Server|beide|

#### für jeden blockierenden Befehl (connect, accept, recv) bestimmen, die Ankunft welches Pakets dafür verantwortlich ist, dass die Ausführung des Befehls vervollständigt wird

|Befehl|Beschreibung|Welches Paket löst "Fortsetzung" aus?|
|---|---|---|
|`sock.connect()` im Client|Baut Verbindung zum Server auf|Wird durch **SYN-ACK** vom Server beantwortet|
|`accept()` im Server|Wartet auf eingehende Verbindung|Wird durch **SYN** vom Client ausgelöst|
|`recv()` im Server|Wartet auf Nachricht vom Client|Wird durch Datenpaket vom Client ausgelöst|
|`recv()` im Client|Wartet auf Antwort vom Server|Wird durch Datenpaket vom Server ausgelöst|
