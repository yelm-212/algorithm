import sys
r = sys.stdin.readline
    
def Primechecker(n):
    if n == 1:
        return False
    else:
        for i in range(2, int(n**0.5)+1):
            if n%i ==0:
                return False
            
        return True

list = [0 for _ in range(20001)] # 10000*2 +1

for i in range(20001):
    if Primechecker(i): list[i] = 1

T = int(r())

for _ in range (T):
    n = int(r())
    temp = int(n/2)
    p1, p2 = 0, 0
    
    if list[temp] == 1: 
        p1, p2 = temp, temp
    else:
        for j in range(temp, n+1):
            if list[j] == 1: 
                p2 = j
                if list[n-j] == 1: 
                    p1 = n-j
                    break
                else: continue
        
        
    print(p1,p2)