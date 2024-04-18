-- 코드를 작성해주세요
select count(ID) count from ECOLI_DATA
where GENOTYPE & 5 and not GENOTYPE & 2
