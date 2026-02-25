# LeetCode Problem Solving

Practice **100–500 LeetCode DSA problems** organized by **problem type**, **Google level**, and **FAANG-style** interview patterns.

## Web UI (Browse Problems in Browser)

Run the web app and open **http://localhost:8080** in your browser:

```bash
./run.sh
```

**Features:** Search, filter by difficulty/type, dark mode, responsive cards, expandable problem details with examples.

**Expose to the internet** (share with others):

```bash
./run-with-ngrok.sh
```

Requires [ngrok](https://ngrok.com) (`brew install ngrok`). Alternative: `npx localtunnel --port 8080`.

**After adding new problems**, regenerate the data:

```bash
python3 scripts/extract_problems.py
```

## Quick Start (Run a Solution)

```bash
mvn compile
java -cp target/classes com.leetcode.easy.array.LC001_TwoSum_Easy_Array
```

## Naming Convention

```
LC{Number}_{ShortTitle}_{Level}_{ProblemType}
```

| Example | Package |
|---------|---------|
| `LC001_TwoSum_Easy_Array` | `com.leetcode.easy.array` |
| `LC015_ThreeSum_Medium_TwoPointers` | `com.leetcode.medium.twopointers` |
| `LC003_LongestSubstring_Medium_SlidingWindow` | `com.leetcode.medium.slidingwindow` |

**See [PROBLEMS_GUIDE.md](PROBLEMS_GUIDE.md)** for full naming rules, Google levels (L3/L4/L5), and FAANG problem types.

## Project Structure

```
com.leetcode/
├── easy/           # L3 (Easy) — array, string, hashmap, linkedlist, twopointers,
│                    #            slidingwindow, stack, tree, binarysearch,
│                    #            dynamicprogramming, backtracking, heap, graph, bitmanipulation
├── medium/         # L4 (Medium) — same types
├── hard/           # L5 (Hard) — array, tree, graph, dynamicprogramming
└── util/           # Utilities
```

## Current Problems

| Package | Classes | Level |
|---------|---------|-------|
| **Easy** | | |
| `easy.array` | `LC001_TwoSum`, `LC053_MaxSubarray` | L3 |
| `easy.string` | `LC344_ReverseString`, `ReverseAlphabeticalCharacters` | L3 |
| `easy.hashmap` | `LC242_ValidAnagram`, `LC202_HappyNumber` | L3 |
| `easy.linkedlist` | `LC206_ReverseLinkedList`, `LC021_MergeTwoSortedLists` | L3 |
| `easy.twopointers` | `LC125_ValidPalindrome`, `LC283_MoveZeroes` | L3 |
| `easy.stack` | `LC020_ValidParentheses`, `LC155_MinStack` | L3 |
| `easy.tree` | `LC104_MaxDepth`, `LC226_InvertBinaryTree` | L3 |
| `easy.binarysearch` | `LC704_BinarySearch`, `LC035_SearchInsertPosition` | L3 |
| `easy.dynamicprogramming` | `LC070_ClimbingStairs`, `LC121_BestTimeToBuyStock` | L3 |
| `easy.bitmanipulation` | `LC136_SingleNumber`, `LC191_NumberOfOneBits` | L3 |
| **Medium** | | |
| `medium.array` | `LC015_ThreeSum`, `LC238_ProductExceptSelf` | L4 |
| `medium.slidingwindow` | `FindMaxSubArray`, `LC003_LongestSubstring` | L4 |
| `medium.twopointers` | `LC011_ContainerWithMostWater`, `LC016_ThreeSumClosest` | L4 |
| `medium.tree` | `LC102_BinaryTreeLevelOrder`, `LC098_ValidateBST` | L4 |
| `medium.graph` | `LC200_NumberOfIslands`, `LC207_CourseSchedule` | L4 |
| `medium.dynamicprogramming` | `LC322_CoinChange`, `LC300_LongestIncreasingSubsequence` | L4 |
| `medium.backtracking` | `LC046_Permutations`, `LC039_CombinationSum` | L4 |
| `medium.binarysearch` | `LC033_SearchRotatedArray`, `LC034_FindFirstLastPosition` | L4 |
| `medium.heap` | `LC215_KthLargest`, `LC347_TopKFrequentElements` | L4 |
| **Hard** | | |
| `hard.array` | `LC042_TrappingRainWater`, `LC041_FirstMissingPositive` | L5 |
| `hard.dynamicprogramming` | `LC072_EditDistance`, `LC010_RegularExpressionMatching` | L5 |
| `hard.tree` | `LC124_BinaryTreeMaxPathSum`, `LC297_SerializeDeserializeBinaryTree` | L5 |
| `hard.graph` | `LC127_WordLadder`, `LC269_AlienDictionary` | L5 |

## Adding New Problems

1. Copy `_ProblemTemplate.java` to the right package
2. Rename: `LC{number}_{Title}_{Level}_{Type}`
3. Add solution + test cases
4. See [PROBLEMS_GUIDE.md](PROBLEMS_GUIDE.md) for problem types and template
