#!/usr/bin/env python3
"""
Extract LeetCode problem metadata from Java source files.
Outputs problems.json for the web UI.
"""
import json
import os
import re
from pathlib import Path

PROJECT_ROOT = Path(__file__).resolve().parent.parent
SRC_DIR = PROJECT_ROOT / "src" / "main" / "java" / "com" / "leetcode"
OUTPUT_FILE = PROJECT_ROOT / "src" / "main" / "resources" / "static" / "problems.json"


def strip_html(text: str) -> str:
    """Remove HTML tags and decode common entities."""
    text = re.sub(r'<[^>]+>', ' ', text)
    text = text.replace('{@code ', '').replace('}', '')
    text = text.replace('&lt;', '<').replace('&gt;', '>').replace('&amp;', '&')
    text = re.sub(r'\n\s*\*\s*', ' ', text)  # Remove Javadoc * at line start
    return ' '.join(text.split())


def extract_javadoc(content: str) -> dict | None:
    """Extract problem metadata from Javadoc comment."""
    match = re.search(r'/\*\*(.*?)\*/', content, re.DOTALL)
    if not match:
        return None

    doc = match.group(1)
    result = {
        "title": "",
        "number": "",
        "difficulty": "",
        "type": "",
        "tags": [],
        "statement": "",
        "constraints": "",
        "examples": "",
        "approach": "",
        "time": "",
        "space": "",
        "className": "",
        "packagePath": "",
    }

    # LC001: Two Sum
    title_match = re.search(r'LC(\d+):\s*(.+)', doc)
    if title_match:
        result["number"] = f"LC{title_match.group(1)}"
        result["title"] = title_match.group(2).strip()

    # Difficulty: Easy | Google: L3
    diff_match = re.search(r'Difficulty:\s*(\w+)\s*\|', doc)
    if diff_match:
        result["difficulty"] = diff_match.group(1)

    # Type: Array, Hash Map
    type_match = re.search(r'Type:\s*([^\n]+)', doc)
    if type_match:
        result["type"] = type_match.group(1).strip()

    # Tags: array, hashmap, faang
    tags_match = re.search(r'Tags:\s*([^\n]+)', doc)
    if tags_match:
        result["tags"] = [t.strip() for t in tags_match.group(1).split(',')]

    # Problem Statement (h2)
    stmt_match = re.search(r'<h2>Problem Statement</h2>\s*(.*?)(?=<h2>|$)', doc, re.DOTALL)
    if stmt_match:
        result["statement"] = strip_html(stmt_match.group(1)).strip()

    # Constraints
    const_match = re.search(r'<h2>Constraints</h2>\s*(.*?)(?=<h2>|$)', doc, re.DOTALL)
    if const_match:
        result["constraints"] = strip_html(const_match.group(1)).strip()

    # Examples - match <pre>...</pre> after Examples h2, or any pre block
    ex_match = re.search(r'<h2>Examples</h2>.*?<pre>\s*(.*?)\s*</pre>', doc, re.DOTALL)
    if ex_match:
        ex = re.sub(r'^\s*\*\s*', '', ex_match.group(1), flags=re.MULTILINE)
        result["examples"] = re.sub(r'\n\s*\*\s*', '\n', ex).strip()

    # Approach
    approach_match = re.search(r'<p>Approach:\s*([^<]+)</p>', doc)
    if approach_match:
        result["approach"] = approach_match.group(1).strip()

    # Time/Space
    time_match = re.search(r'<p>Time:\s*([^|]+)\s*\|\s*Space:\s*([^<]+)</p>', doc)
    if time_match:
        result["time"] = time_match.group(1).strip()
        result["space"] = time_match.group(2).strip()

    return result if result["title"] else None


def extract_java_code(content: str) -> str:
    """Extract Java class code (from package to end), excluding Javadoc."""
    # Remove Javadoc blocks
    code = re.sub(r'/\*\*.*?\*/', '', content, flags=re.DOTALL)
    # Remove single-line comments (but preserve // inside strings - simplified)
    code = re.sub(r'^\s*//.*$', '', code, flags=re.MULTILINE)
    # Normalize whitespace: collapse multiple blank lines to one
    code = re.sub(r'\n\s*\n\s*\n+', '\n\n', code)
    return code.strip()


def main():
    problems = []
    seen = set()

    for java_file in SRC_DIR.rglob("LC*.java"):
        rel_path = java_file.relative_to(SRC_DIR)
        package_path = str(rel_path.parent).replace(os.sep, ".")
        class_name = java_file.stem

        content = java_file.read_text(encoding="utf-8")
        meta = extract_javadoc(content)

        if meta and meta["number"] not in seen:
            meta["className"] = class_name
            meta["packagePath"] = f"com.leetcode.{package_path}" if package_path else "com.leetcode"
            meta["filePath"] = str(rel_path)
            meta["javaCode"] = extract_java_code(content)
            problems.append(meta)
            seen.add(meta["number"])

    problems.sort(key=lambda p: (p["number"],))

    OUTPUT_FILE.parent.mkdir(parents=True, exist_ok=True)
    OUTPUT_FILE.write_text(json.dumps(problems, indent=2), encoding="utf-8")
    print(f"Extracted {len(problems)} problems to {OUTPUT_FILE}")


if __name__ == "__main__":
    main()
