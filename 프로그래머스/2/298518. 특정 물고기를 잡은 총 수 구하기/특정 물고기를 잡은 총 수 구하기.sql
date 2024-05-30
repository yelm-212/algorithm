-- 코드를 작성해주세요
SELECT 
    COUNT(A.ID) AS FISH_COUNT
FROM FISH_INFO AS A
LEFT JOIN FISH_NAME_INFO AS B
ON A.FISH_TYPE = B.FISH_TYPE
WHERE B.FISH_NAME = 'BASS' OR B.FISH_NAME = 'SNAPPER';