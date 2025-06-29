---
title: Peer to Peer Chat
tags:
  - Rechnernetze
  - Laborteil
  - Semester-5
  - Informatik
date: 2025-05-24
---
## **How PeerChat Works**

### 1. Server

The server is a simple TCP socket listener. It waits for connections from clients. When a client connects and registers (with nickname and UDP port), the server stores the information and shares it with all other clients. If a user disconnects, the server removes them and updates everyone.

The server also supports global broadcast: if a client sends a broadcast message, the server forwards it to all connected users using their existing TCP connections.

### 2. Client

Each client does the following:

* Connects to the server over **TCP**
* Sends its nickname and UDP port
* Opens a **UDP socket** to receive discovery messages from other peers
* Opens a **TCP socket** to receive P2P chat connections
* Can initiate or accept P2P chats directly over TCP

When user A wants to chat with user B, A sends a **UDP packet** to B containing A’s TCP port. Then, B uses this information to connect directly back to A using TCP. Once connected, they chat over this dedicated socket.

---

## **TCP vs UDP**

PeerChat uses both protocols based on their strengths:

| Feature            | TCP (used for server & chat) | UDP (used for discovery)     |
| ------------------ | ---------------------------- | ---------------------------- |
| Reliable           | Yes (resends, checks)        | No                           |
| Connection         | Required                     | No                           |
| Message Boundaries | Stream-based                 | Preserved                    |
| Use in PeerChat    | Chat and server coordination | Sending peer connection info |

TCP is used for all important communication (chat messages and server registration) because it’s reliable. UDP is used for a quick, connectionless "hey, connect to me" message before a chat session starts.

---

## **Message Framing**

Because TCP doesn’t preserve message boundaries (it’s just a stream of bytes), PeerChat adds its own framing. Each message is sent with a **10-character length prefix**, so the receiver knows how much data to read.

Example from the code:

```python
def send_message(sock, msg):
    msg = msg.encode()
    msg_len = f"{len(msg):<10}".encode()  # 10 bytes length prefix
    sock.sendall(msg_len + msg)

def recv_message(conn):
    raw_len = recv_all(conn, 10)
    msglen = int(raw_len.decode().strip())
    return recv_all(conn, msglen).decode()
```

This ensures that every message, no matter how big or small, is received completely and correctly.

---

## **Error Handling**

All socket operations are wrapped in `try/except` blocks to avoid crashes. For example, if a peer disconnects, or the server isn’t available, the program catches the error and informs the user instead of crashing.

Example:

```python
try:
    sock.connect((peer_ip, peer_port))
    send_message(sock, message)
except Exception as e:
    print(f"Failed to connect to peer: {e}")
```

The GUI and client also handle broken pipes, timeouts, and user exits gracefully, notifying other peers when a chat is closed.

---

## **How to Use the Program**

### 1. Start the Server

```bash
python -m server.main
```

### 2. Start the Client (GUI)

```bash
python -m client.main
```
   

3. **Use the GUI to**

   * View connected peers
   * Send broadcast messages
   * Start P2P chat (sends a UDP packet to the target)
   * Exchange messages over a direct TCP chat socket

All the logic is built on raw Python sockets, using reliable, low-level networking code.

### -->Check out [_PeerChat_](https://github.com/MomdAli/PeerChat)


