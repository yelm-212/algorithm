import sys
r = sys.stdin.readline
N = input()
a = list(map(int, r().split())) 

def prime(n):
    if n == 1:
        return False
    for i in range(2,n):
        if (n % i) == 0:
            return False
    return True
cnt = 0
for i in range (int(N)):
    if prime(a[i]):
        cnt+=1
        
print(cnt)