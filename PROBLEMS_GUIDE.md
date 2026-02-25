# LeetCode 100–500: FAANG & Google-Style DSA Practice

Practice 100–500 LeetCode problems organized by **problem type**, **Google level**, and **FAANG-style** interview patterns.

---

## Naming Convention

```
LC{LeetCodeNumber}_{ShortTitle}_{Level}_{ProblemType}
```

| Component | Example | Description |
|-----------|---------|-------------|
| **LC Number** | LC001, LC015, LC206 | LeetCode problem number (1–3 digits) |
| **Short Title** | TwoSum, ThreeSum, MergeLists | CamelCase, no spaces |
| **Level** | Easy, Medium, Hard | Or L3/L4/L5 (Google) |
| **Problem Type** | Array, String, Tree | Primary DSA category |

### Examples

| LeetCode | Class Name | Package |
|----------|------------|---------|
| 1. Two Sum | `LC001_TwoSum_Easy_Array` | `com.leetcode.easy.array` |
| 15. 3Sum | `LC015_ThreeSum_Medium_TwoPointers` | `com.leetcode.medium.twopointers` |
| 206. Reverse Linked List | `LC206_ReverseLinkedList_Easy_LinkedList` | `com.leetcode.easy.linkedlist` |
| 3. Longest Substring | `LC003_LongestSubstring_Medium_SlidingWindow` | `com.leetcode.medium.slidingwindow` |

---

## Google Level Mapping

| Google Level | LeetCode Difficulty | Typical Role |
|--------------|---------------------|--------------|
| **L3** | Easy | New Grad, Entry |
| **L4** | Medium | Mid-Level, SDE II |
| **L5** | Hard | Senior, Staff |

---

## FAANG Problem Types

| Type | Package Suffix | Examples |
|------|----------------|----------|
| Array | `array` | Two Sum, Max Subarray, Product Except Self |
| String | `string` | Longest Substring, Valid Palindrome |
| Two Pointers | `twopointers` | 3Sum, Container With Most Water |
| Sliding Window | `slidingwindow` | Longest Substring, Min Window Substring |
| Binary Search | `binarysearch` | Search Rotated Array, Median of Two Arrays |
| Linked List | `linkedlist` | Reverse List, Merge Two Lists |
| Stack | `stack` | Valid Parentheses, Min Stack |
| Tree | `tree` | Max Depth, Invert Binary Tree |
| Graph | `graph` | Number of Islands, Course Schedule |
| Dynamic Programming | `dynamicprogramming` | Climbing Stairs, Coin Change |
| Backtracking | `backtracking` | Permutations, N-Queens |
| Heap | `heap` | Top K Elements, Merge K Lists |
| Hash Map | `hashmap` | Group Anagrams, Two Sum |
| Bit Manipulation | `bitmanipulation` | Single Number, Hamming Distance |
| Union Find | `unionfind` | Number of Islands, Redundant Connection |

---

## Package Structure

```
com.leetcode/
├── easy/
│   ├── array/
│   ├── string/
│   ├── hashmap/
│   ├── linkedlist/
│   ├── twopointers/
│   ├── slidingwindow/
│   ├── stack/
│   ├── tree/
│   ├── binarysearch/
│   ├── dynamicprogramming/
│   ├── backtracking/
│   ├── heap/
│   ├── graph/
│   └── bitmanipulation/
├── medium/
│   └── (same types)
├── hard/
│   └── (same types)
└── util/
```

---

## Problem Template

Each solution should include:

```java
/**
 * LC{number}: {Full Title}
 * Difficulty: {Easy|Medium|Hard} | Google: L{3|4|5}
 * Type: {Problem Type}
 * Tags: {tag1, tag2, tag3}
 * 
 * Problem: {1-2 sentence description}
 * Approach: {Brief approach}
 * Time: O(...) | Space: O(...)
 */
public class LC001_TwoSum_Easy_Array {
    public static void main(String[] args) {
        // Test cases
    }
}
```

---

## Target: 100–500 Problems

| Difficulty | Target Count | Focus |
|------------|--------------|-------|
| Easy | 50–100 | Basics, warm-ups |
| Medium | 150–300 | Core FAANG patterns |
| Hard | 50–100 | Senior-level depth |

**Suggested order:** Easy → Medium → Hard, grouped by problem type.
