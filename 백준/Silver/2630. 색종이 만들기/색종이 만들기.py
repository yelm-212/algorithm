import sys
read = sys.stdin.readline

N = int(input())
nlist = [list(map(int, read().rstrip().split())) for _ in range(N)] # 2차원 배열 리스트로 저장

ans = [0, 0]

def calc(x, y, N):
  clr = nlist[x][y] #현재 좌표 색상 저장

  #재귀함수 호출
  for row in range(x, x+N):
    for col in range(y, y+N):
      if clr !=nlist[row][col]:
        calc(x, y, N//2)
        calc(x, y+N//2, N//2)
        calc(x+N//2, y, N//2)
        calc(x+N//2, y+N//2, N//2)
        return 0

  if clr == 0:
    ans[0] +=1
  else:
    ans[1] +=1

calc(0, 0, N)
print(*ans, sep = "\n")