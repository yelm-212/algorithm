import sys

r = sys.stdin.readline

N = int(r())
numList = list(map(str, r().split()))
max_val, min_val = None, None

visited = [False] * 10 # 0-9

def DFS(x, num): # 재귀함수
    global max_val, min_val
    
    if x == N+1:
        if not min_val:
            min_val = num
        else:
            max_val = num
        return
    for i in range(10):
        if not visited[i]: # 방문하지 않았으면
            if x == 0 or possible(num[-1], str(i), numList[x-1]):
                visited[i] = True
                DFS(x+1,num + str(i))
                visited[i] = False

def possible(i,j,k): # 가능한지 여부를 판정
    if k == '<':
        return i<j
    if k == '>':
        return i>j

    return True

DFS(0,"")
print(max_val)
print(min_val)
