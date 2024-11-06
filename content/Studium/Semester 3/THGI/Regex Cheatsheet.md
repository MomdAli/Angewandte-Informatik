---
title: Regex Cheatsheet
date: 2024-11-07
tags:
  - THGI
  - Semester-3
  - Informatik
---
## Basic Characters

- `.` - Matches any character except newline
- `\\d` - Matches any digit (0-9)
- `\\D` - Matches any non-digit
- `\\w` - Matches any word character (alphanumeric + underscore)
- `\\W` - Matches any non-word character
- `\\s` - Matches any whitespace (space, tab, newline)
- `\\S` - Matches any non-whitespace character

## Anchors

- `^` - Start of a string
- `$` - End of a string
- `\\b` - Word boundary
- `\\B` - Non-word boundary

## Quantifiers

- `*` - 0 or more occurrences
- `+` - 1 or more occurrences
- `?` - 0 or 1 occurrence (optional)
- `{n}` - Exactly n occurrences
- `{n,}` - n or more occurrences
- `{n,m}` - Between n and m occurrences

## Groups and Ranges

- `(abc)` - Matches exactly "abc"
- `[abc]` - Matches "a", "b", or "c"
- `[^abc]` - Matches any character except "a", "b", or "c"
- `[a-z]` - Matches any lowercase letter from a to z
- `[A-Z]` - Matches any uppercase letter from A to Z
- `[0-9]` - Matches any digit from 0 to 9
- `(a|b)` - Matches either "a" or "b" (OR operator)

## Escaping Special Characters

- `\\` - Escape character (e.g., `\\.` to match a period)
  
## Lookahead and Lookbehind

- `(?=...)` - Positive lookahead
- `(?!...)` - Negative lookahead
- `(?<=...)` - Positive lookbehind
- `(?<!...)` - Negative lookbehind

## Common Examples

- Email: `\\b[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}\\b`
- URL: `http[s]?://(?:[a-zA-Z]|[0-9]|[$-_@.&+]|[!*\\(\\),]|(?:%[0-9a-fA-F][0-9a-fA-F]))+`
- Phone Number (US): `\\(?\\d{3}\\)?[-\\s.]?\\d{3}[-\\s.]?\\d{4}`
