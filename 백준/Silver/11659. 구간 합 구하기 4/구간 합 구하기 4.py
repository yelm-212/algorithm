import sys
r = sys.stdin.readline

N, M = map(int, r().rstrip().split())

Nlist = list(map(int, r().rstrip().split()))
sumlist = [0]
sum = 0

for nums in Nlist:
    sum += nums
    sumlist.append(sum)

for _ in range(M):
    i, j = map(int, r().rstrip().split())
    print(sumlist[j] - sumlist[i-1])