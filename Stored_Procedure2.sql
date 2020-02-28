drop procedure if exists cs5200_A3.endorsedUsersForWeek;

DELIMITER $$
create procedure cs5200_A3.endorsedUsersForWeek(IN startDate date, IN endDate date)
Begin
select p.firstName, p.lastName, t.correctAnswers from (
select count(a1.correctAnswer) as correctAnswers, a1.postedBy
FROM cs5200_A3.question q1
join answer a1 on q1.id = a1.questionId
where a1.correctAnswer = true and (q1.postedOn between startDate and endDate)
group by a1.postedBy
order by correctAnswers desc limit 5) t 
join user u on u.id = t.postedBy
join person p on p.id = u.id
order by p.firstName asc;

END$$

-- call cs5200_A3.endorsedUsersForWeek('2020-02-15', '2020-02-24');