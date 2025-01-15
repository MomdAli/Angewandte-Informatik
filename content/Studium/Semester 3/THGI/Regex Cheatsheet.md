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
- `\d` - Matches any digit (0-9)
- `\D` - Matches any non-digit
- `\w` - Matches any word character (alphanumeric + underscore)
- `\W` - Matches any non-word character
- `\s` - Matches any whitespace (space, tab, newline)
- `\S` - Matches any non-whitespace character
- `\t` - Matches a tab character
- `\n` - Matches a newline character
- `\r` - Matches a carriage return

## Anchors

- `^` - Start of a string
- `$` - End of a string
- `\b` - Word boundary (e.g., matches the boundary between a word and a space)
- `\B` - Non-word boundary (e.g., matches the position between two word characters)

## Quantifiers

- `*` - 0 or more occurrences (e.g., `a*` matches "", "a", "aa", etc.)
- `+` - 1 or more occurrences (e.g., `a+` matches "a", "aa", etc.)
- `?` - 0 or 1 occurrence (optional) (e.g., `a?` matches "" or "a")
- `{n}` - Exactly n occurrences (e.g., `a{3}` matches "aaa")
- `{n,}` - n or more occurrences (e.g., `a{2,}` matches "aa", "aaa", etc.)
- `{n,m}` - Between n and m occurrences (e.g., `a{2,4}` matches "aa", "aaa", or "aaaa")

## Groups and Ranges

- `(abc)` - Capturing group, matches exactly "abc"
- `(?:abc)` - Non-capturing group, matches "abc" without storing it for backreferences
- `[abc]` - Character set, matches "a", "b", or "c"
- `[^abc]` - Negated character set, matches any character except "a", "b", or "c"
- `[a-z]` - Character range, matches any lowercase letter from a to z
- `[A-Z]` - Matches any uppercase letter from A to Z
- `[0-9]` - Matches any digit from 0 to 9
- `[a-zA-Z0-9_]` - Matches any alphanumeric character or underscore
- `(a|b)` - Alternation, matches either "a" or "b" (OR operator)

## Special Characters

- `\\` - Escape character (e.g., `\.` to match a period)
- `\n` - Newline
- `\t` - Tab
- `\r` - Carriage return
- `\f` - Form feed
- `\v` - Vertical tab

## Lookahead and Lookbehind

- `(?=...)` - Positive lookahead, matches if `...` follows (e.g., `\d(?=abc)` matches a digit followed by "abc")
- `(?!...)` - Negative lookahead, matches if `...` does not follow (e.g., `\d(?!abc)` matches a digit not followed by "abc")
- `(?<=...)` - Positive lookbehind, matches if `...` precedes (e.g., `(?<=abc)\d` matches a digit preceded by "abc")
- `(?<!...)` - Negative lookbehind, matches if `...` does not precede (e.g., `(?<!abc)\d` matches a digit not preceded by "abc")

## Backreferences

- `\1`, `\2`, etc. - Matches the same text as the first, second, etc., capturing group (e.g., `(\w)\1` matches repeated word characters like "aa" or "bb")
- Named Capturing Groups: `(?<name>...)` - Assigns a name to a capturing group (e.g., `(?<digit>\d)`)
- Named Backreference: `\k<name>` - Refers to a named capturing group (e.g., `\k<digit>`)

## Useful Shorthands

- `.*` - Matches any character (except newline) 0 or more times
- `\w+` - Matches one or more word characters
- `\d{n}` - Matches exactly n digits
- `\s*` - Matches zero or more whitespace characters
- `[a-zA-Z]+` - Matches one or more alphabetic characters
- `[A-Za-z0-9_.+-]` - Matches characters commonly used in email addresses

## Common Regex Patterns

- **Email**: `\b[\w.%+-]+@[\w.-]+\.[A-Za-z]{2,6}\b`
- **URL**: `http[s]?://(?:[a-zA-Z]|[0-9]|[$-_@.&+]|[!*\(\),]|(?:%[0-9a-fA-F][0-9a-fA-F]))+`
- **Phone Number (US)**: `\(?\d{3}\)?[-\s.]?\d{3}[-\s.]?\d{4}`
- **Date (YYYY-MM-DD)**: `\b\d{4}-\d{2}-\d{2}\b`
- **IPv4 Address**: `\b(?:\d{1,3}\.){3}\d{1,3}\b`
- **Postal Code (US)**: `\b\d{5}(?:-\d{4})?\b`
- **Hex Color Code**: `#(?:[0-9a-fA-F]{3}){1,2}`
- **Username**: `[a-zA-Z0-9_]{3,16}` (matches usernames between 3 and 16 characters)

## Flags

- `i` - Case insensitive (e.g., `/abc/i` matches "ABC" as well as "abc")
- `g` - Global search (e.g., `/abc/g` finds all matches)
- `m` - Multi-line mode (`^` and `$` match start/end of lines)
- `s` - Dotall mode (`.` matches newline as well)
- `u` - Unicode mode (e.g., `/\u{1F600}/u` matches a Unicode emoji)

## Tips for Using Regex

- **Escape Special Characters**: Use `\\` to escape special characters like `.`, `*`, `+`, `?`, etc.
- **Capturing vs Non-Capturing Groups**: Use `(?:...)` for non-capturing groups if you don't need backreferences.
- **Testing Regex**: Use tools like [regex101](https://regex101.com/) or [RegExr](https://regexr.com/) to test and debug your expressions.
- **Use Comments for Complex Regex**: In some languages, you can use `x` flag to allow comments and whitespace for readability (e.g., `/ ( [A-Z] \w+ ) /x`)
- **Break Down Complex Patterns**: Break down your pattern into smaller parts to debug step by step.

## Regex Tools and Resources

- **Regex101**: [https://regex101.com/](https://regex101.com/) - Test your regex with detailed explanations
- **RegExr**: [https://regexr.com/](https://regexr.com/) - A great tool for learning, building, and testing regex
- **Cyrilex**: [https://extendsclass.com/regex-tester.html](https://extendsclass.com/regex-tester.html) - Test and visualize your regex
- **Regex Cheat Sheet**: [https://www.rexegg.com/regex-quickstart.html](https://www.rexegg.com/regex-quickstart.html) - A quick reference for regular expressions
