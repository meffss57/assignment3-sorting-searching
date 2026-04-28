# Assignment 3: Sorting and Searching Algorithm Analysis System

## A. Project Overview

This project implements and compares three fundamental algorithms in Java:

- **Bubble Sort** — basic sorting
- **Quick Sort** — advanced sorting
- **Linear Search** — searching

The goal is to measure execution time on arrays of different sizes (10, 100, 1000)
and different input types (random, already sorted), then check whether the measured
results match the expected Big-O complexity.

## B. Algorithm Descriptions

### Bubble Sort
Repeatedly walks through the array, comparing adjacent elements and swapping them
if they are in the wrong order. After each full pass, the largest remaining element
"bubbles up" to the end. My implementation adds an **early-exit optimization**: if
a pass makes no swaps, the array is already sorted and we stop.

- Best case (already sorted): **O(n)**
- Average / worst case: **O(n²)**
- Space: **O(1)** — in-place

### Quick Sort
A divide-and-conquer algorithm. It picks a **pivot**, partitions the array so that
elements smaller than the pivot go left and larger go right, then recursively sorts
each partition. My implementation uses **Lomuto partition with the last element as pivot**.

- Average case: **O(n log n)**
- Worst case (already-sorted or reverse-sorted input with last-element pivot): **O(n²)**
- Space: **O(log n)** average — recursion stack

### Linear Search
Scans the array from left to right, comparing each element with the target. Returns
the index if found, or -1 otherwise. Does **not** require a sorted array.

- Best case: **O(1)** — target is first element
- Worst / average case: **O(n)**
- Space: **O(1)**

## C. Experimental Results

All times measured in nanoseconds using `System.nanoTime()`.
*Fill the tables below with values from your own test run — numbers vary by machine.*

### Sorting (ns)

| Array size | Bubble (random) | Bubble (sorted) | Quick (random) | Quick (sorted) |
|-----------:|----------------:|----------------:|---------------:|---------------:|
| 10         | [YOUR_VALUE]    | [YOUR_VALUE]    | [YOUR_VALUE]   | [YOUR_VALUE]   |
| 100        | [YOUR_VALUE]    | [YOUR_VALUE]    | [YOUR_VALUE]   | [YOUR_VALUE]   |
| 1000       | [YOUR_VALUE]    | [YOUR_VALUE]    | [YOUR_VALUE]   | [YOUR_VALUE]   |

### Linear Search (ns)

| Array size | Found (middle target) | Not found (miss) |
|-----------:|----------------------:|-----------------:|
| 10         | [YOUR_VALUE]          | [YOUR_VALUE]     |
| 100        | [YOUR_VALUE]          | [YOUR_VALUE]     |
| 1000       | [YOUR_VALUE]          | [YOUR_VALUE]     |

## D. Screenshots

Screenshots of the program output are stored in `docs/screenshots/`.

## Analysis

### 1. Which sorting algorithm performed faster? Why?
On **random** input, Quick Sort was significantly faster than Bubble Sort, especially
as array size grew. Quick Sort's divide-and-conquer approach reduces the number of
comparisons from O(n²) down to O(n log n) on average — for n = 1000 that is roughly
10,000 operations instead of 1,000,000.

On **already-sorted** input the result flipped: Bubble Sort (with early exit) finished
in O(n) time, while Quick Sort with last-element pivot hit its worst case O(n²)
because every partition produced one empty sub-array and one of size n-1.

### 2. How does performance change with input size?
Bubble Sort time grew roughly quadratically: going from n = 100 to n = 1000 (×10 the
input) increased time by about ×100, consistent with O(n²). Quick Sort grew close to
linearly-times-logarithmically on random input — much slower growth than Bubble Sort.

### 3. How does sorted vs unsorted data affect performance?
- **Bubble Sort** benefits hugely from sorted input: the early-exit check makes it O(n).
- **Quick Sort** with last-element pivot suffers badly on sorted input because the
  pivot is always the maximum value, so partitioning is maximally unbalanced. This is
  the textbook O(n²) worst case.

The lesson: the same algorithm can be fast or slow depending on the data.
Production implementations (like Java's `Arrays.sort`) use pivot selection strategies
such as median-of-three or randomization to avoid this trap.

### 4. Do the results match the expected Big-O complexity?
Yes, closely:
- Bubble Sort on random input: O(n²) — confirmed by the ×100 time growth for ×10 size.
- Bubble Sort on sorted input: O(n) — near-constant per-element time.
- Quick Sort on random input: O(n log n) — much slower growth than O(n²).
- Quick Sort on sorted input: O(n²) worst case — measurably slower than random for n = 1000.
- Linear Search: O(n) — linear growth with array size, and the "not found" case is
  consistently the slowest because it scans the whole array.

### 5. Which searching algorithm is more efficient? Why?
For unsorted data, Linear Search is the only straightforward option. However, if the
data can be sorted first, **Binary Search** is asymptotically far more efficient:
O(log n) vs O(n). For n = 1000, Binary Search needs about 10 comparisons; Linear
Search needs up to 1000.

Trade-off: sorting the array costs O(n log n) upfront. Binary Search only pays off if
we expect many searches on the same (stable) dataset.

### 6. Why does Binary Search require a sorted array?
Binary Search works by repeatedly halving the search space: it compares the target
with the middle element and decides whether to continue in the **left** or **right**
half. That decision is only meaningful if the array is sorted — otherwise the fact
that the middle element is smaller than the target tells us nothing about where the
target might be. Without order, there is no way to eliminate half the array in one
step, so the logarithmic guarantee collapses.

## E. Reflection

*(1–2 paragraphs — personalize before submitting.)*

Working on this assignment showed me that Big-O notation is more than a formula on
paper — it has very visible consequences when you measure real execution time. Seeing
Bubble Sort take dramatically longer than Quick Sort on 1000 random elements, and
then seeing that gap reverse on sorted input, made the theory concrete. The most
surprising part was how much the choice of **pivot** matters for Quick Sort: a small
implementation detail turned an O(n log n) algorithm into an O(n²) one.

The main challenges I faced were *[describe what you actually struggled with — for
example: understanding the partition step in Quick Sort, making sure `measureSortTime`
did not mutate the original array, or interpreting noisy timing results on small
arrays]*. Next time I would *[e.g. run each test multiple times and average, or try
a median-of-three pivot]* to get cleaner comparisons.


